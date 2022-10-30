package com.example.foodapp.classes;

public class Restaurant {
    private String uid;
    private String name;
    private String email;
    private String Address;

    ///////
    static private String mobile;
    private String password;
    private final String role ="Restaurant";

    public Restaurant(){}

    public String getUid() { return uid; }

    public void setUid(String uid) { this.uid = uid; }

    public String getRole() { return role; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }



    /////////
    public static String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
