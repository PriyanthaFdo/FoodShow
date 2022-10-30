package com.example.foodapp.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class FooditemMainActivity extends AppCompatActivity {

    String foodName;
    String price;

    public FooditemMainActivity() {
    }

    public FooditemMainActivity(String foodName, String price) {
        this.foodName = foodName;
        this.price = price;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
