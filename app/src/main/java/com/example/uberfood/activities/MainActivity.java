package com.example.uberfood.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.uberfood.R;
import com.example.uberfood.adapters.SearchActivityViewPager;
import com.example.uberfood.fragments.HomeFragment;
import com.example.uberfood.fragments.OrdersFragment;
import com.example.uberfood.fragments.ProfilFragment;
import com.example.uberfood.fragments.SearchFragment;

public class MainActivity extends AppCompatActivity {


    private HomeFragment homeFragment;
    private SearchFragment searchFragment ;
    private ProfilFragment profilFragment ;
    private OrdersFragment ordersFragment ;
    FrameLayout mainFrameLayout ;

    BottomNavigationView mainNavBar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        initializeFragments();


    }

    private void initializeFragments() {



        mainNavBar = (BottomNavigationView) findViewById(R.id.main_nav_bar);
        mainFrameLayout = findViewById(R.id.main_frame);
        homeFragment = new HomeFragment();
        searchFragment = new SearchFragment();
        profilFragment = new ProfilFragment();
        ordersFragment = new OrdersFragment();
        mainNavBar.getMenu().getItem(0).setChecked(true);
        setFragment(homeFragment);

        mainNavBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.main_nav_home) {

                    setFragment(homeFragment);
                    return  true ;

                }  else if (item.getItemId() == R.id.main_nav_search) {
                    setFragment(searchFragment);
                    return  true ;

                } else if (item.getItemId() == R.id.main_nav_profil) {
                    setFragment(profilFragment);
                    return  true ;

                } else if (item.getItemId() == R.id.main_nav_orders){
                    setFragment(ordersFragment);
                    return  true ;
                }
                return false ;
            }
        });

    }




    private void setFragment(Fragment fragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame , fragment);
        transaction.addToBackStack(null);

            transaction.commit();




    }


    @Override
    public void onBackPressed() {
        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 0){
            if (manager.getBackStackEntryCount() == 1){

                finish();
            }
            super.onBackPressed();
            Fragment currentFragment = manager.findFragmentById(R.id.main_frame);
            if (currentFragment instanceof  HomeFragment){
                mainNavBar.getMenu().getItem(0).setChecked(true);
                setFragment(homeFragment);
            } else if (currentFragment instanceof  ProfilFragment){
                mainNavBar.getMenu().getItem(3).setChecked(true);
            }else if (currentFragment instanceof SearchFragment){
                mainNavBar.getMenu().getItem(1).setChecked(true);
            } else if (currentFragment instanceof OrdersFragment){
                mainNavBar.getMenu().getItem(2).setChecked(true);

            }
        } else {

            super.onBackPressed();
        }

    }
}
