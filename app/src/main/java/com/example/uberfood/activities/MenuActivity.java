package com.example.uberfood.activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.uberfood.R;
import com.example.uberfood.adapters.MenuCategoriesAdapter;
import com.example.uberfood.models.Menu;
import com.kbeanie.multipicker.api.CameraImagePicker;
import com.kbeanie.multipicker.api.ImagePicker;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    RecyclerView recyclerViewCategories ;
    ArrayList<Menu> listOfMenus = new ArrayList<>();
    ImageView arrowBack ;
    ImagePicker imagePicker ;
    public static LinearLayout panierLayout ;
    CameraImagePicker cameraPicker ;
    public static AppCompatTextView priceText ;
    public static String price = "";
    private String pickerPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R
                .layout.activity_menu);

        initializeViews();
        getValuesFromServer();
        setViews();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

   



    private void getValuesFromServer() {

        // static way

        listOfMenus.add(new Menu("CHICKEN PIZZAS" , "CHICKEN SUPREME" , "Crilled chiken, green peppers , onions and mushrooms" , "$22.0" , R.drawable.chiken_pizza));
        listOfMenus.add(new Menu("CHICKEN PIZZAS" , "CHICKEN SUPREME" , "Crilled chiken, green peppers , onions and mushrooms" , "$22.0" , R.drawable.chiken_pizza));

        listOfMenus.add(new Menu("CHICKEN PIZZAS" , "CHICKEN SUPREME" , "Crilled chiken, green peppers , onions and mushrooms" , "$22.0" , R.drawable.chiken_pizza));

        listOfMenus.add(new Menu("CHICKEN PIZZAS" , "CHICKEN SUPREME" , "Crilled chiken, green peppers , onions and mushrooms" , "$22.0" , R.drawable.chiken_pizza));

        listOfMenus.add(new Menu("CHICKEN PIZZAS" , "CHICKEN SUPREME" , "Crilled chiken, green peppers , onions and mushrooms" , "$22.0" , R.drawable.chiken_pizza));

        listOfMenus.add(new Menu("CHICKEN PIZZAS" , "CHICKEN SUPREME" , "Crilled chiken, green peppers , onions and mushrooms" , "$22.0" , R.drawable.chiken_pizza));

        listOfMenus.add(new Menu("CHICKEN PIZZAS" , "CHICKEN SUPREME" , "Crilled chiken, green peppers , onions and mushrooms" , "$22.0" , R.drawable.chiken_pizza));

        listOfMenus.add(new Menu("CHICKEN PIZZAS" , "CHICKEN SUPREME" , "Crilled chiken, green peppers , onions and mushrooms" , "$22.0" , R.drawable.chiken_pizza));

    }

    private void setViews() {

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(MenuActivity.this);
        recyclerViewCategories.setLayoutManager(mLayoutManager);
        MenuCategoriesAdapter menuCategoriesAdapter = new MenuCategoriesAdapter(MenuActivity.this, listOfMenus);
        recyclerViewCategories.setAdapter(menuCategoriesAdapter);
    }

    private void initializeViews() {

        recyclerViewCategories = findViewById(R.id.recyler_view_menu_activity);
        arrowBack = findViewById(R.id.arrow_back_from_menu_activity);
        panierLayout = findViewById(R.id.panier);
        priceText = findViewById(R.id.price);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        panierLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, OrderActivity.class );
                startActivity(intent );
            }
        });





    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
