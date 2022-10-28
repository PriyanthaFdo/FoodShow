package com.example.foodapp.restaurant;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.foodapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class FoodFragment extends Fragment {
    FloatingActionButton floatingActionButton;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.restatant_fragment_food, container, false);

        floatingActionButton = view.findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),AddActivity.class));
            }
        });



       return view;
    }
}