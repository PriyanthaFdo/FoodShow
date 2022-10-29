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
import com.example.foodapp.accounts.Deliveryman;
import com.example.foodapp.login.LoginMain;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DriverRegisterFragment extends Fragment {
    EditText edt_name, edt_mobile, edt_email, edt_password, edt_confirmPassword;
    Button btn_ToSignIn, btn_next;
    Deliveryman deliveryman;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;

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
        btn_next = view.findViewById(R.id.btn_ToSignIn);
        progressBar = getActivity().findViewById(R.id.progressBar);

        deliveryman = new Deliveryman();
        firebaseAuth = FirebaseAuth.getInstance();

        btn_ToSignIn.setOnClickListener(v -> {
            Intent i = new Intent(v.getContext(), LoginMain.class);
            startActivity(i);
        });

        btn_next.setOnClickListener(v ->{
            if(TextUtils.isEmpty(edt_name.getText().toString())) {
                edt_name.setError("Name is Required!");
                edt_name.requestFocus();
                return;
            }

            if(TextUtils.isEmpty(edt_mobile.getText().toString())) {
                edt_mobile.setError("Mobile Number is Required!");
                edt_mobile.requestFocus();
                return;
            }

            if(TextUtils.isEmpty(edt_email.getText().toString())) {
                edt_email.setError("Email is Required!");
                edt_email.requestFocus();
                return;
            }

            if(TextUtils.isEmpty(edt_password.getText().toString())) {
                edt_password.setError("Email is Required!");
                edt_password.requestFocus();
                return;
            }

            if(edt_password.getText().toString().length() < 6) {
                edt_password.setError("Password must be at-least 6 characters long");
                edt_password.requestFocus();
                return;
            }

            if(TextUtils.isEmpty(edt_confirmPassword.getText().toString())) {
                edt_confirmPassword.setError("Email is Required!");
                edt_confirmPassword.requestFocus();
                return;
            }

            if(!edt_password.getText().toString().contentEquals(edt_confirmPassword.getText().toString())) {
                edt_password.setText("");
                edt_confirmPassword.setText("");
                edt_password.setError("Passwords not matched!");
                edt_confirmPassword.setError("Passwords not matched!");
                edt_password.requestFocus();
                return;
            }

            progressBar.setVisibility(View.VISIBLE);

            String email = edt_email.getText().toString().trim();
            String pass = edt_password.getText().toString().trim();

            deliveryman.setName(edt_name.getText().toString().trim());
            deliveryman.setMobile(edt_mobile.getText().toString().trim());
            deliveryman.setEmail(email);
            deliveryman.setPassword(pass);

            //store data in firebase authentication
            firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    deliveryman.setUid(firebaseAuth.getCurrentUser().getUid());
                    //store data in realtime database
                    DB.child(deliveryman.getUid()).setValue(deliveryman);

                    Intent i = new Intent(view.getContext(), UploadImage.class);
                    i.putExtra("next","Deliveryman");
                    startActivity(i);
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