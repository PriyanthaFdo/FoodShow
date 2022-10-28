package com.example.foodapp.register;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.databinding.RegisterActivityUploadimageBinding;
import com.example.foodapp.deliverer.DeliveryMain;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UploadImage extends AppCompatActivity {
    RegisterActivityUploadimageBinding binding;
    Uri imageUri;
    StorageReference storageReference;
    ProgressDialog progressDialog;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = RegisterActivityUploadimageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        id = getIntent().getStringExtra("id");

        binding.btnSelectImg.setOnClickListener(v -> {
            selectImage();
        });

        binding.btnUploadImg.setOnClickListener(v -> {
            if(imageUri != null)
                uploadImage(v);
            else
                Toast.makeText(UploadImage.this, "Select Image", Toast.LENGTH_SHORT).show();
        });

        binding.btnSkip.setOnClickListener(v -> {
            Intent i = new Intent(v.getContext(), DeliveryMain.class);
            startActivity(i);
        });
    }

    private void uploadImage(View v) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Creating your account...");
        progressDialog.show();

        storageReference = FirebaseStorage.getInstance().getReference("/images/driver/"+id);
        storageReference.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        binding.driverProfileImage.setImageURI(null);
                        Toast.makeText(UploadImage.this, "Upload Successful", Toast.LENGTH_SHORT).show();

                        if(progressDialog.isShowing())
                            progressDialog.dismiss();

                        Intent i = new Intent(v.getContext(), DeliveryMain.class);
                        startActivity(i);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if(progressDialog.isShowing())
                            progressDialog.dismiss();

                        Toast.makeText(UploadImage.this, "Failed to Upload", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && data != null && data.getData() != null){
            imageUri = data.getData();
            binding.driverProfileImage.setImageURI(imageUri);
        }
    }
}