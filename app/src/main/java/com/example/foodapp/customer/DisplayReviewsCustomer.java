package com.example.foodapp.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.foodapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DisplayReviewsCustomer extends AppCompatActivity {

    //FloatingActionButton btnAddReview;

    ImageView delete_bin;

    private ArrayList<Review> myReviewList;
    RecyclerView recycleView;
    DatabaseReference readRef;
    ReviewAdapter reviewAdapter;
    //FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_reviews_customer);

       // btnAddReview = findViewById(R.id.addReview);
        recycleView = findViewById(R.id.recyclerRateView);
        delete_bin = findViewById(R.id.deleteBin);


        readRef = FirebaseDatabase.getInstance().getReference("Admin");

        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

        myReviewList = new ArrayList<>();
        reviewAdapter = new ReviewAdapter(myReviewList, this);

        recycleView.setAdapter(reviewAdapter);

        reviewAdapter.setOnItemClickListener(new ReviewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                myReviewList.remove(position);

                reviewAdapter.notifyItemRemoved(position);
            }
        });







       // firebaseAuth = FirebaseAuth.getInstance();


        readRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

               myReviewList.clear();

                for(DataSnapshot reviews : snapshot.getChildren()){


                    Review rav = reviews.getValue(Review.class);

                    rav.setId(reviews.getKey());

                    myReviewList.add(rav);

                }

                reviewAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}