
package com.oliwer.plantswap.models;

import com.oliwer.plantswap.enums.*;
import com.oliwer.plantswap.enums.Size;
import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;

@Document(collection = "plants")
public class Plant {

    @Id
    private String id;

// här jsonignore
    @DBRef
    @NotNull(message = "Cant be null")
    private User user;

    @NotNull(message = "Cant be null")
    @NotEmpty(message = "Cant be empty")
    @jakarta.validation.constraints.Size(max = 100, message = "max 100 characters")
    private String name;

    @NotNull(message = "Cant be null")
    private Size size;

    @NotNull(message = "Cant be null")
    private StageOfGrowth stageOfGrowth;

    @NotNull(message = "Cant be null")
    private LightRequirement lightRequirement;

    @NotEmpty(message = "Cant be empty")
    @NotNull(message = "Cant be null")
    @jakarta.validation.constraints.Size(max = 500, message = "max 300 characters")
    private String waterRequirement;

    @NotNull(message = "Cant be null")
    @Min(value = 1, message = "Min 1")
    @Max(value = 5, message = "Max 5")
    private Integer difficulty;

    @NotNull(message = "Cant be null")
    private FormOfPayment formOfPayment;

    @NotNull(message = "Cant be null")
    private Double price;


    @jakarta.validation.constraints.Size(max = 5, message = "max 5 photos")
    private ArrayList<String> photos;

    @NotNull(message = "Cant be null")
    private PlantStatus plantStatus;

    @NotNull(message = "Cant be null")
    private Date createdAt;

    @NotNull(message = "Cant be null")
    private Date updatedAt;

    @NotNull(message = "Cant be null")
    private Date endDate;




    //------------------------- CONSTRUCTOR -------------------------------------------------------------------------

    public Plant() {
    }


//------------------------- Getters & Setters ------------------------------------------------------------------------


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public @NotNull(message = "Cant be null") User getUser() {
        return user;
    }

    public void setUser(@NotNull(message = "Cant be null") User user) {
        this.user = user;
    }

    public @NotNull(message = "Cant be null") @NotEmpty(message = "Cant be empty") @jakarta.validation.constraints.Size(max = 100, message = "max 100 characters") String getName() {
        return name;
    }

    public void setName(@NotNull(message = "Cant be null") @NotEmpty(message = "Cant be empty") @jakarta.validation.constraints.Size(max = 100, message = "max 100 characters") String name) {
        this.name = name;
    }

    public @NotNull(message = "Cant be null") Size getSize() {
        return size;
    }

    public void setSize(@NotNull(message = "Cant be null") Size size) {
        this.size = size;
    }

    public @NotNull(message = "Cant be null") StageOfGrowth getStageOfGrowth() {
        return stageOfGrowth;
    }

    public void setStageOfGrowth(@NotNull(message = "Cant be null") StageOfGrowth stageOfGrowth) {
        this.stageOfGrowth = stageOfGrowth;
    }

    public @NotNull(message = "Cant be null") LightRequirement getLightRequirement() {
        return lightRequirement;
    }

    public void setLightRequirement(@NotNull(message = "Cant be null") LightRequirement lightRequirement) {
        this.lightRequirement = lightRequirement;
    }

    public @NotEmpty(message = "Cant be empty") @NotNull(message = "Cant be null") @jakarta.validation.constraints.Size(max = 500, message = "max 300 characters") String getWaterRequirement() {
        return waterRequirement;
    }

    public void setWaterRequirement(@NotEmpty(message = "Cant be empty") @NotNull(message = "Cant be null") @jakarta.validation.constraints.Size(max = 500, message = "max 300 characters") String waterRequirement) {
        this.waterRequirement = waterRequirement;
    }

    public @NotNull(message = "Cant be null") @Min(value = 1, message = "Min 1") @Max(value = 5, message = "Max 5") Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(@NotNull(message = "Cant be null") @Min(value = 1, message = "Min 1") @Max(value = 5, message = "Max 5") Integer difficulty) {
        this.difficulty = difficulty;
    }

    public @NotNull(message = "Cant be null") FormOfPayment getFormOfPayment() {
        return formOfPayment;
    }

    public void setFormOfPayment(@NotNull(message = "Cant be null") FormOfPayment formOfPayment) {
        this.formOfPayment = formOfPayment;
    }

    public @NotNull(message = "Cant be null") Double getPrice() {
        return price;
    }

    public void setPrice(@NotNull(message = "Cant be null") Double price) {
        this.price = price;
    }

    public @jakarta.validation.constraints.Size(max = 5, message = "max 5 photos") ArrayList<String> getPhotos() {
        return photos;
    }

    public void setPhotos(@jakarta.validation.constraints.Size(max = 5, message = "max 5 photos") ArrayList<String> photos) {
        this.photos = photos;
    }

    public @NotNull(message = "Cant be null") PlantStatus getPlantStatus() {
        return plantStatus;
    }

    public void setPlantStatus(@NotNull(message = "Cant be null") PlantStatus plantStatus) {
        this.plantStatus = plantStatus;
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

    public @NotNull(message = "Cant be null") Date getEndDate() {
        return endDate;
    }

    public void setEndDate(@NotNull(message = "Cant be null") Date endDate) {
        this.endDate = endDate;
    }
}
