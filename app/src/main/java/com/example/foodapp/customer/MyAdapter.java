package com.example.foodapp.customer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.accounts.Restaurant;
import com.example.foodapp.register.RestaurantRegisterFragment;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

      ArrayList<CusRestaurant> restaurants;
      Context context;


    public MyAdapter(ArrayList<CusRestaurant> restaurants, Context context) {
        this.restaurants = restaurants;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_restaurant, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        CusRestaurant cusRes = restaurants.get(position);
        String mobile = Restaurant.getMobile();
        holder.name.setText(cusRes.getName());
        holder.Location.setText(cusRes.getAddress());

        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RestaurantDetails.class);
                intent.putExtra("mobile", mobile);
                context.startActivity(intent);
            }
        });
*/
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

       // Button views, rate;
        private TextView name, Location;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            /*rate = itemView.findViewById(R.id.Review);
            final Intent  intent1 = getIntent();

            rate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), WriteReview.class);
                    i.putExtra("mobile", )
                }
            });
*/


            //getting TextView from customer_fragment_home
            name = itemView.findViewById(R.id.resName);
            Location = itemView.findViewById(R.id.location);

        }
    }
}
