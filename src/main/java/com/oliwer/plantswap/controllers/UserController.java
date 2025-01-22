
package com.oliwer.plantswap.controllers;


import com.oliwer.plantswap.models.User;
import com.oliwer.plantswap.repositories.PlantRepository;
import com.oliwer.plantswap.repositories.TransactionRepository;
import com.oliwer.plantswap.repositories.UserRepository;
import com.oliwer.plantswap.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final PlantRepository plantRepository;
    private final TransactionRepository transactionRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(PlantRepository plantRepository, TransactionRepository transactionRepository, UserService userService, UserRepository userRepository) {
        this.plantRepository = plantRepository;
        this.transactionRepository = transactionRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    //------------------------------- METHODS ------------------------------------------------------------------------------

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User newUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userService.getUserById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return ResponseEntity.ok(user);
    }

    // fick inte patch att funka
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @Valid @RequestBody User user) {
        User existingUser = userService.updateUser(id, user);
        return ResponseEntity.ok(existingUser);
    }

    // skippa service här för det blev så knäppt.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        if(!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }















}


