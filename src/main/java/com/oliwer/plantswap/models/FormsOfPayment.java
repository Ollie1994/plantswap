package com.oliwer.plantswap.models;

import java.util.HashMap;

public class FormsOfPayment {

    private HashMap<String, String> trade;
    private double price;





    public FormsOfPayment() {
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public HashMap<String, String> getTrade() {
        return trade;
    }

    public void setTrade(HashMap<String, String> trade) {
        this.trade = trade;
    }
}
