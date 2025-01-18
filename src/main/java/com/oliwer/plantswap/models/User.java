package com.oliwer.plantswap.models;

import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "users")
public class User {

    @Id
    private String id;

    @NotNull(message = "Cant be null")
    @NotEmpty(message = "Cant be empty")
    @Size(max = 20, message = "Can not be more than 20 character")
    private String username;

    @NotNull(message = "Cant be null")
    @NotEmpty(message = "Cant be empty")
    @Size(max = 20, message = "Can not be more than 20 character")
    private String firstName;

    @NotNull(message = "Cant be null")
    @NotEmpty(message = "Cant be empty")
    @Size(max = 20, message = "Can not be more than 20 character")
    private String lastName;

   // @Indexed(unique = true)
    @Email(message = "Not a valid email")
    @NotNull(message = "Cant be null")
    @NotEmpty(message = "Cant be empty")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).{8,}$", message = "The password must contain atleast 1 capital letter, one number and be 8 characters long ")
    private String password;

    private String profilePicture;

    @NotNull(message = "Cant be null")
    private Date createdAt;// Fel datum typ ?

    @NotNull(message = "Cant be null")
    private Date updatedAt; // Fel datum typ ?

    public User() {

    }












    //------------------------- Getters & Setters ------------------------------------------------------------------------


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public @NotNull(message = "Cant be null") @NotEmpty(message = "Cant be empty") @Size(max = 20, message = "Can not be more than 20 character") String getUsername() {
        return username;
    }

    public void setUsername(@NotNull(message = "Cant be null") @NotEmpty(message = "Cant be empty") @Size(max = 20, message = "Can not be more than 20 character") String username) {
        this.username = username;
    }

    public @NotNull(message = "Cant be null") @NotEmpty(message = "Cant be empty") @Size(max = 20, message = "Can not be more than 20 character") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull(message = "Cant be null") @NotEmpty(message = "Cant be empty") @Size(max = 20, message = "Can not be more than 20 character") String firstName) {
        this.firstName = firstName;
    }

    public @NotNull(message = "Cant be null") @NotEmpty(message = "Cant be empty") @Size(max = 20, message = "Can not be more than 20 character") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotNull(message = "Cant be null") @NotEmpty(message = "Cant be empty") @Size(max = 20, message = "Can not be more than 20 character") String lastName) {
        this.lastName = lastName;
    }

    public @Email(message = "Not a valid email") @NotNull(message = "Cant be null") @NotEmpty(message = "Cant be empty") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Not a valid email") @NotNull(message = "Cant be null") @NotEmpty(message = "Cant be empty") String email) {
        this.email = email;
    }

    public @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).{8,}$", message = "The password must contain atleast 1 capital letter, one number and be 8 characters long ") String getPassword() {
        return password;
    }

    public void setPassword(@Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).{8,}$", message = "The password must contain atleast 1 capital letter, one number and be 8 characters long ") String password) {
        this.password = password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public @NotNull(message = "Cant be null") Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(@NotNull(message = "Cant be null") Date createdAt) {
        this.createdAt = createdAt;
    }

    public @NotNull(message = "Cant be null") Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(@NotNull(message = "Cant be null") Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
