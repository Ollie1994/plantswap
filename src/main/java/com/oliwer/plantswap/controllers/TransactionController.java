
package com.oliwer.plantswap.controllers;

import com.oliwer.plantswap.models.Transaction;
import com.oliwer.plantswap.repositories.PlantRepository;
import com.oliwer.plantswap.repositories.UserRepository;
import com.oliwer.plantswap.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final PlantRepository plantRepository;
    private final UserRepository userRepository;
    private final TransactionService transactionService;


    public TransactionController(PlantRepository plantRepository, UserRepository userRepository, TransactionService transactionService) {
        this.plantRepository = plantRepository;
        this.userRepository = userRepository;
        this.transactionService = transactionService;
    }



//------------------------------- METHODS ------------------------------------------------------------------------------

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody Transaction transaction) {
        Transaction newTransaction = transactionService.createTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTransaction);

    }







/*
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return ResponseEntity.ok(transactions);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable String id) {
        Transaction transaction = this.transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found"));
        return ResponseEntity.ok(transaction);
    }



    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable String id, @Valid @RequestBody Transaction transaction) {
        Transaction existingTransaction = this.transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found"));
        // uppdatera egenskaper
        existingTransaction.setSeller(transaction.getSeller());
        existingTransaction.setBuyer(transaction.getBuyer());
        existingTransaction.setSellerPlant(transaction.getSellerPlant());
        existingTransaction.setBuyerPlant(transaction.getBuyerPlant());
        existingTransaction.setFormOfPayment(transaction.getFormOfPayment());
        existingTransaction.setPrice(transaction.getPrice());
        existingTransaction.setSellerShippingAddress(transaction.getSellerShippingAddress());
        existingTransaction.setBuyerShippingAddress(transaction.getBuyerShippingAddress());
        existingTransaction.setSellerAgreementToTrade(transaction.getSellerAgreementToTrade());
        existingTransaction.setBuyerAgreementToTrade(transaction.getBuyerAgreementToTrade());
        existingTransaction.setCreatedAt(transaction.getCreatedAt());
        existingTransaction.setUpdatedAt(transaction.getUpdatedAt());


        return ResponseEntity.ok(this.transactionRepository.save(existingTransaction));
    }



    @GetMapping("/seller/{seller}")
    public ResponseEntity<List<Transaction>> getTransactionsBySellerId(@PathVariable String seller) {
        List<Transaction> transactions = transactionRepository.findBySeller(seller);
        return ResponseEntity.ok(transactions);
    }


    @GetMapping("/buyer/{buyer}")
    public ResponseEntity<List<Transaction>> getTransactionsByBuyerId(@PathVariable String buyer) {
        List<Transaction> transactions = transactionRepository.findByBuyer(buyer);
        return ResponseEntity.ok(transactions);
    }




 */



}


