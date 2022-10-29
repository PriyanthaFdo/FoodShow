package com.example.foodapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //profile Start
    private TextView resID,resName,resAddress,resEmail,resConNo;
    private ImageView imageView,profileImage;
    private String email,password;
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private static final String RESTAURANT ="Restaurant";


    public ProfileFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Intent intent = new Intent();
        String email = intent.getStringExtra("email");

        resID=resID.findViewById(R.id.ResID);
        resName=resName.findViewById(R.id.resName);
        resEmail=resEmail.findViewById(R.id.resEmail);
        resAddress=resAddress.findViewById(R.id.resAddress);
        resConNo=resConNo.findViewById(R.id.resConNo);
        imageView=imageView.findViewById(R.id.imageView);
        profileImage=profileImage.findViewById(R.id.profile);

        database = FirebaseDatabase.getInstance();
        userRef = database.getReference(RESTAURANT);

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    if (dataSnapshot.child("email").getValue().equals(email)){
                        resName.setText(dataSnapshot.child("name").getValue(String.class));
                        resName.setText(email);
                        resName.setText(dataSnapshot.child("address").getValue(String.class));
                        resName.setText(dataSnapshot.child("mobile").getValue(String.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        return view;
    }
}