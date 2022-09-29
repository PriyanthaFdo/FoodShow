package com.example.foodapp.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.foodapp.R;

public class RegisterMain extends AppCompatActivity {

    Spinner regTypeSpinner;

    CustomerRegisterFragment cusFragment;
    RestaurantRegisterFragment resFragment;
    DriverRegisterFragment driFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity_main);


        Spinner regTypeSpinner = findViewById(R.id.regTypeSpinner);
        cusFragment = new CustomerRegisterFragment();
        resFragment = new RestaurantRegisterFragment();
        driFragment = new DriverRegisterFragment();

        //spinner (dropdown) to select Register Form
        ArrayAdapter<String> regTypeAdapter = new ArrayAdapter<>(RegisterMain.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.regTypes));
        regTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regTypeSpinner.setAdapter(regTypeAdapter);

        regTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0: setFragment(cusFragment);
                        break;
                    case 1: setFragment(resFragment);
                        break;
                    case 2: setFragment(driFragment);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.RegFormFragmentView, fragment);
        fragmentTransaction.commit();
    }
}