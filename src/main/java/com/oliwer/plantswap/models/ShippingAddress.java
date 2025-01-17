package com.oliwer.plantswap.models;

public class ShippingAddress {

    private String country;
    private String city;
    private String streetAdress;
    private String postalCode;


    public ShippingAddress(String country, String city, String streetAdress, String postalCode) {
        this.country = country;
        this.city = city;
        this.streetAdress = streetAdress;
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetAdress() {
        return streetAdress;
    }

    public void setStreetAdress(String streetAdress) {
        this.streetAdress = streetAdress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
