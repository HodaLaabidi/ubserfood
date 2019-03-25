package com.example.uberfood.activities;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.uberfood.R;
import com.example.uberfood.adapters.CustomCarouselPageAdapter;


import static com.example.uberfood.adapters.CustomCarouselPageAdapter.FIRST_PAGE;

public class FirstScreenActivity extends AppCompatActivity {



    ViewPager carouselViewPager ;
    CustomCarouselPageAdapter customCarouselPageAdapter;
    AppCompatTextView signIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        initializeViews();

        carouselViewPager.setAdapter(customCarouselPageAdapter);
        carouselViewPager.setPageTransformer(false, customCarouselPageAdapter);

        carouselViewPager.setCurrentItem(FIRST_PAGE);


        carouselViewPager.setOffscreenPageLimit(3);

        carouselViewPager.setPageMargin(-400);

    }

    private void initializeViews() {
        carouselViewPager = findViewById(R.id.carousel_view_pager);

        customCarouselPageAdapter = new CustomCarouselPageAdapter(this, this.getSupportFragmentManager());


        signIn = findViewById(R.id.sign_in);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstScreenActivity.this , LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
