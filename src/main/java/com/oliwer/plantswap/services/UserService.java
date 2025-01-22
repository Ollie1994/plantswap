package com.oliwer.plantswap.services;

import com.oliwer.plantswap.models.User;
import com.oliwer.plantswap.repositories.PlantRepository;
import com.oliwer.plantswap.repositories.TransactionRepository;
import com.oliwer.plantswap.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PlantRepository plantRepository;
    private final TransactionRepository transactionRepository;

    public UserService(UserRepository userRepository, PlantRepository plantRepository, TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.plantRepository = plantRepository;
        this.transactionRepository = transactionRepository;
    }

//------------------------------- METHODS ------------------------------------------------------------------------------

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public User updateUser(String id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        // här måste vi ha våra setter annars blir alla fält som inte är med null
        existingUser.setUsername(user.getUsername());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        // Vet att vi inte ska hantera lösenord så här men för denna uppgift så fokuserar jag itne på det. eftersom ajg itne vet exakt hur än
        existingUser.setPassword(user.getPassword());
        existingUser.setProfilePicture(user.getProfilePicture());
        existingUser.setCreatedAt(user.getCreatedAt());
        existingUser.setUpdatedAt(user.getUpdatedAt());
        return userRepository.save(existingUser);
    }

//------------------------------- HELPERS ------------------------------------------------------------------------------


















}
