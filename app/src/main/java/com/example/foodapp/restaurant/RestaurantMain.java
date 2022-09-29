package com.example.foodapp.restaurant;

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

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if(id == R.id.home)
                replaceFragment(new HomeFragment());
            else if(id == R.id.search)
                replaceFragment(new SearchFragment());
            else if(id == R.id.food)
                replaceFragment(new FoodFragment());
            else if(id == R.id.profile)
                replaceFragment(new ProfileFragment());

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