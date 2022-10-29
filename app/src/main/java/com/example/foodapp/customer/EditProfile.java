package com.example.foodapp.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.accounts.Customer;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EditProfile extends AppCompatActivity {

    EditText edtfname, edtlname, edtemail, edtmobile, edtaddress, edtpwd;
    Button savedata;
    FirebaseAuth fAuth;
    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Customer");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        Intent data = getIntent();
        String fname = data.getStringExtra("firstName");
        String lname = data.getStringExtra("lastName");
        //String email = data.getStringExtra("email");
        String mobile = data.getStringExtra("mobile");
        String address = data.getStringExtra("address");
       // String pwd = data.getStringExtra("password");


        edtfname = findViewById(R.id.editfname);
        edtlname = findViewById(R.id.editlname);
       // edtemail = findViewById(R.id.editemail);
        edtmobile = findViewById(R.id.editpno);
        edtaddress = findViewById(R.id.editAddress);
       // edtpwd = findViewById(R.id.editPWD);
        savedata = findViewById(R.id.editProf);
        fAuth = FirebaseAuth.getInstance();
        //user = fAuth.getCurrentUser();

        edtfname.setText(fname);
        edtlname.setText(lname);
        //edtemail.setText(email);
        edtmobile.setText(mobile);
        edtaddress.setText(address);
        //edtpwd.setText(pwd);


        Customer cus = new Customer();

        savedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                String id = fAuth.getCurrentUser().getUid();
                if(edtfname.getText().toString().isEmpty()||edtlname.getText().toString().isEmpty()||
                edtmobile.getText().toString().isEmpty()||edtaddress.getText().toString().isEmpty()){

                    Toast.makeText(EditProfile.this, "Please, fill all the details", Toast.LENGTH_SHORT).show();

                }

                else{
                    cus.setFirstName(edtfname.getText().toString().trim());
                    cus.setLastName(edtlname.getText().toString().trim());
                    cus.setMobile(edtmobile.getText().toString().trim());
                    cus.setAddress(edtaddress.getText().toString().trim());

                    dbRef.child(id).child("firstName").setValue(cus.getFirstName());
                    dbRef.child(id).child("lastName").setValue(cus.getLastName());
                    dbRef.child(id).child("mobile").setValue(cus.getMobile());
                    dbRef.child(id).child("address").setValue(cus.getAddress());

                    Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(EditProfile.this, CustomerMain.class);
                    startActivity(i);

                }

               // String emailDB = edtemail.getText().toString();




            }
        });

    }
}