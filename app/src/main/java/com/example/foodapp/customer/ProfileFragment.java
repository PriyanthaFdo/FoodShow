package com.example.foodapp.customer;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TextView;

import com.example.foodapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {

    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Customer");
    private String phone;
    private TextView fName, lName, email, mobile, address, pwd;
    FirebaseAuth firebaseAuth;
    Button editProfile;

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
        editProfile = view.findViewById(R.id.updateProfile);
        firebaseAuth = FirebaseAuth.getInstance();

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), EditProfile.class);
                i.putExtra("firstName", fName.getText().toString());
                i.putExtra("lastName", lName.getText().toString());
                i.putExtra("email", email.getText().toString());
                i.putExtra("mobile", mobile.getText().toString());
                i.putExtra("address", address.getText().toString());
                i.putExtra("password", pwd.getText().toString());
                startActivity(i);
            }
        });

        dbRef = dbRef.child(firebaseAuth.getCurrentUser().getUid());
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                        fName.setText(snapshot.child("firstName").getValue().toString());
                        lName.setText(snapshot.child("lastName").getValue(String.class));
                        email.setText(snapshot.child("email").getValue(String.class));
                        mobile.setText(snapshot.child("mobile").getValue(String.class));
                        address.setText(snapshot.child("address").getValue(String.class));
                        pwd.setText(snapshot.child("password").getValue(String.class));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }
}
