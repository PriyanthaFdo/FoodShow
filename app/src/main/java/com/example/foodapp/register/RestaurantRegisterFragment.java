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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.accounts.Restaurant;
import com.example.foodapp.customer.CustomerMain;
import com.example.foodapp.login.LoginMain;
import com.example.foodapp.restaurant.RestaurantMain;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RestaurantRegisterFragment extends Fragment {
    EditText shopName, email, address, mobileNumber, password, confirmPassword;
    Button btn_ToSignIn, btn_register;
    Restaurant restaurant;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;

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
        btn_ToSignIn = view.findViewById(R.id.btn_toLogin);
        btn_register = view.findViewById(R.id.btn_next);
        progressBar = getActivity().findViewById(R.id.progressBar);

        restaurant = new Restaurant();
        firebaseAuth = FirebaseAuth.getInstance();

        btn_ToSignIn.setOnClickListener(v -> {
            Intent i = new Intent(v.getContext(), LoginMain.class);
            startActivity(i);
        });

        btn_register.setOnClickListener(v -> {
            if(TextUtils.isEmpty(shopName.getText().toString())){
                shopName.setError("Restaurant Name is required!");
                shopName.requestFocus();
                return;
            }

            if(TextUtils.isEmpty(email.getText().toString())){
                email.setError("Email is required!");
                email.requestFocus();
                return;
            }

            if(TextUtils.isEmpty(address.getText().toString())){
                address.setError("Address is required!");
                address.requestFocus();
                return;
            }

            if(TextUtils.isEmpty(mobileNumber.getText().toString())){
                mobileNumber.setError("Mobile Number is required!");
                mobileNumber.requestFocus();
                return;
            }

            if(TextUtils.isEmpty(password.getText().toString())){
                password.setError("Password is required!");
                password.requestFocus();
                return;
            }

            if(password.getText().toString().length() < 6) {
                password.setError("Password must be at-least 6 characters long");
                password.requestFocus();
                return;
            }

            if(TextUtils.isEmpty(confirmPassword.getText().toString())){
                confirmPassword.setError("Password Confirmation is required!");
                confirmPassword.requestFocus();
                return;
            }

            if(!password.getText().toString().contentEquals(confirmPassword.getText().toString())) {
                password.setText("");
                confirmPassword.setText("");
                password.setError("Passwords not matched!");
                confirmPassword.setError("Passwords not matched!");
                password.requestFocus();
                return;
            }

            progressBar.setVisibility(View.VISIBLE);

            String mail = email.getText().toString().trim();
            String pass = password.getText().toString().trim();

            restaurant.setName(shopName.getText().toString().trim());
            restaurant.setEmail(mail);
            restaurant.setAddress(address.getText().toString().trim());
            restaurant.setMobile(mobileNumber.getText().toString().trim());
            restaurant.setPassword(pass);

            //store data in firebase authentication
            firebaseAuth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    restaurant.setUid(firebaseAuth.getCurrentUser().getUid());
                    //store data in realtime database
                    DB.child(restaurant.getUid()).setValue(restaurant);

                    Toast.makeText(getContext(), "Restaurant Account Created", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(view.getContext(), RestaurantMain.class));
                    getActivity().finish();
                }else{
                    Toast.makeText(getContext(), "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            });

        });

        return view;
    }
}