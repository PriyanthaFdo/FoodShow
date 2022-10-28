package com.example.foodapp.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.foodapp.R;
import com.example.foodapp.register.RegisterMain;

public class LoginMain extends AppCompatActivity {

    Spinner loginTypeSpinner;
    Button btn_ToReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_main);

        loginTypeSpinner = findViewById(R.id.loginTypeSpinner);
        btn_ToReg = findViewById(R.id.View);

        btn_ToReg.setOnClickListener(view -> {


            Intent intent = new Intent(view.getContext(), RegisterMain.class);
            startActivity(intent);
        });

        //spinner (dropdown) to select Login type
        ArrayAdapter<String> lgTypeAdapter = new ArrayAdapter<>(LoginMain.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.regTypes));
        lgTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        loginTypeSpinner.setAdapter(lgTypeAdapter);
    }
}