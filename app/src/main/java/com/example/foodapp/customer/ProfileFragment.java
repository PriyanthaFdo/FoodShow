package com.example.foodapp.customer;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;

import com.example.foodapp.R;
import com.example.foodapp.login.LoginMain;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {
    Button btn_logout;

    final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Customer");
    private String phone;
    private TextView fName, lName, email, mobile, address, pwd;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.customer_fragment_profile, container, false);

        btn_logout = view.findViewById(R.id.btn_logout);

        btn_logout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(v.getContext(), LoginMain.class));
            Toast.makeText(getContext(), "Logged out!", Toast.LENGTH_SHORT).show();
            getActivity().finish();
        });


        Intent  intent = getActivity().getIntent();
        phone = intent.getStringExtra("mobile");

        fName = view.findViewById(R.id.fname);
        lName = view.findViewById(R.id.lname);
        email = view.findViewById(R.id.email);
        mobile = view.findViewById(R.id.mobile);
        address = view.findViewById(R.id.address);
        pwd = view.findViewById(R.id.password);

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    if(ds.child("mobile").getValue().equals(phone)){
                        fName.setText(ds.child("firstName").getValue(String.class));
                        lName.setText(ds.child("lastName").getValue(String.class));
                        email.setText(ds.child("email").getValue(String.class));
                        mobile.setText(phone);
                        address.setText(ds.child("address").getValue(String.class));
                        pwd.setText(ds.child("password").getValue(String.class));


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }
}
