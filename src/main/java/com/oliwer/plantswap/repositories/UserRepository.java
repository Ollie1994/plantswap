package com.oliwer.plantswap.repositories;

import com.oliwer.plantswap.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
