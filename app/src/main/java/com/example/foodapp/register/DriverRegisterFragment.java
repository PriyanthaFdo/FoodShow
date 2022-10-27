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
import com.example.foodapp.accounts.Deliveryman;
import com.example.foodapp.deliverer.DeliveryMain;
import com.example.foodapp.login.LoginMain;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DriverRegisterFragment extends Fragment {
    EditText edt_name, edt_mobile, edt_email, edt_password, edt_confirmPassword;
    Button btn_ToSignIn, btn_next;
    Deliveryman deliveryman;

    final DatabaseReference DB = FirebaseDatabase.getInstance().getReference().child("Deliveryman");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.register_fragment_driver, container, false);

        edt_name = view.findViewById(R.id.edt_reg_del_name);
        edt_mobile = view.findViewById(R.id.edt_reg_del_mobile);
        edt_email = view.findViewById(R.id.edt_reg_del_mail);
        edt_password = view.findViewById(R.id.edt_reg_del_pass);
        edt_confirmPassword = view.findViewById(R.id.edt_reg_del_confirmPass);
        btn_ToSignIn = view.findViewById(R.id.btn_toLogin);
        btn_next = view.findViewById(R.id.btn_next);

        deliveryman = new Deliveryman();

        btn_ToSignIn.setOnClickListener(v -> {
            Intent i = new Intent(v.getContext(), LoginMain.class);
            startActivity(i);
        });

        btn_next.setOnClickListener(v ->{
            if(sendDataToDB() == 1){
                Intent i = new Intent(v.getContext(), UploadImage.class);
                i.putExtra("id",edt_mobile.getText().toString());
                startActivity(i);
            }
        });

        return view;
    }

    private int sendDataToDB(){
        int success = 0;
        try{
            if(TextUtils.isEmpty(edt_name.getText().toString()))
                Toast.makeText(getActivity(), "Please Enter Name", Toast.LENGTH_SHORT).show();
            else if(TextUtils.isEmpty(edt_mobile.getText().toString()))
                Toast.makeText(getActivity(), "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
            else if(TextUtils.isEmpty(edt_email.getText().toString()))
                Toast.makeText(getActivity(), "Please Enter Email", Toast.LENGTH_SHORT).show();
            else if(TextUtils.isEmpty(edt_password.getText().toString()))
                Toast.makeText(getActivity(), "Please Enter Password", Toast.LENGTH_SHORT).show();
            else if(TextUtils.isEmpty(edt_confirmPassword.getText().toString()))
                Toast.makeText(getActivity(), "Please Enter Confirm Password", Toast.LENGTH_SHORT).show();
            else if(!edt_password.getText().toString().contentEquals(edt_confirmPassword.getText().toString())){
                Toast.makeText(getActivity(), "Confirm password mismatch! Try again", Toast.LENGTH_SHORT).show();
                edt_password.setText("");
                edt_confirmPassword.setText("");
            }
            else{
                deliveryman.setName(edt_name.getText().toString().trim());
                deliveryman.setMobile(edt_mobile.getText().toString().trim());
                deliveryman.setEmail(edt_email.getText().toString().trim());
                deliveryman.setPassword(edt_password.getText().toString().trim());

                DB.child(deliveryman.getMobile()).setValue(deliveryman);

                success = 1;
            }
        }catch (NumberFormatException e){
            Toast.makeText(getActivity(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
        }
        return success;
    }
}