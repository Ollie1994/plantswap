package com.oliwer.plantswap.models;

import com.oliwer.plantswap.models.enums.FormOfPayment;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transactions")
public class Transaction {

    @Id
    private String id;

    @DBRef
    private User seller;

    @DBRef
    private User buyer;

    @DBRef
    private Plant sellerPlant;

    @DBRef
    private Plant buyerPlant;

    private FormOfPayment formOfPayment;
    private Double amount;



}
