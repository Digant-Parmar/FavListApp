package com.example.favlistapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CategoryItemsActivity extends AppCompatActivity {

    private RecyclerView itemsRecyclerView;
    private FloatingActionButton addItemsFloatingActionbtn;
    Category category;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_category_items);

        category = (Category)getIntent().getSerializableExtra(MainActivity.CATEGORY_OBJECT_KEY);

        setTitle(category.getName());
        itemsRecyclerView = findViewById(R.id.items_recycler_view);

        itemsRecyclerView.setAdapter(new ItemsRecyclerAdapter(category));
        itemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        addItemsFloatingActionbtn = findViewById(R.id.add_item_btn);

        addItemsFloatingActionbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            displayItemCreationDialog();

            }
        });
    }
    private void displayItemCreationDialog(){
        final EditText itemEditTxt = new EditText(this);

        itemEditTxt.setInputType(InputType.TYPE_CLASS_TEXT);

        new AlertDialog.Builder(this)
                .setTitle("Enter the category item")
                .setView(itemEditTxt)
                .setPositiveButton("Creat Item", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        String itemName = itemEditTxt.getText().toString();
                        category.getItems().add(itemName);
                        ItemsRecyclerAdapter itemsRecyclerAdapter =(ItemsRecyclerAdapter) itemsRecyclerView.getAdapter();
                        itemsRecyclerAdapter.notifyItemInserted(category.getItems().size()-1);
                        dialog.dismiss();


                    }
                })
                .create()
                .show();
    }

    @Override
    public void onBackPressed() {

        Bundle bundle = new Bundle();
        bundle.putSerializable(MainActivity.CATEGORY_OBJECT_KEY,category);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK,intent);
        super.onBackPressed();
    }
}