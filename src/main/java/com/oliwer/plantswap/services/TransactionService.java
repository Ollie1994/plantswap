package com.oliwer.plantswap.services;

import com.oliwer.plantswap.enums.FormOfPayment;
import com.oliwer.plantswap.enums.PlantStatus;
import com.oliwer.plantswap.models.Plant;
import com.oliwer.plantswap.models.Transaction;
import com.oliwer.plantswap.models.User;
import com.oliwer.plantswap.repositories.PlantRepository;
import com.oliwer.plantswap.repositories.TransactionRepository;
import com.oliwer.plantswap.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;
    private PlantRepository plantRepository;
    private UserRepository userRepository;


    public TransactionService(TransactionRepository transactionRepository, PlantRepository plantRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.plantRepository = plantRepository;
        this.userRepository = userRepository;
    }


//------------------------------- METHODS ------------------------------------------------------------------------------

    public Transaction createTransaction(Transaction transaction) {
       getTransactionAndValidate(transaction.getFormOfPayment(),transaction.getPrice());
       User seller = getSellerAndValidate(transaction.getSeller());
       User buyer = getBuyerAndValidate(transaction.getBuyer());
       transaction.setSeller(seller);
       transaction.setBuyer(buyer);
       validateUserCombination(seller, buyer);
       Plant sellerPlant = getSellerPlantAndValidate(transaction.getSellerPlant());
       transaction.setSellerPlant(sellerPlant);
       transaction.getSellerPlant().setPlantStatus(PlantStatus.SOLD);

       if (transaction.getBuyerPlant() != null) {
            Plant buyerPlant = getBuyerPlantAndValidate(transaction.getBuyerPlant());
            transaction.setBuyerPlant(buyerPlant);
            validatePlantCombination(sellerPlant, buyerPlant);
            if (transaction.getBuyerPlant().getPlantStatus() != null) {
                transaction.getBuyerPlant().setPlantStatus(PlantStatus.SOLD);
            }
            plantRepository.save(buyerPlant);
        }

        if (transaction.getFormOfPayment() == FormOfPayment.TRADE && transaction.getSellerPlant().getFormOfPayment() == FormOfPayment.TRADE &&
                transaction.getBuyerPlant().getFormOfPayment() == FormOfPayment.TRADE) {
            if (transaction.getSellerAgreementToTrade() == null || transaction.getBuyerAgreementToTrade() == null
            ||transaction.getSellerAgreementToTrade() == false || transaction.getBuyerAgreementToTrade() == false) {
                throw new IllegalArgumentException("Both Seller and Buyer must have agreed to the trade");
            }

        }


        plantRepository.save(sellerPlant);
        return transactionRepository.save(transaction);
    }

    /*
    public Book createBook(Book book) {

        Author mainAuthor = getAuthorAndValidate(book.getAuthor(), "Main Author");

        book.setAuthor(mainAuthor);

        if (book.getCoAuthor() != null) {
            Author coAuthor = getAuthorAndValidate(book.getCoAuthor(), "Co Author");
            book.setCoAuthor(coAuthor);
            validateAuthorCombination(mainAuthor, coAuthor);
        }
     */



//------------------------------- HELPERS ------------------------------------------------------------------------------


    private User getSellerAndValidate(User seller) {
        // första kolla om id är tomt eller null
        if(seller == null || seller.getId() == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        // kolla i collection om author finns, kasta fel om ej finns
        return userRepository.findById(seller.getId())
                .orElseThrow(() -> new IllegalArgumentException("Seller not found!"));
    }


    private User getBuyerAndValidate(User buyer) {
        // första kolla om id är tomt eller null
        if(buyer == null || buyer.getId() == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        // kolla i collection om author finns, kasta fel om ej finns
        return userRepository.findById(buyer.getId())
                .orElseThrow(() -> new IllegalArgumentException("Buyer not found!"));
    }




    private void validateUserCombination(User seller, User buyer) {
        if (seller != null && seller.getId().equals(buyer.getId()) ||
            buyer != null && seller.getId().equals(buyer.getId())){
            throw new IllegalArgumentException("The seller and buyer cant be the same");
        }
    }

    private Plant getSellerPlantAndValidate(Plant sellerPlant) {
        // första kolla om id är tomt eller null
        if(sellerPlant == null || sellerPlant.getId() == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        // kolla i collection om author finns, kasta fel om ej finns
        return plantRepository.findById(sellerPlant.getId())
                .orElseThrow(() -> new IllegalArgumentException("SellerPlant not found!"));
    }

    private Plant getBuyerPlantAndValidate(Plant buyerPlant) {
        // första kolla om id är tomt eller null
        if(buyerPlant == null || buyerPlant.getId() == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        // kolla i collection om author finns, kasta fel om ej finns
        return plantRepository.findById(buyerPlant.getId())
                .orElseThrow(() -> new IllegalArgumentException("BuyerPlant not found!"));
    }

    private void validatePlantCombination(Plant sellerPlant, Plant buyerPlant) {
        if (sellerPlant != null && sellerPlant.getId().equals(buyerPlant.getId()) ||
                buyerPlant != null && sellerPlant.getId().equals(buyerPlant.getId())){
            throw new IllegalArgumentException("The sellerPlant and buyerPlant cant be the same");
        }
    }

    private void getTransactionAndValidate(FormOfPayment formOfPayment, Double price) {
        if (formOfPayment == null && price == null ||
                formOfPayment == null && price < 50 ||
                formOfPayment == null && price > 1000 ||
                formOfPayment == formOfPayment.TRADE && price == null ||
                formOfPayment == formOfPayment.TRADE && price > 0 ||
                formOfPayment == formOfPayment.TRADE && price < 0 ||
                formOfPayment == formOfPayment.CURRENCY && price < 50 ||
                formOfPayment == formOfPayment.CURRENCY && price > 1000) {
            throw new IllegalArgumentException("FormOfPayment cannot be <" + formOfPayment + "> while also having a price of <" + price + "> (have to be range between 50 and 1000)");
        }
    }

















}
