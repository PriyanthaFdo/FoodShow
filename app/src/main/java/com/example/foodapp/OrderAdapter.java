package com.example.foodapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder>{

    private final ArrayList<Order> items;
    private final Context context;

    public OrderAdapter(ArrayList<Order> items, Context context) {
        this.items = items;
        this.context = context;
    }


    @NonNull
    @Override
    public OrderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.pending_orders_card,null));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.MyViewHolder holder, int position) {
        Order order =items.get(position);
        holder.orderId.setText(order.getOrderID());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //my ViewHolder class
    static class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView orderId;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            orderId = itemView.findViewById(R.id.orderID);
        }
    }
}


