package com.oliwer.plantswap.repositories;

import com.oliwer.plantswap.models.Plant;
import com.oliwer.plantswap.models.enums.PlantStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PlantRepository extends MongoRepository<Plant, String> {
    List<Plant> findByPlantStatus(PlantStatus plantStatus);

}

