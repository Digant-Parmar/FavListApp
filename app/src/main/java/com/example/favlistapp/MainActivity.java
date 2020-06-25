package com.example.favlistapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CatagoryRecyclerAdaptor.CategoryIsClickedInterface {

    private RecyclerView catagoryRecyclerView;
    private CategoryManager mCategoryManager = new CategoryManager(this);

    public static final String CATEGORY_OBJECT_KEY = "CATEGORY_KEY";
    public static final int MAIN_ACTIVITY_REQUEST_CODE_ = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ArrayList<Category> categories  = mCategoryManager.retrieveCategories();

        catagoryRecyclerView = findViewById(R.id.cat_RV);
        catagoryRecyclerView.setAdapter(new CatagoryRecyclerAdaptor(categories,this));
        catagoryRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(MainActivity.this, "Fab is tapped", Toast.LENGTH_SHORT).show();
                displayCreateCategoryDialoge();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void displayCreateCategoryDialoge(){
        String alertTitle = "Enter the name of the Category";
        String positiveButtonTitle = "Create";
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        final EditText categoryEditText = new EditText(this);
        categoryEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        alertBuilder.setTitle(alertTitle);
        alertBuilder.setView(categoryEditText);
        alertBuilder.setPositiveButton(positiveButtonTitle, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                    Category category = new Category(categoryEditText.getText().toString(),new ArrayList<String>());
                    mCategoryManager.saveCategory(category);

                    CatagoryRecyclerAdaptor catagoryRecyclerAdaptor =(CatagoryRecyclerAdaptor) catagoryRecyclerView.getAdapter();
                    catagoryRecyclerAdaptor.addCategory(category);

                    dialogInterface.dismiss();
                    displayCategoryItems(category);
            }
        });

        alertBuilder.create().show();
    }

    private void  displayCategoryItems(Category category){

        Intent categoryItemsIntent = new Intent(this,CategoryItemsActivity.class);
        categoryItemsIntent.putExtra(CATEGORY_OBJECT_KEY,category);
        startActivityForResult(categoryItemsIntent,MAIN_ACTIVITY_REQUEST_CODE_);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MAIN_ACTIVITY_REQUEST_CODE_&& resultCode == Activity.RESULT_OK){

            if(data != null){
                mCategoryManager.saveCategory((Category)data.getSerializableExtra(CATEGORY_OBJECT_KEY));
                updateCategories();
            }
        }
    }

    private void updateCategories() {

        ArrayList<Category> categories =  mCategoryManager.retrieveCategories();
        catagoryRecyclerView.setAdapter(new CatagoryRecyclerAdaptor(categories,this));

    }

    @Override
    public void categoryIsClick(Category category) {
        displayCategoryItems(category);
    }
}