package com.example.foodapp.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.foodapp.R;
import com.example.foodapp.register.RegisterMain;

public class LoginMain extends AppCompatActivity {

    Spinner loginTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_main);

        loginTypeSpinner = findViewById(R.id.loginTypeSpinner);

        //spinner (dropdown) to select Login Form
        ArrayAdapter<String> lgTypeAdapter = new ArrayAdapter<>(LoginMain.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.regTypes));
        lgTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        loginTypeSpinner.setAdapter(lgTypeAdapter);


    }
}