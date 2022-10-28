package com.example.foodapp.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.foodapp.R;

public class DisplayReviewsCustomer extends AppCompatActivity {

    Button btnAddReview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_reviews_customer);

        btnAddReview = findViewById(R.id.addReview);

        btnAddReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DisplayReviewsCustomer.this, WriteReview.class);
                startActivity(i);
            }
        });

    }
}