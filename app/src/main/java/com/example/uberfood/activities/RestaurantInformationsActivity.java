package com.example.uberfood.activities;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.uberfood.R;
import com.example.uberfood.adapters.GalleryInformationsActivityAdapter;
import com.example.uberfood.adapters.ViewPagerGalleryAdapter;
import com.example.uberfood.models.Menu;
import com.example.uberfood.models.OnePhoto;
import com.example.uberfood.models.Restaurant;
import com.example.uberfood.utils.ExpandableHeightGridView;
import com.example.uberfood.utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

import static com.example.uberfood.utils.Constants.MENU_COLLECTION;
import static com.example.uberfood.utils.Constants.RESTAURANT_KEY;

public class RestaurantInformationsActivity extends AppCompatActivity {

    private static final String TAG =  "RestaurantInfosActivity" ;
    static String id = "";
    private static int currentPage = 0 ;
    LinearLayout  arrowBack  , llImageSlider;
    ViewPager viewPager ;



    private  ArrayList<OnePhoto> galleryArrayList = new ArrayList<>();
    ExpandableHeightGridView gridView ;
    AppCompatTextView restaurantName  , price;

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

        galleryArrayList.add(new OnePhoto("https://firebasestorage.googleapis.com/v0/b/restaurant-app-ac6c5.appspot.com/o/Restaurantlogo%2F1.jpg?alt=media&token=2627e948-d010-4f00-ba5d-aa1416389008" , 1));
        galleryArrayList.add(new OnePhoto("https://firebasestorage.googleapis.com/v0/b/restaurant-app-ac6c5.appspot.com/o/Restaurantlogo%2F1.jpg?alt=media&token=2627e948-d010-4f00-ba5d-aa1416389008" , 2));
        galleryArrayList.add(new OnePhoto("https://firebasestorage.googleapis.com/v0/b/restaurant-app-ac6c5.appspot.com/o/Restaurantlogo%2F1.jpg?alt=media&token=2627e948-d010-4f00-ba5d-aa1416389008" , 3));
        galleryArrayList.add(new OnePhoto("https://firebasestorage.googleapis.com/v0/b/restaurant-app-ac6c5.appspot.com/o/Restaurantlogo%2F1.jpg?alt=media&token=2627e948-d010-4f00-ba5d-aa1416389008" , 4));

        gridView.setExpanded(true);
        GalleryInformationsActivityAdapter galleryInformationsActivityAdapter = new GalleryInformationsActivityAdapter(RestaurantInformationsActivity.this,galleryArrayList );



        gridView.setAdapter(galleryInformationsActivityAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
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
        });


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
