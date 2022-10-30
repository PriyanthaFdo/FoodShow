package com.example.foodapp.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.classes.OrderItem;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder>{

    ArrayList<OrderItem> OrderedItems;
    Context context;

    public OrderAdapter(ArrayList<OrderItem> OrderedItems, Context context) {
        this.OrderedItems = OrderedItems;
        this.context = context;
    }


    @NonNull
    @Override
    public OrderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.restaurant_orders_card,parent,false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.MyViewHolder holder, int position) {
        OrderItem order =OrderedItems.get(position);
        holder.orderID.setText(order.getOrderID());
        holder.orderItems.setText(order.getOrderItems());
        holder.orderStatus.setText(order.getOrderStatus());
        holder.orderTotal.setText(order.getOrderTotal());
    }

    @Override
    public int getItemCount() {
        return OrderedItems.size();
    }

    //my ViewHolder class
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView orderID,orderStatus,orderItems,orderTotal;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.orderID = itemView.findViewById(R.id.OrderId);
            this.orderItems = itemView.findViewById(R.id.OrderItems);
            this.orderStatus = itemView.findViewById(R.id.OrderStatus);
            this.orderTotal = itemView.findViewById(R.id.OrderTotal);

        }
    }
}


