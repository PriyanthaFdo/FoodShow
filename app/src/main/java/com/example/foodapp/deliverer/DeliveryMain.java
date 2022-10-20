package com.example.foodapp.deliverer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.foodapp.R;
import com.example.foodapp.databinding.DeliveryActivityMainBinding;

public class DeliveryMain extends AppCompatActivity {
    DeliveryActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DeliveryActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceDeliveryFragment(new HomeFragment());

        binding.deliveryBottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if(id == R.id.driNav_home)
                replaceDeliveryFragment(new HomeFragment());
            else if(id == R.id.driNav_profile)
                replaceDeliveryFragment(new ProfileFragment());

            return true;
        });
    }
    private void replaceDeliveryFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.delivery_frame_layout,fragment);
        fragmentTransaction.commit();

    }
}