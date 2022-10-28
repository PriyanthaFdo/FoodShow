package com.example.foodapp.accounts;

public class Deliveryman {
    private String Uid;
    private String name;
    private String mobile;
    private String email;
    private String password;
    private final String role = "Deliveryman";

    public  Deliveryman(){}

    public String getUid() { return Uid; }

    public void setUid(String uid) { Uid = uid; }

    public String getRole() { return role; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
