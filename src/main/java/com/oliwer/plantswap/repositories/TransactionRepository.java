package com.oliwer.plantswap.repositories;

import com.oliwer.plantswap.models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

    // dessa 2 hittar alla transAcs som tillhör seller/buyer
    List<Transaction> findBySeller(String seller);
    List<Transaction> findByBuyer(String buyer);
}
