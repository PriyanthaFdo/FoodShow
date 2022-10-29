package com.example.foodapp.restaurant;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.foodapp.databinding.RestaurantActivityFoodimageuploadBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class FoodUploadImageActivity extends AppCompatActivity {

    RestaurantActivityFoodimageuploadBinding binding;
    Uri imageUri;
    StorageReference storageReference;
    ProgressDialog progressDialog;
    String itemName;
    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = RestaurantActivityFoodimageuploadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            itemName = extras.getString("itemName");
        }


        setContentView(binding.getRoot());


        binding.btnSelectImg.setOnClickListener(v -> selectImage());

        binding.btnUploadImg.setOnClickListener(v -> {
            if(imageUri != null)
                uploadImage(v);
            else
                Toast.makeText(FoodUploadImageActivity.this, "Select Image", Toast.LENGTH_SHORT).show();
        });

    }



    private void uploadImage(View v) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading Image...");
        progressDialog.show();

        storageReference = FirebaseStorage.getInstance().getReference("/images/food/"+uid+"/"+itemName);
        storageReference.putFile(imageUri)
                .addOnSuccessListener(taskSnapshot -> {

                    binding.foodProfileImage.setImageURI(null);
                    Toast.makeText(FoodUploadImageActivity.this, "Upload Successful", Toast.LENGTH_SHORT).show();

                    if(progressDialog.isShowing())
                        progressDialog.dismiss();

                    Intent i = new Intent(v.getContext(), AddFoodActivity.class);
                    startActivity(i);
                }).addOnFailureListener(e -> {
                    if(progressDialog.isShowing())
                        progressDialog.dismiss();

                    Toast.makeText(FoodUploadImageActivity.this, "Failed to Upload", Toast.LENGTH_SHORT).show();
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
            binding.foodProfileImage.setImageURI(imageUri);
        }
    }
}


