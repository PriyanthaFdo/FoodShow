package com.example.foodapp.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.customer.CustomerMain;
import com.example.foodapp.deliverer.DeliveryMain;
import com.example.foodapp.register.RegisterMain;
import com.example.foodapp.restaurant.RestaurantMain;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginMain extends AppCompatActivity {

    Spinner loginTypeSpinner;
    Button btn_ToReg, btn_login;
    EditText edt_mail, edt_pass;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    DatabaseReference db;
    FirebaseUser uid;
    final String adminPass = "admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_main);

        loginTypeSpinner = findViewById(R.id.loginTypeSpinner);
        btn_ToReg = findViewById(R.id.View);
        btn_login = findViewById(R.id.btn_next);
        edt_mail = findViewById(R.id.edt_login_mail);
        edt_pass = findViewById(R.id.edt_login_pass);
        progressBar = findViewById(R.id.login_progressbar);
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference();
        uid = firebaseAuth.getCurrentUser();

        if(uid != null){
            progressBar.setVisibility(View.VISIBLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

            db.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.child("Customer").hasChild(uid.getUid())){
                        startActivity(new Intent(LoginMain.this, CustomerMain.class));
                        finish();
                    }
                    else if(snapshot.child("Deliveryman").hasChild(uid.getUid())){
                        startActivity(new Intent(LoginMain.this, DeliveryMain.class));
                        finish();
                    }
                    else if(snapshot.child("Restaurant").hasChild(uid.getUid())){
                        startActivity(new Intent(LoginMain.this, RestaurantMain.class));
                        finish();
                    }
                    else {
                        FirebaseAuth.getInstance().signOut();
                        progressBar.setVisibility(View.INVISIBLE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {}
            });
        }

        btn_login.setOnClickListener(v -> {
            String mail = edt_mail.getText().toString().trim();
            String pass = edt_pass.getText().toString().trim();
            String type = loginTypeSpinner.getSelectedItem().toString();

            if(pass.equals(adminPass)){
                Toast.makeText(this, pass, Toast.LENGTH_SHORT).show();
            }

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
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);



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
                                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
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
                                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
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
                                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
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
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
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