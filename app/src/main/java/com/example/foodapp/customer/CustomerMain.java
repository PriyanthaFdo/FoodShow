package com.example.foodapp.customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.foodapp.R;
import com.example.foodapp.databinding.CustomerActivityMainBinding;

public class CustomerMain extends AppCompatActivity {
    CustomerActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = CustomerActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceCustomerFragment(new HomeFragment());

        binding.customerBottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if(id == R.id.cusNav_home)
                replaceCustomerFragment(new HomeFragment());
            else if(id == R.id.cusNav_cart)
                replaceCustomerFragment(new CartFragment());
            else if(id == R.id.cusNav_profile)
                replaceCustomerFragment(new ProfileFragment());

            return true;
        });

    }

    private void replaceCustomerFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.customer_frame_layout,fragment);
        fragmentTransaction.commit();
    }
}