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
import com.example.foodapp.accounts.Customer;
import com.example.foodapp.accounts.Restaurant;
import com.example.foodapp.customer.CustomerMain;
import com.example.foodapp.deliverer.DeliveryMain;
import com.example.foodapp.register.RegisterMain;
import com.example.foodapp.restaurant.RestaurantMain;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Driver;

public class LoginMain extends AppCompatActivity {

    Spinner loginTypeSpinner;
    Button btn_ToReg, btn_login;
    EditText edt_mail, edt_pass;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    DatabaseReference db;
    String role;

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
        db = FirebaseDatabase.getInstance().getReference();

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
                    switch (type){
                        case "Customer":{
                            DatabaseReference dbCus = db.child("Customer");

                            dbCus.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if(snapshot.hasChild(firebaseAuth.getCurrentUser().getUid())){
                                        Toast.makeText(LoginMain.this, "Customer Login Successful!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), CustomerMain.class));
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(LoginMain.this, "Customer Login Failed!", Toast.LENGTH_SHORT).show();
                                        FirebaseAuth.getInstance().signOut();
                                        progressBar.setVisibility(View.INVISIBLE);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {}
                            });
                            break;
                        }

                        case "Restaurant":{
                            DatabaseReference dbCus = db.child("Restaurant");

                            dbCus.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if(snapshot.hasChild(firebaseAuth.getCurrentUser().getUid())){
                                        Toast.makeText(LoginMain.this, "Restaurant Login Successful!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), RestaurantMain.class));
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(LoginMain.this, "Restaurant Login Failed!", Toast.LENGTH_SHORT).show();
                                        FirebaseAuth.getInstance().signOut();
                                        progressBar.setVisibility(View.INVISIBLE);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {}
                            });
                            break;
                        }

                        case "Deliveryman":{
                            DatabaseReference dbCus = db.child("Deliveryman");

                            dbCus.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if(snapshot.hasChild(firebaseAuth.getCurrentUser().getUid())){
                                        Toast.makeText(LoginMain.this, "Deliveryman Login Successful!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), DeliveryMain.class));
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(LoginMain.this, "Deliveryman Login Failed!", Toast.LENGTH_SHORT).show();
                                        FirebaseAuth.getInstance().signOut();
                                        progressBar.setVisibility(View.INVISIBLE);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {}
                            });
                            break;
                        }
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