package com.oliwer.plantswap.repositories;

import com.oliwer.plantswap.enums.PlantStatus;
import com.oliwer.plantswap.models.Plant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PlantRepository extends MongoRepository<Plant, String> {

    // hittar alla plantor som är "AVAILABLE"
    List<Plant> findByPlantStatus(PlantStatus plantStatus);

    //hittar alla plantor som tillhör en user
    List<Plant> findByUser(String user);


}

