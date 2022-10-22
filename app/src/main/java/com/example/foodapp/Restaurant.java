package com.example.foodapp;

public class Restaurant {
    private String ResID;
    private String ResName;
    private String ResAddress;
    private String ResEmail;
    private Integer ResConNo;

    public Restaurant() {
    }

    public String getResID() {
        return ResID;
    }

    public void setResID(String resID) {
        ResID = resID;
    }

    public String getResName() {
        return ResName;
    }

    public void setResName(String resName) {
        ResName = resName;
    }

    public String getResAddress() {
        return ResAddress;
    }

    public void setResAddress(String resAddress) {
        ResAddress = resAddress;
    }

    public String getResEmail() {
        return ResEmail;
    }

    public void setResEmail(String resEmail) {
        ResEmail = resEmail;
    }

    public Integer getResConNo() {
        return ResConNo;
    }

    public void setResConNo(Integer resConNo) {
        ResConNo = resConNo;
    }
}
