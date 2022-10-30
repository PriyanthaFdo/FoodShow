package com.example.foodapp.classes;

public class OrderItem {
    private String orderID;
    private String address;

    public OrderItem() {}

    public OrderItem(String orderID, String Address) {
        this.orderID = orderID;
        this.address = Address;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getAddress() {
        return address;
    }
}
