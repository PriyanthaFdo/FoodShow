package com.example.foodapp.customer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.classes.Foods;

import java.util.ArrayList;

public class BuyItemAdapter extends RecyclerView.Adapter<BuyItemAdapter.BuyItemViewHolder> {
    Context context;
    ArrayList<Foods> list;

    public BuyItemAdapter(Context context, ArrayList<Foods> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public BuyItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.customer_fooditem, parent, false);
        return new BuyItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyItemViewHolder holder, int position) {
        Foods foodItem = list.get(position);
        holder.name.setText(foodItem.getName());
        holder.price.setText(foodItem.getPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class BuyItemViewHolder extends RecyclerView.ViewHolder{
        TextView name, price;


        public BuyItemViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.lbl_cust_fooditem_name);
            price = itemView.findViewById(R.id.lbl_cust_fooditem_price);
        }
    }
}
