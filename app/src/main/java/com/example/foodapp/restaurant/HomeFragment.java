package com.example.foodapp.restaurant;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.classes.OrderItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    Button changeStatus;
    private ArrayList<OrderItem> OrderedItems;
    RecyclerView orderRecyclerview;
    DatabaseReference OrderRef;
    OrderAdapter orderAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.restaurant_fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        changeStatus = view.findViewById(R.id.ChangeStatus);

        orderRecyclerview = view.findViewById(R.id.restaurant_order_recycler);
        OrderRef = FirebaseDatabase.getInstance().getReference().child("Orders");
        orderRecyclerview.setHasFixedSize(true);
        orderRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        OrderedItems = new ArrayList<>();
        orderAdapter = new OrderAdapter(OrderedItems, getContext());
        orderRecyclerview.setAdapter(orderAdapter);

        OrderRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    OrderItem orderItem = dataSnapshot.getValue(OrderItem.class);
                    OrderedItems.add(orderItem);
                }
                orderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}