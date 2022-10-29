package com.example.foodapp.customer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.classes.Restaurant;

import java.util.ArrayList;

public class ViewRestaurantsAdapter extends RecyclerView.Adapter<ViewRestaurantsAdapter.MyViewHolder>{
      ArrayList<Restaurant> restaurants;
      Context context;


    public ViewRestaurantsAdapter(ArrayList<Restaurant> restaurants, Context context) {
        this.restaurants = restaurants;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewRestaurantsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_restaurant, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewRestaurantsAdapter.MyViewHolder holder, int position) {

        Restaurant cusRes = restaurants.get(position);
        holder.uid.setText(cusRes.getUid());
        holder.name.setText(cusRes.getName());
        holder.Location.setText(cusRes.getAddress());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, BuyItemActivity.class);
            intent.putExtra("u_id", cusRes.getUid());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView name, Location, uid;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.resName);
            Location = itemView.findViewById(R.id.location);
            uid = itemView.findViewById(R.id.rest_uid);
        }
    }
}
