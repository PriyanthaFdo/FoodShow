package com.example.foodapp.restaurant;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.classes.Restaurant;
import com.example.foodapp.login.LoginMain;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.URISyntaxException;

public class ProfileFragment extends Fragment {
    Button btn_logout,res_edit;
    private TextView uid,name,Address,mobile,email;
    private String emil,password;
    private FirebaseDatabase database;
    private DatabaseReference ResRef;
    private String userid = FirebaseAuth.getInstance().getUid();
    private static final String RESTAURANT ="Restaurant";
    private Restaurant restaurant = new Restaurant();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.restaurant_fragment_profile, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {
            Intent intent = Intent.getIntent("email");
            String email = intent.getStringExtra("email");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        btn_logout = view.findViewById(R.id.btn_res_logout);
        res_edit = view.findViewById(R.id.restaurant_edit);

        //profile
        name = view.findViewById(R.id.res_Name);
        Address = view.findViewById(R.id.res_Address);
        email = view.findViewById(R.id.res_Email);
        mobile = view.findViewById(R.id.res_conNo);

        database = FirebaseDatabase.getInstance();
        ResRef = database.getReference(RESTAURANT).child(userid);

        ResRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name.setText(snapshot.child("name").getValue(String.class));
                email.setText(snapshot.child("email").getValue(String.class));
                Address.setText(snapshot.child("address").getValue(String.class));
                mobile.setText(snapshot.child("mobile").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btn_logout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(v.getContext(), LoginMain.class));
            getActivity().finish();
            Toast.makeText(getContext(), "Logged out!", Toast.LENGTH_SHORT).show();
        });
        res_edit.setOnClickListener(view1 -> {
            ResRef.child("name").setValue(name.getText().toString());
            ResRef.child("address").setValue(Address.getText().toString());
            ResRef.child("email").setValue(email.getText().toString());
            ResRef.child("mobile").setValue(mobile.getText().toString());
            Toast.makeText(getContext(), "Restaurants Details Update Successful", Toast.LENGTH_SHORT).show();
        });
    }
}