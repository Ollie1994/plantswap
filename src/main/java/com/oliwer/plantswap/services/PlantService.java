package com.oliwer.plantswap.services;

import com.oliwer.plantswap.enums.FormOfPayment;
import com.oliwer.plantswap.enums.PlantStatus;
import com.oliwer.plantswap.models.Plant;
import com.oliwer.plantswap.models.User;
import com.oliwer.plantswap.repositories.PlantRepository;
import com.oliwer.plantswap.repositories.TransactionRepository;
import com.oliwer.plantswap.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantService {

    private TransactionRepository transactionRepository;
    private PlantRepository plantRepository;
    private UserRepository userRepository;

    public PlantService(PlantRepository plantRepository, UserRepository userRepository, TransactionRepository transactionRepository) {
        this.plantRepository = plantRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }




//------------------------------- METHODS ------------------------------------------------------------------------------

    public Plant createPlant(Plant plant) {
        User user = getUserAndValidate(plant.getUser());
        plant.setUser(user);
        checkToSeeHowManyPlantsAUserHas(plant.getUser().getId());
        getPlantAndCompare(plant.getFormOfPayment(), plant.getPrice());

        return plantRepository.save(plant);
    }

    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }

    public List<Plant> getAllAvailablePlants(PlantStatus plantStatus) {
        return plantRepository.findByPlantStatus(plantStatus);
    }

    public List<Plant> getAllPlantsByUserId(String user) {
        return plantRepository.findByUser(user);
    }

    public Optional<Plant> getPlantById(String id) {
        return plantRepository.findById(id);
    }

    public Plant updatePlant(String id, Plant plant) {
        Plant existingPlant = plantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Plant not found"));
        if (plant.getUser() != null) {
            getUserAndValidate(existingPlant.getUser());
        }
        // här måste vi ha våra setter annars blir alla fält som inte är med null
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
        return plantRepository.save(existingPlant);
    }




//------------------------------- HELPERS ------------------------------------------------------------------------------

    //validerar en user som sedan "setUser" sparar i createPlant (så att jag kan använda User och tillhörande fält i andra metoder)
    private User getUserAndValidate(User user) {
        if(user == null || user.getId() == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found!"));
    }

    //kollar hur många plantor en user har och begänsar de till 10
    public void checkToSeeHowManyPlantsAUserHas(String user) {
        List<Plant> plants = plantRepository.findByUser(user);
        Long count = plants.stream().count();
        if (count >= 10) {
            throw new IllegalArgumentException("User cant have more than 10 plants <" + count + ">");
        }
        System.out.println("User now has " + (count + 1) + " plants");
    }

    // kollar så att price och ENum TRADE inte kan vara samma. och kolla att ifall Currency så måste price vara mellan 50 och 1000
    private void getPlantAndCompare(FormOfPayment formOfPayment, Double price) {
        // första kolla om id är tomt eller null
        if (formOfPayment == null && price == null ||
                formOfPayment == null && price < 50 ||
                formOfPayment == null && price > 1000 ||
                formOfPayment == formOfPayment.TRADE && price == null ||
                formOfPayment == formOfPayment.TRADE && price > 0 ||
                formOfPayment == formOfPayment.TRADE && price < 0 ||
                formOfPayment == formOfPayment.CURRENCY && price < 50 ||
                formOfPayment == formOfPayment.CURRENCY && price > 1000) {
            throw new IllegalArgumentException("FormOfPayment cannot be <" + formOfPayment + "> while also having a price of <" + price + "> (if (currency) have to be range between 50 and 1000)");
        }
    }




}
