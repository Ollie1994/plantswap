
package com.oliwer.plantswap.controllers;

import com.oliwer.plantswap.enums.PlantStatus;
import com.oliwer.plantswap.models.Plant;
import com.oliwer.plantswap.repositories.PlantRepository;
import com.oliwer.plantswap.repositories.UserRepository;
import com.oliwer.plantswap.services.PlantService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/plants")
public class PlantController {

    private final PlantRepository plantRepository;
    private final UserRepository userRepository;
    private final PlantService plantService;

    public PlantController(PlantRepository plantRepository, UserRepository userRepository, PlantService plantService) {
        this.plantRepository = plantRepository;
        this.userRepository = userRepository;
        this.plantService = plantService;
    }

//------------------------------- METHODS ------------------------------------------------------------------------------

    @PostMapping
    public ResponseEntity<Plant> createPlant(@Valid @RequestBody Plant plant) {
        Plant newPlant = plantService.createPlant(plant);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPlant);
    }

    @GetMapping
    public ResponseEntity<List<Plant>> getAllPlants() {
        List<Plant> plants = plantService.getAllPlants();
        return ResponseEntity.ok(plants);
    }

    @GetMapping("/plantStatus/{plantStatus}")
    public ResponseEntity<List<Plant>> getAllPlantsByAvailability(@PathVariable PlantStatus plantStatus) {
        List<Plant> plants = plantService.getAllAvailablePlants(plantStatus);
        Long count = plants.stream().count();
        System.out.println(count);
        return ResponseEntity.ok(plants);
    }

    @GetMapping("/user/{user}")
    public ResponseEntity<List<Plant>> getAllPlantsByUserId(@PathVariable String user) {
        List<Plant> plants = plantService.getAllPlantsByUserId(user);
        Long count = plants.stream().count();
        System.out.println(count);
        return ResponseEntity.ok(plants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plant> getPlantById(@PathVariable String id) {
        Plant plant = plantService.getPlantById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plant not found"));
        return ResponseEntity.ok(plant);
    }

    // fick inte patch att funka
    @PutMapping("/{id}")
    public ResponseEntity<Plant> updatePlant(@PathVariable String id, @Valid @RequestBody Plant plant) {
        Plant existingPlant = plantService.updatePlant(id, plant);
        return ResponseEntity.ok(existingPlant);
    }

    // skippa service här för det blev så knäppt.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlant(@PathVariable String id) {
        if(!plantRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "plant not found");
        }
        plantRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }















}

