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
import com.example.foodapp.accounts.Customer;
import com.example.foodapp.customer.CustomerMain;
import com.example.foodapp.login.LoginMain;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerRegisterFragment extends Fragment {
    EditText edt_fname, edt_lname, edt_mail, edt_mobile, edt_address, edt_pass, edt_conPass;
    Button btn_ToSignIn, btn_register;
    Customer customer;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    final DatabaseReference DB = FirebaseDatabase.getInstance().getReference().child("Customer");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.register_fragment_customer, container, false);

        edt_fname = view.findViewById(R.id.edt_reg_cus_FirstName);
        edt_lname = view.findViewById(R.id.edt_reg_cus_LastName);
        edt_mail = view.findViewById(R.id.edt_reg_cus_mail);
        edt_mobile = view.findViewById(R.id.edt_reg_cus_mobile);
        edt_address = view.findViewById(R.id.edt_reg_cus_address);
        edt_pass = view.findViewById(R.id.edt_reg_cus_password);
        edt_conPass = view.findViewById(R.id.edt_reg_cus_conformPassword);
        btn_ToSignIn = view.findViewById(R.id.btn_toLogin);
        btn_register = view.findViewById(R.id.btn_ToSignIn);
        progressBar = getActivity().findViewById(R.id.progressBar);

        customer = new Customer();
        fAuth = FirebaseAuth.getInstance();

        btn_ToSignIn.setOnClickListener(v -> {
            Intent i = new Intent(v.getContext(), LoginMain.class);
            startActivity(i);
        });

        if(fAuth.getCurrentUser() != null) {
            startActivity(new Intent(view.getContext(), CustomerMain.class));
            getActivity().finish();
        }

        btn_register.setOnClickListener(v ->{
            if(TextUtils.isEmpty(edt_fname.getText().toString())) {
                edt_fname.setError("First Name is Required!");
                edt_fname.requestFocus();
                return;
            }

            if(TextUtils.isEmpty(edt_lname.getText().toString())) {
                edt_lname.setError("Last Name is Required!");
                edt_lname.requestFocus();
                return;
            }

           if(TextUtils.isEmpty(edt_mail.getText().toString())) {
                edt_mail.setError("Email is required!");
                edt_mail.requestFocus();
                return;
            }

            if(TextUtils.isEmpty(edt_mobile.getText().toString())) {
                edt_mobile.setError("Mobile Number is Required!");
                edt_mobile.requestFocus();
                return;
            }

            if(TextUtils.isEmpty(edt_address.getText().toString())) {
                edt_address.setError("Address is Required!");
                edt_address.requestFocus();
                return;
            }

            if(TextUtils.isEmpty(edt_pass.getText().toString())) {
                edt_pass.setError("Password is Required!");
                edt_pass.requestFocus();
                return;
            }

            if(edt_pass.getText().toString().length() < 6) {
                edt_pass.setError("Password must be at-least 6 characters long");
                edt_pass.requestFocus();
                return;
            }

            if(TextUtils.isEmpty(edt_conPass.getText().toString())) {
                edt_conPass.setError("Password Confirmation is required!");
                edt_conPass.requestFocus();
                return;
            }

            if(!edt_pass.getText().toString().contentEquals(edt_conPass.getText().toString())) {
                edt_pass.setText("");
                edt_conPass.setText("");
                edt_pass.setError("Passwords not matched!");
                edt_conPass.setError("Passwords not matched!");
                edt_pass.requestFocus();
                return;
            }

            progressBar.setVisibility(View.VISIBLE);


            String email = edt_mail.getText().toString().trim();
            String pass = edt_pass.getText().toString().trim();

            customer.setFirstName(edt_fname.getText().toString().trim());
            customer.setLastName(edt_lname.getText().toString().trim());
            customer.setEmail(email);
            customer.setMobile(edt_mobile.getText().toString().trim());
            customer.setAddress(edt_address.getText().toString().trim());
            customer.setPassword(pass);

            //store data in firebase authentication
            fAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    customer.setUid(fAuth.getCurrentUser().getUid());
                    //store data in realtime database
                    DB.child(customer.getUid()).setValue(customer);

                    Toast.makeText(getContext(), "Customer Account Created", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(view.getContext(), CustomerMain.class));
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