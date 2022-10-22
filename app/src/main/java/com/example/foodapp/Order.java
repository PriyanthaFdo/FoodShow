package com.example.foodapp;

public class Order {
    private final String orderID;

    public Order(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderID() {
        return orderID;
    }
}
