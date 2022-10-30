package com.example.foodapp.restaurant;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.foodapp.R;
import com.example.foodapp.databinding.RestaurantActivityMainBinding;

public class RestaurantMain extends AppCompatActivity {

    RestaurantActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = RestaurantActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        ActionBar actionBar = getSupportActionBar();
        setTitle("Home");


        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.resNav_home:
                    actionBar.setTitle("Home");
                    replaceFragment(new HomeFragment());
                    break;

                case R.id.resNav_search:
                    actionBar.setTitle("Search");
                    replaceFragment(new SearchFragment());
                    break;

                case R.id.resNev_food:
                    actionBar.setTitle("Food");
                    replaceFragment(new FoodFragment());
                    break;

                case R.id.resNav_pulse:
                    actionBar.setTitle("Profile");
                    replaceFragment(new ProfileFragment());
                    break;
            }

            return true;
        });
    }
    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();

    }
}