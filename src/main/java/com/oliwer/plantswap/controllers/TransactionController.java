
package com.oliwer.plantswap.controllers;

import com.oliwer.plantswap.models.Transaction;
import com.oliwer.plantswap.repositories.PlantRepository;
import com.oliwer.plantswap.repositories.UserRepository;
import com.oliwer.plantswap.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/seller/{seller}")
    public ResponseEntity<List<Transaction>> getAllTransactionsBySellerId(@PathVariable String seller) {
        List<Transaction> transactions = transactionService.getAllTransactionsBySellerId(seller);
        Long count = transactions.stream().count();
        System.out.println(count);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/buyer/{buyer}")
    public ResponseEntity<List<Transaction>> getAllTransactionsByBuyerId(@PathVariable String buyer) {
        List<Transaction> transactions = transactionService.getAllTransactionsByBuyerId(buyer);
        Long count = transactions.stream().count();
        System.out.println(count);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable String id) {
        Transaction transaction = transactionService.getTransactionById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found"));
        return ResponseEntity.ok(transaction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable String id, @Valid @RequestBody Transaction transaction) {
        Transaction existingTransaction = transactionService.updateTransaction(id, transaction);
        return ResponseEntity.ok(existingTransaction);
    }

}


