package com.example.foodapp.customer;

public class Review {

    String review;
    float rate;


    public Review(String review, int rate) {
        this.review = review;
        this.rate = rate;
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

    public void setRate(float rate) {
        this.rate = rate;
    }
}
