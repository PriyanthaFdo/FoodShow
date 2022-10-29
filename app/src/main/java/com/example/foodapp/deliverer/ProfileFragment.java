package com.example.foodapp.deliverer;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.login.LoginMain;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {
    Button btn_logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.delivery_fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_logout = view.findViewById(R.id.dri_profile_logout);

        btn_logout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(v.getContext(), LoginMain.class));
            Toast.makeText(getContext(), "Logged out!", Toast.LENGTH_SHORT).show();
            getActivity().finish();
        });
    }
}