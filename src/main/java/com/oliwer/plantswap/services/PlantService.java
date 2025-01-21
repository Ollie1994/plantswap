package com.oliwer.plantswap.services;

import com.oliwer.plantswap.models.Plant;
import com.oliwer.plantswap.models.User;
import com.oliwer.plantswap.repositories.PlantRepository;
import com.oliwer.plantswap.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantService {

    private PlantRepository plantRepository;
    private UserRepository userRepository;

    public PlantService(PlantRepository plantRepository, UserRepository userRepository) {
        this.plantRepository = plantRepository;
        this.userRepository = userRepository;
    }




//------------------------------- METHODS ------------------------------------------------------------------------------

    public Plant createPlant(Plant plant) {
       User user = getUserAndValidate(plant.getUser());
       plant.setUser(user);
        return plantRepository.save(plant);
    }



//------------------------------- HELPERS ------------------------------------------------------------------------------

    private User getUserAndValidate(User user) {
        // första kolla om id är tomt eller null
        if(user == null || user.getId() == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        // kolla i collection om author finns, kasta fel om ej finns
        return userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found!"));
    }

    public void checkToSeeHowManyPlantsAUserHas(String user) {
        List<Plant> plants = plantRepository.findByUser(user);
        Long count = plants.stream().count();
        if (count >= 10) {
            throw new IllegalArgumentException("User cant have more than 10 plants <" + count + ">");
        }
        System.out.println("User has now has " + (count + 1) + " plants");
    }





}
