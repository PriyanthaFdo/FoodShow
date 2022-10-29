package com.example.foodapp.customer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyRateViewHolder> {


    ArrayList<Review> reviews;
    Context context;


    private OnItemClickListener listener;



    public interface OnItemClickListener{
        void onItemClick(int position);
    }


    public void setOnItemClickListener(OnItemClickListener clickListener){
        listener = clickListener;
    }

    public ReviewAdapter(ArrayList<Review> reviews, Context context) {
        this.reviews = reviews;
        this.context = context;
    }

    @NonNull
    @Override
    public MyRateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyRateViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_reviews, parent, false),listener);

    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.MyRateViewHolder holder, int position) {
        Review revs = reviews.get(position);
      //  holder.Rate.setText((int) revs.getRate());

        //num.setText(String.valueOf(returnnum));
        holder.Rate.setText(String.valueOf(revs.getRate()));
        holder.ReviewTxt.setText(revs.getReview());

        holder.id.setText(revs.getId());
    }





    @Override
    public int getItemCount() {

        return reviews.size();
    }

    static class MyRateViewHolder extends RecyclerView.ViewHolder{
       private  final TextView Rate, ReviewTxt, id;
        private  ImageView deleteBin;



        public MyRateViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            deleteBin = itemView.findViewById(R.id.deleteBin);

            Rate = itemView.findViewById(R.id.rateStar);
            ReviewTxt = itemView.findViewById(R.id.rateReview);
            id = itemView.findViewById(R.id.uID);


            DatabaseReference delRef = FirebaseDatabase.getInstance().getReference("Admin");

            deleteBin.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {
                    listener.onItemClick(getBindingAdapterPosition());

                    delRef.child(id.getText().toString()).removeValue();


                }
            });
        }
    }

}