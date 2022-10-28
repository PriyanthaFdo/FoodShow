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
import com.example.foodapp.accounts.Customer;
import com.example.foodapp.customer.CustomerMain;
import com.example.foodapp.login.LoginMain;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerRegisterFragment extends Fragment {
    EditText edt_fname, edt_lname, edt_mail, edt_mobile, edt_address, edt_pass, edt_conPass;
    Button btn_ToSignIn, btn_register;
    Customer customer;

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
        btn_ToSignIn = view.findViewById(R.id.View);
        btn_register = view.findViewById(R.id.btn_reg_signUp);

        customer = new Customer();

        btn_ToSignIn.setOnClickListener(v -> {
            Intent i = new Intent(v.getContext(), LoginMain.class);
            startActivity(i);
        });

        btn_register.setOnClickListener(v ->{
            if(SendDataToDB() == 1){
                Intent i = new Intent(v.getContext(), CustomerMain.class);
                startActivity(i);
            }
        });






        return view;
    }

    private int SendDataToDB(){
        int success = 0;
        try{
            if(TextUtils.isEmpty(edt_fname.getText().toString()))
                Toast.makeText(getActivity(), "Please Enter First Name", Toast.LENGTH_SHORT).show();
            else if(TextUtils.isEmpty(edt_lname.getText().toString()))
                Toast.makeText(getActivity(), "Please Enter Last Name", Toast.LENGTH_SHORT).show();
            else if(TextUtils.isEmpty(edt_mail.getText().toString()))
                Toast.makeText(getActivity(), "Please Enter Email", Toast.LENGTH_SHORT).show();
            else if(TextUtils.isEmpty(edt_mobile.getText().toString()))
                Toast.makeText(getActivity(), "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
            else if(TextUtils.isEmpty(edt_address.getText().toString()))
                Toast.makeText(getActivity(), "Please Enter Address", Toast.LENGTH_SHORT).show();
            else if(TextUtils.isEmpty(edt_pass.getText().toString()))
                Toast.makeText(getActivity(), "Please Enter Password", Toast.LENGTH_SHORT).show();
            else if(TextUtils.isEmpty(edt_conPass.getText().toString()))
                Toast.makeText(getActivity(), "Please Enter Confirm Password", Toast.LENGTH_SHORT).show();
            else if(!edt_pass.getText().toString().contentEquals(edt_conPass.getText().toString())){
                Toast.makeText(getActivity(), "Passwords mismatch! Enter Again!", Toast.LENGTH_SHORT).show();
                edt_pass.setText("");
                edt_conPass.setText("");
            }
            else{
                customer.setFirstName(edt_fname.getText().toString().trim());
                customer.setLastName(edt_lname.getText().toString().trim());
                customer.setEmail(edt_mail.getText().toString().trim());
                customer.setMobile(edt_mobile.getText().toString().trim());
                customer.setAddress(edt_address.getText().toString().trim());
                customer.setPassword(edt_pass.getText().toString().trim());

                DB.child(customer.getMobile()).setValue(customer);

                success = 1;
            }
        }catch (NumberFormatException e){
            Toast.makeText(getActivity(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
        }
        return  success;
    }
}