package com.example.foodapp.customer;

public class Review {

    String review;
    Float rate;
    String Id;

    public Review() {
    }

    public Review(String review, Float rate) {
        this.review = review;
        this.rate = rate;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public float  getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }
}
