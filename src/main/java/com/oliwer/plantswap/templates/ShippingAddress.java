package com.oliwer.plantswap.templates;

public class ShippingAddress {

    private String country;
    private String city;
    private String streetAddress;
    private String postalCode;


    public ShippingAddress(String country, String city, String streetAddress, String postalCode) {
        this.country = country;
        this.city = city;
        this.streetAddress = streetAddress;
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

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
