package com.example.uberfood.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.uberfood.R;
import com.example.uberfood.adapters.GalleryInformationsActivityAdapter;
import com.example.uberfood.adapters.ViewPagerGalleryAdapter;
import com.example.uberfood.models.OnePhoto;
import com.example.uberfood.models.Restaurant;
import com.example.uberfood.utils.CustomToast;
import com.example.uberfood.utils.ExpandableHeightGridView;
import com.example.uberfood.utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

import static com.example.uberfood.activities.MenuActivity.id_restaurant;
import static com.example.uberfood.utils.Constants.GALLERY_COLLECTION;
import static com.example.uberfood.utils.Constants.RESTAURANT_KEY;
import static com.example.uberfood.utils.Constants.countImg;

public class RestaurantInformationsActivity extends AppCompatActivity {

    private static final String TAG =  "RestaurantInfosActivity" ;
    static String id = "";
    String fromSearchLayout = "";
    private static int currentPage = 0 ;
    LinearLayout  arrowBack  , llImageSlider;
    ViewPager viewPager ;



    private  ArrayList<OnePhoto> galleryArrayList = new ArrayList<>();
    ExpandableHeightGridView gridView ;
    AppCompatTextView restaurantName  , price;
    AppCompatTextView more ;
    LinearLayout panierLayout;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ImageView logo ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_informations);

        initializeViews();



        setDynamicValues();

        setGalleryView();
    }

    private void setGalleryView() {

        // static values

   /*     galleryArrayList.add(new OnePhoto("https://firebasestorage.googleapis.com/v0/b/restaurant-app-ac6c5.appspot.com/o/Restaurantlogo%2F1.jpg?alt=media&token=2627e948-d010-4f00-ba5d-aa1416389008" , 1));
        galleryArrayList.add(new OnePhoto("https://firebasestorage.googleapis.com/v0/b/restaurant-app-ac6c5.appspot.com/o/Restaurantlogo%2F1.jpg?alt=media&token=2627e948-d010-4f00-ba5d-aa1416389008" , 2));
        galleryArrayList.add(new OnePhoto("https://firebasestorage.googleapis.com/v0/b/restaurant-app-ac6c5.appspot.com/o/Restaurantlogo%2F1.jpg?alt=media&token=2627e948-d010-4f00-ba5d-aa1416389008" , 3));
        galleryArrayList.add(new OnePhoto("https://firebasestorage.googleapis.com/v0/b/restaurant-app-ac6c5.appspot.com/o/Restaurantlogo%2F1.jpg?alt=media&token=2627e948-d010-4f00-ba5d-aa1416389008" , 4));*/


        // dynamic way
        if (id_restaurant.trim() != ""){
            db.collection(RESTAURANT_KEY).document(id_restaurant).collection(GALLERY_COLLECTION)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for ( QueryDocumentSnapshot document : task.getResult()) {
                                    Log.e("menu", document.getId() +document.getData() +" !");

                                    OnePhoto onePhoto = document.toObject(OnePhoto.class);

                                    if (!galleryArrayList.contains(onePhoto)){
                                        galleryArrayList.add(onePhoto);
                                        Log.e("test menu " , onePhoto.toString());

                                    }


                                }


                                gridView.setExpanded(true);
                                GalleryInformationsActivityAdapter galleryInformationsActivityAdapter = new GalleryInformationsActivityAdapter(RestaurantInformationsActivity.this,galleryArrayList );



                                gridView.setAdapter(galleryInformationsActivityAdapter);

                                more.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        setViewPagerGallery(galleryArrayList);

                                    }
                                });

                                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                        setViewPagerGallery(galleryArrayList);
                                    }
                                });
                            }
                        }});


        }


    }

    private void setViewPagerGallery(final ArrayList<OnePhoto> galleryArrayList) {

        if (llImageSlider.getVisibility() == View.VISIBLE){
            llImageSlider.setVisibility(View.GONE);
        }  else {
            llImageSlider.setVisibility(View.VISIBLE);

            Log.e("list of photos " , galleryArrayList.size() +" !");
            viewPager.setAdapter( new ViewPagerGalleryAdapter(RestaurantInformationsActivity.this , galleryArrayList));
            CircleIndicator indicator = findViewById(R.id.circle_indicator_gallery_view_pager);
            indicator.setViewPager(viewPager);
            new Handler();
            final Runnable update = new Runnable() {
                @Override
                public void run() {
                    if (currentPage == galleryArrayList.size()){
                        currentPage = 0 ;
                    }
                    viewPager.setCurrentItem(currentPage++ , true);
                }
            };
        }
    }

    private void setDynamicValues() {



         db.collection(RESTAURANT_KEY).document(id)
        .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Restaurant restaurant = document.toObject(Restaurant.class);
                        Glide.with(RestaurantInformationsActivity.this)
                                .load(restaurant.getLogo())
                                .into(logo);
                        restaurantName.setText(restaurant.getName());
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        Log.d(TAG , "restaurant object data " + restaurant.toString());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });


    }

    private void initializeViews() {
        panierLayout = findViewById(R.id.panier_from_infos_activity);
        more = findViewById(R.id.see_more_photos);
        id = getIntent().getExtras().getString("id_restaurant");
        viewPager = findViewById(R.id.view_pager_gallery);
        price = findViewById(R.id.price_from_informations_activity);
        llImageSlider = findViewById(R.id.ll_image_slider);
        arrowBack = findViewById(R.id.arrow_back_from_restaurant_infos_activity);
        logo = findViewById(R.id.image_item_delivery);
        restaurantName = findViewById(R.id.restaurant_name_informations_activity);
        gridView = findViewById(R.id.gv_from_infos_activity);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        price.setText(Utils.price + " DT");
         fromSearchLayout = getIntent().getExtras().getString("from_search_layout_adapter");



        panierLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                FirebaseAuth auth = FirebaseAuth.getInstance() ;
                final FirebaseUser firebaseUser = auth.getCurrentUser();
                if (firebaseUser == null) {
                    Intent intent = new Intent(RestaurantInformationsActivity.this , LoginActivity.class);
                    startActivity(intent);
                    finish();

                } else {

                    if (Utils.price != 0){
                        Intent intent = new Intent(RestaurantInformationsActivity.this, OrderActivity.class );
                        Bundle bundle = new Bundle();
                        bundle.putString("id_restaurant" , id_restaurant );
                        intent.putExtras(bundle);
                        startActivity(intent );
                    }  else {
                        new CustomToast(RestaurantInformationsActivity.this, getResources().getString(R.string.warning), getResources().getString(R.string.no_panel), R.drawable.warning_icon, CustomToast.WARNING).show();

                    }

                }

            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        countImg = 0 ;
    }

    @Override
    public void onBackPressed() {
       if (llImageSlider.getVisibility() == View.VISIBLE){
           llImageSlider.setVisibility(View.GONE);
       } else {
           finish();
       }



    }
}
