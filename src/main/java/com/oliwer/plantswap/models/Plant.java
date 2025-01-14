package com.oliwer.plantswap.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashMap;

@Document(collection = "plants")
public class Plant {

    @Id
    private String id;

    @DBRef
    private User user;

    private String name;
    private char size;
    private String stageOfGrowth;
    private String lightRequirement;
    private String waterRequirement;
    private int difficulty;
    private FormsOfPayment formsOfPayment;
    private HashMap<String, String> photos;
    private PlantStatus plantStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime endDate;
}
