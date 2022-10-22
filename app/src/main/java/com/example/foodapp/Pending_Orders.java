package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Pending_Orders extends AppCompatActivity {

    private final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    private final ArrayList<Order> myItemsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_orders);

        //getting RecyclerView from xml
        final RecyclerView recyclerView = findViewById(R.id.pendingOrders);

        //setting recyclerView size fixed for every item in the recyclerView
        recyclerView.setHasFixedSize(true);

        //setting linear layout
        recyclerView.setLayoutManager(new LinearLayoutManager(Pending_Orders.this));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                myItemsList.clear();

                for(DataSnapshot orders : snapshot.child("Orders").getChildren()){

                    if(orders.hasChild("id")) {
                        final String getOrderID = orders.child("id").getValue(String.class);

                        Order myItems = new Order(getOrderID);

                        myItemsList.add(myItems);
                    }
                }

                recyclerView.setAdapter(new OrderAdapter(myItemsList,Pending_Orders.this));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}