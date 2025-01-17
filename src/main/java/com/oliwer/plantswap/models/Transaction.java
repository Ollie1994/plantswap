package com.oliwer.plantswap.models;

import com.oliwer.plantswap.models.enums.FormOfPayment;
import com.oliwer.plantswap.models.enums.Status;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

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
    private ShippingAddress sellerShippingAddress;
    private ShippingAddress buyerShippingAddress;
    private Status status;
    private Date createdAt;
    private Date updatedAt;


    public Transaction() {
    }



    //------------------------- Getters & Setters ------------------------------------------------------------------------


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Plant getSellerPlant() {
        return sellerPlant;
    }

    public void setSellerPlant(Plant sellerPlant) {
        this.sellerPlant = sellerPlant;
    }

    public Plant getBuyerPlant() {
        return buyerPlant;
    }

    public void setBuyerPlant(Plant buyerPlant) {
        this.buyerPlant = buyerPlant;
    }

    public FormOfPayment getFormOfPayment() {
        return formOfPayment;
    }

    public void setFormOfPayment(FormOfPayment formOfPayment) {
        this.formOfPayment = formOfPayment;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public ShippingAddress getSellerShippingAddress() {
        return sellerShippingAddress;
    }

    public void setSellerShippingAddress(ShippingAddress sellerShippingAddress) {
        this.sellerShippingAddress = sellerShippingAddress;
    }

    public ShippingAddress getBuyerShippingAddress() {
        return buyerShippingAddress;
    }

    public void setBuyerShippingAddress(ShippingAddress buyerShippingAddress) {
        this.buyerShippingAddress = buyerShippingAddress;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
