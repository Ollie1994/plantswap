package com.oliwer.plantswap.models;

import java.util.ArrayList;

public class FormsOfPayment {

    private ArrayList<String> trade;
    private double price;





    public FormsOfPayment() {
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ArrayList<String> getTrade() {
        return trade;
    }

    public void setTrade(ArrayList<String> trade) {
        this.trade = trade;
    }
}
