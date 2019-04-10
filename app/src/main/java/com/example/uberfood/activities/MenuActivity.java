package com.example.uberfood.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.uberfood.R;
import com.example.uberfood.adapters.MenuCategoriesAdapter;

import com.example.uberfood.factories.DialogBuilderFactory;
import com.example.uberfood.models.Menu;
import com.example.uberfood.models.Restaurant;
import com.example.uberfood.utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kbeanie.multipicker.api.CameraImagePicker;
import com.kbeanie.multipicker.api.ImagePicker;

import java.util.ArrayList;

import static com.example.uberfood.utils.Constants.MENU_COLLECTION;
import static com.example.uberfood.utils.Constants.RESTAURANT_KEY;

public class MenuActivity extends AppCompatActivity {

    RecyclerView recyclerViewCategories ;
    ArrayList<Menu> listOfMenus = new ArrayList<>();
    ImageView arrowBack ;
    ImagePicker imagePicker ;
    String id = "";
    public static LinearLayout panierLayout ;
    CameraImagePicker cameraPicker ;
    public static AppCompatTextView priceText  , restaurantName ;

    LinearLayout buttonInformations ;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String pickerPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R
                .layout.activity_menu);

        initializeViews();
        getValuesFromServer();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

   



    private void getValuesFromServer() {

        // dynamic method




        db.collection(RESTAURANT_KEY).document(id).collection(MENU_COLLECTION)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for ( QueryDocumentSnapshot document : task.getResult()) {
                                Log.e("menu", document.getId() +document.getData() +" !");

                                Menu menu = document.toObject(Menu.class);

                                if (!listOfMenus.contains(menu)){
                                    listOfMenus.add(menu);
                                    Log.e("menu object " , menu.toString());
                                }


                            }

                            setViews();
                        }
                    }});


        // static way

        /*listOfMenus.add(new Menu("CHICKEN PIZZAS" , "CHICKEN SUPREME" , "Crilled chiken, green peppers , onions and mushrooms" , "$22.0" , R.drawable.chiken_pizza));
        listOfMenus.add(new Menu("CHICKEN PIZZAS" , "CHICKEN SUPREME" , "Crilled chiken, green peppers , onions and mushrooms" , "$22.0" , R.drawable.chiken_pizza));

        listOfMenus.add(new Menu("CHICKEN PIZZAS" , "CHICKEN SUPREME" , "Crilled chiken, green peppers , onions and mushrooms" , "$22.0" , R.drawable.chiken_pizza));

        listOfMenus.add(new Menu("CHICKEN PIZZAS" , "CHICKEN SUPREME" , "Crilled chiken, green peppers , onions and mushrooms" , "$22.0" , R.drawable.chiken_pizza));

        listOfMenus.add(new Menu("CHICKEN PIZZAS" , "CHICKEN SUPREME" , "Crilled chiken, green peppers , onions and mushrooms" , "$22.0" , R.drawable.chiken_pizza));

        listOfMenus.add(new Menu("CHICKEN PIZZAS" , "CHICKEN SUPREME" , "Crilled chiken, green peppers , onions and mushrooms" , "$22.0" , R.drawable.chiken_pizza));

        listOfMenus.add(new Menu("CHICKEN PIZZAS" , "CHICKEN SUPREME" , "Crilled chiken, green peppers , onions and mushrooms" , "$22.0" , R.drawable.chiken_pizza));

        listOfMenus.add(new Menu("CHICKEN PIZZAS" , "CHICKEN SUPREME" , "Crilled chiken, green peppers , onions and mushrooms" , "$22.0" , R.drawable.chiken_pizza));*/

    }

    private void setViews() {

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(MenuActivity.this);
        recyclerViewCategories.setLayoutManager(mLayoutManager);
        MenuCategoriesAdapter menuCategoriesAdapter = new MenuCategoriesAdapter(MenuActivity.this, listOfMenus);
        recyclerViewCategories.setAdapter(menuCategoriesAdapter);
    }

    private void initializeViews() {

         id = getIntent().getExtras().getString("id_restaurant");
         restaurantName = findViewById(R.id.name_restaurant_from_menu_activity);
        recyclerViewCategories = findViewById(R.id.recyler_view_menu_activity);
        buttonInformations = findViewById(R.id.button_restaurant_informations);
        arrowBack = findViewById(R.id.arrow_back_from_menu_activity);
        panierLayout = findViewById(R.id.panier);
        priceText = findViewById(R.id.price);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        restaurantName.setText(getIntent().getExtras().getString("name_restaurant"));

        panierLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.price != 0){
                    Intent intent = new Intent(MenuActivity.this, OrderActivity.class );
                    Bundle bundle = new Bundle();
                    bundle.putString("id_restaurant" , id );
                    intent.putExtras(bundle);
                    startActivity(intent );
                }
            }
        });

        buttonInformations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MenuActivity.this , RestaurantInformationsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id_restaurant" , id );
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });





    }


    @Override
    public void onBackPressed() {

        if (Utils.price == 0 ){
            finish();
        } else {
            DialogBuilderFactory.showDialog(MenuActivity.this);
        }



    }
}
