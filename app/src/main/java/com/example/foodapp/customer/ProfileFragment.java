package com.example.foodapp.customer;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.foodapp.R;
import com.example.foodapp.login.LoginMain;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {
    Button btn_logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.customer_fragment_profile, container, false);

        btn_logout = view.findViewById(R.id.btn_logout);

        btn_logout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(v.getContext(), LoginMain.class));
            getActivity().finish();
        });

        return view;
    }
}