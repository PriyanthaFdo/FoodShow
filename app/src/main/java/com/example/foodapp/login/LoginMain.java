package com.example.foodapp.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.customer.CustomerMain;
import com.example.foodapp.register.RegisterMain;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginMain extends AppCompatActivity {

    Spinner loginTypeSpinner;
    Button btn_ToReg, btn_login;
    EditText edt_mail, edt_pass;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_main);

        loginTypeSpinner = findViewById(R.id.loginTypeSpinner);
        btn_ToReg = findViewById(R.id.btn_toLogin);
        btn_login = findViewById(R.id.btn_next);
        edt_mail = findViewById(R.id.edt_login_mail);
        edt_pass = findViewById(R.id.edt_login_pass);
        progressBar = findViewById(R.id.login_progressbar);
        firebaseAuth = FirebaseAuth.getInstance();

        btn_login.setOnClickListener(v -> {
            String mail = edt_mail.getText().toString().trim();
            String pass = edt_pass.getText().toString().trim();
            String type = loginTypeSpinner.getSelectedItem().toString();

            if(TextUtils.isEmpty(mail)){
                edt_mail.setError("Mail is required!");
                edt_mail.requestFocus();
                return;
            }

            if(TextUtils.isEmpty(pass)){
                edt_pass.setError("Password is required!");
                edt_pass.requestFocus();
                return;
            }

            progressBar.setVisibility(View.VISIBLE);

            firebaseAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    Toast.makeText(LoginMain.this, "Successfuly Logged In!", Toast.LENGTH_SHORT).show();
                    if(TextUtils.equals(type, "Customer")) {
                        startActivity(new Intent(getApplicationContext(), CustomerMain.class));
                        finish();
                    }
                }
                else {
                    Toast.makeText(LoginMain.this, "Login Failed! "+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            });

        });

        btn_ToReg.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), RegisterMain.class);
            startActivity(intent);
        });

        //spinner (dropdown) to select Login type
        ArrayAdapter<String> lgTypeAdapter = new ArrayAdapter<>(LoginMain.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.regTypes));
        lgTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        loginTypeSpinner.setAdapter(lgTypeAdapter);
    }
}