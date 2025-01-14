package com.oliwer.plantswap.repositories;

import com.oliwer.plantswap.models.Plant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlantRepository extends MongoRepository<Plant, String> {
}
