package com.example.foodapp.customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.foodapp.R;
import com.example.foodapp.classes.Foods;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class BuyItemActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference DB;
    BuyItemAdapter adapter;
    ArrayList<Foods> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_activity_buy_item);

        recyclerView = findViewById(R.id.buyItemsrecyclerview);
        DB = FirebaseDatabase.getInstance().getReference();
        //TODO complete this recycler view https://youtu.be/M8sKwoVjqU0?t=764
    }
}