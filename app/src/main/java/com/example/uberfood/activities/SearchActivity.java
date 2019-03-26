package com.example.uberfood.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.uberfood.R;
import com.example.uberfood.adapters.SearchActivityViewPager;

import lib.kingja.switchbutton.SwitchMultiButton;

public class SearchActivity extends AppCompatActivity {

    ViewPager viewPager ;
    SwitchMultiButton switchMultiButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initializeViews();
        setClickableLayouts();
    }

    private void setClickableLayouts() {

        switchMultiButton.setOnSwitchListener(new SwitchMultiButton.OnSwitchListener() {
            @Override
            public void onSwitch(int position, String tabText) {


                if (position == 0){


                    viewPager.setCurrentItem(0);

                } else {
                    viewPager.setCurrentItem(1);
                }

            }
        });


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {


                if (position == 0){

                    switchMultiButton.clearSelection();
                    switchMultiButton.setSelectedTab(0);


                } else {
                    switchMultiButton.clearSelection();
                    switchMultiButton.setSelectedTab(1);




                }


            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initializeViews() {

       viewPager = findViewById(R.id.search_activity_view_pager);
       switchMultiButton = findViewById(R.id.switch_multi_button);
       viewPager.setCurrentItem(0);
       viewPager.setAdapter(new SearchActivityViewPager(getSupportFragmentManager() , 2));
    }
}
