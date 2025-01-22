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


    private final TransactionRepository transactionRepository;
    private final PlantRepository plantRepository;
    private final UserRepository userRepository;


    public TransactionService(TransactionRepository transactionRepository, PlantRepository plantRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.plantRepository = plantRepository;
        this.userRepository = userRepository;

    }


//------------------------------- METHODS ------------------------------------------------------------------------------

    public Transaction createTransaction(Transaction transaction) {
        getTransactionAndCompare(transaction.getFormOfPayment(), transaction.getPrice(), transaction.getSellerAgreementToTrade(),
                   transaction.getBuyerAgreementToTrade(), transaction.getSellerPlant().getFormOfPayment(), transaction.getBuyerPlant().getFormOfPayment());
        User seller = getSellerAndValidate(transaction.getSeller());
        User buyer = getBuyerAndValidate(transaction.getBuyer());
        transaction.setSeller(seller);
        transaction.setBuyer(buyer);
        validateUserCombination(seller, buyer);
        Plant sellerPlant = getSellerPlantAndValidate(transaction.getSellerPlant());
        transaction.setSellerPlant(sellerPlant);

        getSellerPlantAndCompareToTransaction(transaction.getFormOfPayment(), transaction.getSellerPlant().getFormOfPayment(),
                transaction.getPrice(), transaction.getSellerPlant().getPrice(), transaction.getSeller().getId(), transaction.getSellerPlant().getUser().getId());

        if (transaction.getBuyerPlant() != null) {
            Plant buyerPlant = getBuyerPlantAndValidate(transaction.getBuyerPlant());
            transaction.setBuyerPlant(buyerPlant);
            getBuyerPlantAndCompareToTransaction(transaction.getFormOfPayment(),
                    transaction.getBuyerPlant().getFormOfPayment(), transaction.getPrice(), transaction.getBuyerPlant().getPrice(),
                    transaction.getBuyer().getId(), transaction.getBuyerPlant().getUser().getId());
            validatePlantCombination(sellerPlant, buyerPlant);
            if (transaction.getBuyerPlant().getPlantStatus() != null) {
                transaction.getBuyerPlant().setPlantStatus(PlantStatus.SOLD);
            }
            plantRepository.save(buyerPlant);
        }
        transaction.getSellerPlant().setPlantStatus(PlantStatus.SOLD);
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
        if (seller == null || seller.getId() == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        // kolla i collection om Seller finns, kasta fel om ej finns
        return userRepository.findById(seller.getId())
                .orElseThrow(() -> new IllegalArgumentException("Seller not found!"));
    }

    private User getBuyerAndValidate(User buyer) {
        // första kolla om id är tomt eller null
        if (buyer == null || buyer.getId() == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        // kolla i collection om seller finns, kasta fel om ej finns
        return userRepository.findById(buyer.getId())
                .orElseThrow(() -> new IllegalArgumentException("Buyer not found!"));
    }

    private Plant getSellerPlantAndValidate(Plant sellerPlant) {
        // första kolla om id är tomt eller null
        if (sellerPlant == null || sellerPlant.getId() == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        // kolla i collection om sellerPlant finns, kasta fel om ej finns
        return plantRepository.findById(sellerPlant.getId())
                .orElseThrow(() -> new IllegalArgumentException("SellerPlant not found!"));
    }

    private Plant getBuyerPlantAndValidate(Plant buyerPlant) {
        // första kolla om id är tomt eller null
        if (buyerPlant == null || buyerPlant.getId() == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        // kolla i collection om buyerPlant finns, kasta fel om ej finns
        return plantRepository.findById(buyerPlant.getId())
                .orElseThrow(() -> new IllegalArgumentException("BuyerPlant not found!"));
    }






    private void validateUserCombination(User seller, User buyer) {
        if (seller != null && seller.getId().equals(buyer.getId()) ||
                buyer != null && seller.getId().equals(buyer.getId())) {
            throw new IllegalArgumentException("The seller and buyer cant be the same");
        }
    }

    private void getSellerPlantAndCompareToTransaction(FormOfPayment transactionFormOfPayment, FormOfPayment sellerFormOfPayment,
                                            Double transactionPrice, Double sellerPrice, String sellerId, String sellerPlantSellerId) {

        if (transactionFormOfPayment == transactionFormOfPayment.TRADE && sellerFormOfPayment == sellerFormOfPayment.CURRENCY ||
                transactionFormOfPayment == transactionFormOfPayment.CURRENCY && sellerFormOfPayment == sellerFormOfPayment.TRADE) {

            throw new IllegalArgumentException("FormOfPayment for transaction <" + transactionFormOfPayment + "> and seller <" + sellerFormOfPayment + "> must be the same");
        }
        if (!transactionPrice.equals(sellerPrice)) {
            throw new IllegalArgumentException("TransactionPrice <" + transactionPrice + "> must be equal to SellerPrice <" + sellerPrice + ">");
        }

        if (!sellerId.equals(sellerPlantSellerId)) {
            throw new IllegalArgumentException("Seller id <" + sellerId + "> must match sellerPlantSeller id <" + sellerPlantSellerId + ">");

        }

    }

    private void getBuyerPlantAndCompareToTransaction(FormOfPayment transactionFormOfPayment, FormOfPayment buyerFormOfPayment,
                                           Double transactionPrice, Double buyerPrice,  String buyerId, String buyerPlantSellerId) {
        if (transactionFormOfPayment == transactionFormOfPayment.TRADE && buyerFormOfPayment == buyerFormOfPayment.CURRENCY ||
                transactionFormOfPayment == transactionFormOfPayment.CURRENCY && buyerFormOfPayment == buyerFormOfPayment.TRADE) {

            throw new IllegalArgumentException("FormOfPayment for transaction <" + transactionFormOfPayment + "> and buyer <" + buyerFormOfPayment + "> must be the same");
        }
        // ÄDNRA DETTTA SKA ej kunna ha ett buyer price och ett trans price
        if (!transactionPrice.equals(buyerPrice)) {
            throw new IllegalArgumentException("TransactionPrice <" + transactionPrice + "> must be equal to BuyerPrice <" + buyerPrice + ">");
        }
        if (!buyerId.equals(buyerPlantSellerId)) {
            throw new IllegalArgumentException("Buyer id <" + buyerId + "> must match buyerPlantBuyer id <" + buyerPlantSellerId + ">");

        }
    }

    private void validatePlantCombination(Plant sellerPlant, Plant buyerPlant) {
        if (sellerPlant != null && sellerPlant.getId().equals(buyerPlant.getId()) ||
                buyerPlant != null && sellerPlant.getId().equals(buyerPlant.getId())) {
            throw new IllegalArgumentException("The sellerPlant and buyerPlant cant be the same");
        }
    }

    private void getTransactionAndCompare(FormOfPayment transactionFormOfPayment, Double price, Boolean sellerAgreementToTrade,
                                          Boolean buyerAgreementToTrade, FormOfPayment sellerFormOfPayment,
                                          FormOfPayment buyerFormOfPayment) {
        if (transactionFormOfPayment == null && price == null ||
                transactionFormOfPayment == null && price < 50 ||
                transactionFormOfPayment == null && price > 1000 ||
                transactionFormOfPayment == transactionFormOfPayment.TRADE && price == null ||
                transactionFormOfPayment == transactionFormOfPayment.TRADE && price > 0 ||
                transactionFormOfPayment == transactionFormOfPayment.TRADE && price < 0 ||
                transactionFormOfPayment == transactionFormOfPayment.CURRENCY && price < 50 ||
                transactionFormOfPayment == transactionFormOfPayment.CURRENCY && price > 1000) {
            throw new IllegalArgumentException("FormOfPayment cannot be <" + transactionFormOfPayment + "> while also having a price of <" + price + "> (have to be range between 50 and 1000)");
        }
        if (transactionFormOfPayment == transactionFormOfPayment.TRADE && sellerFormOfPayment == sellerFormOfPayment.TRADE &&
                buyerFormOfPayment == buyerFormOfPayment.TRADE) {
            if (sellerAgreementToTrade == null || buyerAgreementToTrade == null
                    || sellerAgreementToTrade == false || buyerAgreementToTrade == false) {
                throw new IllegalArgumentException("Both Seller and Buyer must have agreed to the trade");
            }

        }
    }


}
