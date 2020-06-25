package com.example.favlistapp;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class CatagoryViewHolder extends RecyclerView.ViewHolder {

    private TextView txtCatNum;
    private TextView txtCatName;

    public CatagoryViewHolder(View view){
        super(view);

        txtCatName = view.findViewById(R.id.cat_name_txtview);
        txtCatNum = view.findViewById(R.id.cat_num_txtView);
    }

    public TextView getTxtCatNum() {
        return txtCatNum;
    }

    public TextView getTxtCatName() {
        return txtCatName;
    }
}
