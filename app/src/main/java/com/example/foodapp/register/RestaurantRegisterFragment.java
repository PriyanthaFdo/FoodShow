package com.example.foodapp.register;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.accounts.Restaurant;
import com.example.foodapp.login.LoginMain;
import com.example.foodapp.restaurant.RestaurantMain;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RestaurantRegisterFragment extends Fragment {
    EditText shopName, email, address, mobileNumber, password, confirmPassword;
    Button btn_ToSignIn, btn_register;
    Restaurant restaurant;

    final DatabaseReference DB = FirebaseDatabase.getInstance().getReference().child("Restaurant");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.register_fragment_restaurant, container, false);

        shopName = view.findViewById(R.id.edt_reg_rest_name);
        email = view.findViewById(R.id.edt_reg_rest_mail);
        address = view.findViewById(R.id.edt_reg_rest_address);
        mobileNumber = view.findViewById(R.id.edt_reg_rest_mobile);
        password = view.findViewById(R.id.edt_reg_rest_password);
        confirmPassword = view.findViewById(R.id.edt_reg_rest_confirmPass);
        btn_ToSignIn = view.findViewById(R.id.View);
        btn_register = view.findViewById(R.id.btn_reg_signUp);


        restaurant = new Restaurant();

        btn_ToSignIn.setOnClickListener(v -> {
            Intent i = new Intent(v.getContext(), LoginMain.class);
            startActivity(i);
        });

        btn_register.setOnClickListener(v -> {
            if(saveDataToDB() == 1){
                Intent i = new Intent(v.getContext(), RestaurantMain.class);
                startActivity(i);
            }
        });

        return view;
    }

    private int saveDataToDB(){
        int success = 0;
        try{
            if(TextUtils.isEmpty(shopName.getText().toString()))
                Toast.makeText(getActivity(), "Please Enter Name", Toast.LENGTH_SHORT).show();
            else if(TextUtils.isEmpty(email.getText().toString()))
                Toast.makeText(getActivity(), "Please Enter Email", Toast.LENGTH_SHORT).show();
            else if(TextUtils.isEmpty(address.getText().toString()))
                Toast.makeText(getActivity(), "Please Enter Address", Toast.LENGTH_SHORT).show();
            else if(TextUtils.isEmpty(mobileNumber.getText().toString()))
                Toast.makeText(getActivity(), "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
            else if(TextUtils.isEmpty(password.getText().toString()))
                Toast.makeText(getActivity(), "Please Enter Password", Toast.LENGTH_SHORT).show();
            else if(TextUtils.isEmpty(confirmPassword.getText().toString()))
                Toast.makeText(getActivity(), "Please Enter Confirm Password", Toast.LENGTH_SHORT).show();
            else if(!password.getText().toString().contentEquals(confirmPassword.getText().toString())) {
                Toast.makeText(getActivity(), "Password mismatch! Try again!", Toast.LENGTH_SHORT).show();
                password.setText("");
                confirmPassword.setText("");
            }
            else{
                restaurant.setName(shopName.getText().toString().trim());
                restaurant.setEmail(email.getText().toString().trim());
                restaurant.setAddress(address.getText().toString().trim());
                restaurant.setMobile(mobileNumber.getText().toString().trim());
                restaurant.setPassword(password.getText().toString().trim());

                DB.child(restaurant.getMobile()).setValue(restaurant);

                success = 1;
            }
        }catch (NumberFormatException e){
            Toast.makeText(getActivity(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
        }

        return success;
    }
}