package com.example.favlistapp;

import android.text.PrecomputedText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CatagoryRecyclerAdaptor extends RecyclerView.Adapter<CatagoryViewHolder> {

    //String [] cat = {"Hobbie","Hobbie","Hobbie","Hobbie","Hobbie","Hobbie","Hobbie"};


    interface CategoryIsClickedInterface{
        void categoryIsClick(Category category);
    }

    private CategoryIsClickedInterface categoryIsClickedInterface;

    private ArrayList<Category> cat;

    public CatagoryRecyclerAdaptor(ArrayList<Category>cat,CategoryIsClickedInterface categoryIsClickedInterface){
        this.cat = cat;
        this.categoryIsClickedInterface = categoryIsClickedInterface;
    }


    @NonNull
    @Override
    public CatagoryViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());


            View view = layoutInflater.inflate(R.layout.catagory_viewholder,parent,false);

            CatagoryViewHolder catagoryViewHolder = new CatagoryViewHolder(view);

            return catagoryViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull CatagoryViewHolder holder, final int position) {

        holder.getTxtCatNum().setText(Integer.toString(position+1));
        holder.getTxtCatName().setText(cat.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryIsClickedInterface.categoryIsClick(cat.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return cat.size();
    }

    public void addCategory(Category category){
        cat.add(category);
        notifyItemInserted(cat.size()-1);
    }
}
