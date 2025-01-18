package com.oliwer.plantswap.controllers;

import com.oliwer.plantswap.enums.PlantStatus;
import com.oliwer.plantswap.models.Plant;
import com.oliwer.plantswap.repositories.PlantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/plants")
public class PlantController {

    private final PlantRepository plantRepository;

    public PlantController(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }


    @PostMapping
    public ResponseEntity<Plant> createPlant(@RequestBody Plant plant) {
        Plant savedPlant = plantRepository.save(plant);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPlant);

    }


    @GetMapping
    public ResponseEntity<List<Plant>> getAllPlants() {
        List<Plant> plants = plantRepository.findAll();
        return ResponseEntity.ok(plants);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Plant> getPlantById(@PathVariable String id) {
        Plant plant = plantRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plant not found"));
        return ResponseEntity.ok(plant);
    }



    @PutMapping("/{id}")
    public ResponseEntity<Plant> updatePlant(@PathVariable String id, @RequestBody Plant plant) {
        Plant existingPlant = plantRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plant not found"));
        // uppdatera egenskaper
       existingPlant.setUser(plant.getUser());
       existingPlant.setName(plant.getName());
       existingPlant.setSize(plant.getSize());
       existingPlant.setStageOfGrowth(plant.getStageOfGrowth());
       existingPlant.setLightRequirement(plant.getLightRequirement());
       existingPlant.setWaterRequirement(plant.getWaterRequirement());
       existingPlant.setDifficulty(plant.getDifficulty());
       existingPlant.setFormOfPayment(plant.getFormOfPayment());
       existingPlant.setPrice(plant.getPrice());
       existingPlant.setPhotos(plant.getPhotos());
       existingPlant.setPlantStatus(plant.getPlantStatus());
       existingPlant.setCreatedAt(plant.getCreatedAt());
       existingPlant.setUpdatedAt(plant.getUpdatedAt());
       existingPlant.setEndDate(plant.getEndDate());

        return ResponseEntity.ok(plantRepository.save(existingPlant));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlant(@PathVariable String id) {
        if (!plantRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Plant not found");
        }
        plantRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/getAllAvailablePlants")
    public ResponseEntity<List<Plant>> getAllAvailablePlants(@RequestParam("plantStatus") PlantStatus plantStatus) {
        List<Plant> plants = plantRepository.findByPlantStatus(plantStatus);

        return ResponseEntity.ok(plants);
    }


    @GetMapping("/user/{user}")
    public ResponseEntity<List<Plant>> getPlantsByUserId(@PathVariable String user) {
        List<Plant> plants = plantRepository.findByUser(user);

        return ResponseEntity.ok(plants);
    }



/*
    // PATCH TEST
    @PatchMapping("/patch/{id}")
    public ResponseEntity<Plant> updateASpecificPlantAttribute(@PathVariable String id, @RequestBody Plant plant) {
        Plant existingPlant = plantRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plant not found"));


        return ResponseEntity.ok(plantRepository.save(existingPlant));
    }
*/


}
