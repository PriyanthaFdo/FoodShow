package com.example.foodapp.deliverer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;

import java.util.ArrayList;

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.DriverItemViewHolder> {
    Context context;
    ArrayList<OrderItem> itemArrayList;

    public ItemRecyclerAdapter(Context context, ArrayList<OrderItem> itemList) {
        this.context = context;
        this.itemArrayList = itemList;
    }

    @NonNull
    @Override
    public DriverItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.delivery_orderitem, parent, false);
        return new DriverItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRecyclerAdapter.DriverItemViewHolder holder, int position) {
        OrderItem order = itemArrayList.get(position);
        holder.orderID.setText(order.getOrderID());
        holder.address.setText(order.getAddress());
    }

    @Override
    public int getItemCount() {
        return itemArrayList .size();
    }

    public static class DriverItemViewHolder extends RecyclerView.ViewHolder{
        private TextView orderID;
        private TextView address;


        public DriverItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.orderID = itemView.findViewById(R.id.lbl_orderId);
            this.address = itemView.findViewById(R.id.lbl_address);
        }
    }
}
