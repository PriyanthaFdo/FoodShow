package com.example.foodapp.customer;

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
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.example.foodapp.R;
import com.example.foodapp.classes.Restaurant;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    FloatingActionButton viewReviewBtn;

    private ArrayList<Restaurant> myRestaurantList;
    RecyclerView recycleView;
    DatabaseReference readRef;
    ViewRestaurantsAdapter resAdapter;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.customer_fragment_home, container, false);

        viewReviewBtn=view.findViewById(R.id.floatingActionButton);


        viewReviewBtn.setOnClickListener(view1 -> {
            Intent i = new Intent(view1.getContext(), WriteReview.class);
            startActivity(i);
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
        resAdapter = new ViewRestaurantsAdapter(myRestaurantList, getContext());
        recycleView.setAdapter(resAdapter);
        progressBar = requireActivity().findViewById(R.id.cus_progressbar);

        progressBar.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        readRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myRestaurantList.clear();

                for (DataSnapshot restaurants : snapshot.getChildren()) {
                    Restaurant res = restaurants.getValue(Restaurant.class);
                    myRestaurantList.add(res);
                }

                resAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.INVISIBLE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
}