package com.example.foodapp.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.classes.Foods;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFoodActivity extends AppCompatActivity {

    EditText foodName,price;
    Button Save, btnBack;
    DatabaseReference dbRef;
    Foods food;
    FirebaseAuth firebaseAuth;
/*
    private void clearInputs(){
        foodName.setText("");
        price.setText("");

    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_activity_addfood);

        foodName=(EditText)findViewById(R.id.foodName);
        price=(EditText)findViewById(R.id.price);


        Save=(Button)findViewById(R.id.Save);
        btnBack=(Button)findViewById(R.id.btnBack);

        food = new Foods();
        firebaseAuth = FirebaseAuth.getInstance();

        Save.setOnClickListener(view -> {

            dbRef = FirebaseDatabase.getInstance().getReference().child("Restaurant").child(firebaseAuth.getCurrentUser().getUid()).child("FoodItem");

            if (TextUtils.isEmpty(foodName.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter a food", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(price.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter a Price", Toast.LENGTH_SHORT).show();


            else {
                food.setName(foodName.getText().toString().trim());
                food.setPrice(price.getText().toString().trim());

                //dbRef.push().setValue(std);
                dbRef.child(food.getName()).setValue(food);
                Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                //clearInputs();

                Intent i = new Intent(AddFoodActivity.this, FoodUploadImageActivity.class);
                i.putExtra("itemName",food.getName());
                startActivity(i);
            }
        });


        btnBack.setOnClickListener(view -> startActivity(new Intent(AddFoodActivity.this, RestaurantMain.class)));




    }


    }
