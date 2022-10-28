package com.example.foodapp.customer;

public class CusRestaurant {

    String name;
    String address;

    public CusRestaurant() {
    }

    public CusRestaurant(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
