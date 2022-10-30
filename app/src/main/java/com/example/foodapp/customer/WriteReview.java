package com.example.foodapp.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
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



    Button btnSubmit, backBtn;
    RatingBar ratingStar;
    TextView txtReview;
    FirebaseAuth firebaseAuth;
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
        rev = new Review();







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

                //display those messages based on the customer inputs
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
                if(TextUtils.isEmpty(txtReview.getText().toString())) {
                    txtReview.setError("Please enter a review about our app");
                    txtReview.requestFocus();
                    return;
                }

                Intent i = new Intent(WriteReview.this, CustomerMain.class);
                startActivity(i);
                InputData();



            }
        });

    }

    private void InputData() {

        rev.setReview(txtReview.getText().toString().trim());
        rev.setRate(ratingStar.getRating());

        //save review and rate details in the Admin reference
        dbRef =  FirebaseDatabase.getInstance().getReference("Admin");
        dbRef.push().setValue(rev).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                //display this when review added to DB successfully
                Toast.makeText(WriteReview.this, "Review Published successfully", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //display this when review failed to added review to DB
                Toast.makeText(WriteReview.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}