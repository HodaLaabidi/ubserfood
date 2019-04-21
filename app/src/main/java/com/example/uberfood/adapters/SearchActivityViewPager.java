package com.example.uberfood.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.uberfood.fragments.DeliveryFragment;
import com.example.uberfood.fragments.PickUpFragment;

public class SearchActivityViewPager  extends FragmentStatePagerAdapter {


    int numberOfPacks ;


    public  SearchActivityViewPager (FragmentManager fm , int numberOfPacks){
        super(fm);
        this.numberOfPacks = numberOfPacks;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0:
                DeliveryFragment deliveryFragment = new DeliveryFragment();
                return  deliveryFragment;
           /* case 1:
                PickUpFragment pickUpFragment = new PickUpFragment();
                return pickUpFragment;*/
        }
        return null;
    }

    @Override
    public int getCount() {
        return numberOfPacks;
    }

}
