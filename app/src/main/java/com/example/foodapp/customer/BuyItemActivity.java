package com.example.foodapp.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.foodapp.R;
import com.example.foodapp.classes.Foods;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BuyItemActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference DB;
    BuyItemAdapter adapter;
    ArrayList<Foods> list;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_activity_buy_item);

        Intent i = getIntent();
        uid = i.getStringExtra("u_id");

        recyclerView = findViewById(R.id.buyItemsrecyclerview);
        DB = FirebaseDatabase.getInstance().getReference().child("Restaurant").child(uid).child("FoodItem");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new BuyItemAdapter(this, list);
        recyclerView.setAdapter(adapter);

        DB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();

                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Foods food = dataSnapshot.getValue(Foods.class);
                    list.add(food);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}