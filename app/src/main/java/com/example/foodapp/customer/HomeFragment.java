package com.example.foodapp.customer;

import android.app.AlertDialog;
import android.content.Intent;
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

import com.example.foodapp.R;
import com.example.foodapp.accounts.Restaurant;
import com.example.foodapp.login.LoginMain;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    FloatingActionButton viewReviewBtn;

    //private ArrayList<CusRestaurant> RestaurantArrayList;
    private ArrayList<CusRestaurant> myRestaurantList;
    RecyclerView recycleView;
    DatabaseReference readRef;
    MyAdapter resAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.customer_fragment_home, container, false);


        viewReviewBtn=view.findViewById(R.id.floatingActionButton);

        viewReviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), DisplayReviewsCustomer.class);
                startActivity(i);
            }
        });



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recycleView = view.findViewById(R.id.recyclerview);
        readRef = FirebaseDatabase.getInstance().getReference().child("Restaurant");
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        myRestaurantList = new ArrayList<>();
        resAdapter = new MyAdapter(myRestaurantList, getContext());
        recycleView.setAdapter(resAdapter);




        readRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot restaurants : snapshot.getChildren()) {

                    CusRestaurant res = restaurants.getValue(CusRestaurant.class);

                    myRestaurantList.add(res);

                }

                resAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}