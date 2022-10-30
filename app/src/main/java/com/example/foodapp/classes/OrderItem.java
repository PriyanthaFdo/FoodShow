package com.example.foodapp.classes;

public class OrderItem {
    private String orderID;
    private String address;
    private String orderItems;
    private String orderStatus;
    private String orderTotal;

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

    public String getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(String orderItems) {
        this.orderItems = orderItems;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(String orderTotal) {
        this.orderTotal = orderTotal;
    }
}
