package com.example.favlistapp;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    public TextView txtViewItem;


    public ItemViewHolder(View itemView){

        super(itemView);
        txtViewItem = itemView.findViewById(R.id.item_txtView);

    }
}
