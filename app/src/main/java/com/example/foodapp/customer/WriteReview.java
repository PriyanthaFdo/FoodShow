package com.example.foodapp.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.accounts.Restaurant;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class WriteReview extends AppCompatActivity {


    //Unique phone nu of restaurant
    //private

    Button btnSubmit, backBtn;
    RatingBar ratingStar;
    TextView txtReview;
    FirebaseAuth firebaseAuth;
    //String mobile;
    private DatabaseReference dbRef;
    Review rev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);

        backBtn = findViewById(R.id.back);
        txtReview = findViewById(R.id.Review);
        btnSubmit =  findViewById(R.id.btnSubmit);
        ratingStar = findViewById(R.id.ratingBar);
       // firebaseAuth = FirebaseAuth.getInstance();
        rev = new Review();
       // mobile = getIntent().getStringExtra("mobile");

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });

        ratingStar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {


                int rating = (int) v;
                String message = null;

                switch(rating) {
                    case 1:
                        message = "Sorry to hear that!";
                        break;

                    case 2:
                        message = "You always accept suggestions!";
                        break;

                    case 3:
                        message = "Good enough!";
                        break;

                    case 4:
                        message = "Great! Thank you!";
                        break;

                    case 5:
                        message = "Awesome! You are the best";
                        break;
                }

                Toast.makeText(WriteReview.this, message, Toast.LENGTH_SHORT).show();


            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputData();


            }
        });

    }

    private void InputData() {


        rev.setReview(txtReview.getText().toString().trim());
        rev.setRate(ratingStar.getRating());



/*
        HashMap<String, Object> hashmap = new HashMap<>();
      // hashmap.put("RestaurantNo", ""+mobile);
        hashmap.put("ratings", "" + ratings);
        hashmap.put("review", "" + review);

 */

dbRef =  FirebaseDatabase.getInstance().getReference("Admin");
        dbRef.push().setValue(rev).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                //review added to DB
                Toast.makeText(WriteReview.this, "Review Published successfully", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //failed added review to DB
                Toast.makeText(WriteReview.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}