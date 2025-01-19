package com.oliwer.plantswap.models;

import com.oliwer.plantswap.enums.FormOfPayment;
import com.oliwer.plantswap.enums.Status;
import com.oliwer.plantswap.templates.ShippingAddress;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "transactions")
public class Transaction {

    @Id
    private String id;

    @DBRef
    @NotNull(message = "Cant be null")
    private User seller;

    @DBRef
    @NotNull(message = "Cant be null")
    private User buyer;

    @DBRef
    @NotNull(message = "Cant be null")
    private Plant sellerPlant;

    @DBRef
    private Plant buyerPlant;

    @NotNull(message = "Cant be null")
    private FormOfPayment formOfPayment;

    @Min(value = 50, message = "Min 50")
    @Max(value = 1000, message = "Max 1000")
    private Double amount;

    private ShippingAddress sellerShippingAddress;

    @NotNull (message = "Cant be null")
    private ShippingAddress buyerShippingAddress;

    @NotNull (message = "Cant be null")
    private Status status;

    @AssertTrue (message = "Cant be false")
    private Boolean sellerAgreementToTrade;

    @AssertTrue (message = "Cant be false")
    private Boolean buyerAgreementToTrade;

    @NotNull (message = "Cant be null")
    private Date createdAt;

    @NotNull (message = "Cant be null")
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

    public @NotNull(message = "Cant be null") User getSeller() {
        return seller;
    }

    public void setSeller(@NotNull(message = "Cant be null") User seller) {
        this.seller = seller;
    }

    public @NotNull(message = "Cant be null") User getBuyer() {
        return buyer;
    }

    public void setBuyer(@NotNull(message = "Cant be null") User buyer) {
        this.buyer = buyer;
    }

    public @NotNull(message = "Cant be null") Plant getSellerPlant() {
        return sellerPlant;
    }

    public void setSellerPlant(@NotNull(message = "Cant be null") Plant sellerPlant) {
        this.sellerPlant = sellerPlant;
    }

    public Plant getBuyerPlant() {
        return buyerPlant;
    }

    public void setBuyerPlant(Plant buyerPlant) {
        this.buyerPlant = buyerPlant;
    }

    public @NotNull(message = "Cant be null") FormOfPayment getFormOfPayment() {
        return formOfPayment;
    }

    public void setFormOfPayment(@NotNull(message = "Cant be null") FormOfPayment formOfPayment) {
        this.formOfPayment = formOfPayment;
    }

    public @Min(value = 50, message = "Min 50") @Max(value = 1000, message = "Max 1000") Double getAmount() {
        return amount;
    }

    public void setAmount(@Min(value = 50, message = "Min 50") @Max(value = 1000, message = "Max 1000") Double amount) {
        this.amount = amount;
    }

    public ShippingAddress getSellerShippingAddress() {
        return sellerShippingAddress;
    }

    public void setSellerShippingAddress(ShippingAddress sellerShippingAddress) {
        this.sellerShippingAddress = sellerShippingAddress;
    }

    public @NotNull(message = "Cant be null") ShippingAddress getBuyerShippingAddress() {
        return buyerShippingAddress;
    }

    public void setBuyerShippingAddress(@NotNull(message = "Cant be null") ShippingAddress buyerShippingAddress) {
        this.buyerShippingAddress = buyerShippingAddress;
    }

    public @NotNull(message = "Cant be null") Status getStatus() {
        return status;
    }

    public void setStatus(@NotNull(message = "Cant be null") Status status) {
        this.status = status;
    }

    public @AssertTrue(message = "Cant be false") Boolean getSellerAgreementToTrade() {
        return sellerAgreementToTrade;
    }

    public void setSellerAgreementToTrade(@AssertTrue(message = "Cant be false") Boolean sellerAgreementToTrade) {
        this.sellerAgreementToTrade = sellerAgreementToTrade;
    }

    public @AssertTrue(message = "Cant be false") Boolean getBuyerAgreementToTrade() {
        return buyerAgreementToTrade;
    }

    public void setBuyerAgreementToTrade(@AssertTrue(message = "Cant be false") Boolean buyerAgreementToTrade) {
        this.buyerAgreementToTrade = buyerAgreementToTrade;
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
