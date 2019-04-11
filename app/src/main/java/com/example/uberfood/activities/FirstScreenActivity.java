package com.example.uberfood.activities;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.uberfood.R;
import com.example.uberfood.adapters.CustomCarouselPageAdapter;


import static com.example.uberfood.adapters.CustomCarouselPageAdapter.FIRST_PAGE;

public class FirstScreenActivity extends AppCompatActivity {



    ViewPager carouselViewPager ;
    CustomCarouselPageAdapter customCarouselPageAdapter;
    AppCompatTextView signIn;
    LinearLayout searchButton ;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        initializeViews();

        carouselViewPager.setAdapter(customCarouselPageAdapter);
        carouselViewPager.setPageTransformer(false, customCarouselPageAdapter);

        carouselViewPager.setCurrentItem(1);


        carouselViewPager.setOffscreenPageLimit(3);

        carouselViewPager.setPageMargin(-400);

    }

    private void initializeViews() {

        signIn = findViewById(R.id.sign_in);

        carouselViewPager = findViewById(R.id.carousel_view_pager);
        searchButton = findViewById(R.id.search_button);

        customCarouselPageAdapter = new CustomCarouselPageAdapter(this, this.getSupportFragmentManager());





        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstScreenActivity.this , LoginActivity.class);
               startActivity(intent);
               finish();

            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent =  new Intent(FirstScreenActivity.this , MainActivity.class);
                startActivity(intent);

            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
