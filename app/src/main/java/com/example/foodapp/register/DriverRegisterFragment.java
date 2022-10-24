package com.example.foodapp.register;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.foodapp.R;
import com.example.foodapp.login.LoginMain;

public class DriverRegisterFragment extends Fragment {
    Button btn_ToSignIn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.register_fragment_driver, container, false);

        btn_ToSignIn = view.findViewById(R.id.btn_toLogin);

        btn_ToSignIn.setOnClickListener(v -> {
            Intent i = new Intent(v.getContext(), LoginMain.class);
            startActivity(i);
        });

        return view;
    }
}