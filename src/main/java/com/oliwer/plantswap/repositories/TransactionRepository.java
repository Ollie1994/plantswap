package com.oliwer.plantswap.repositories;

import com.oliwer.plantswap.models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findByUser(String user);

}
