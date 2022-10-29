package com.example.foodapp.classes;

public class OrderItem {
    private String orderID;
    private String address;
    private String pickLocation;
    private String destinationMobile;
    private String price;

    private class foodInOrder{
        private String foodName;
        private String fodPrice;

    }

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
