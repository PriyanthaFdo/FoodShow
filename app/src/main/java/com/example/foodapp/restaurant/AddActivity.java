package com.example.foodapp.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.accounts.Foods;
import com.example.foodapp.databinding.RestaurantActivityMainBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {

    EditText foodName,price;
    Button Save, btnBack;
    DatabaseReference dbRef;
    Foods food;

    private void clearInputs(){
        foodName.setText("");
        price.setText("");

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add2);

        foodName=(EditText)findViewById(R.id.foodName);
        price=(EditText)findViewById(R.id.price);


        Save=(Button)findViewById(R.id.Save);
        btnBack=(Button)findViewById(R.id.btnBack);

        food = new Foods();

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbRef = FirebaseDatabase.getInstance().getReference().child("FoodItem");

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
                        clearInputs();

                        Intent i = new Intent(AddActivity.this, FoodUploadImageActivity.class);
                        i.putExtra("itemName",food.getName());
                        startActivity(i);
                    }




            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           //     startActivity(new Intent(this,FoodFragment.this));
            }
        });




    }


    }
