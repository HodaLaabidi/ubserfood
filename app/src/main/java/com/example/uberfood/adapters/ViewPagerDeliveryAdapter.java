package com.example.uberfood.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.uberfood.fragments.ItemViewPagerDeliveryFragment;
import com.example.uberfood.models.Restaurant;
import com.example.uberfood.utils.Utils;

import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.uberfood.utils.Constants.RESTAURANT_KEY;

public class ViewPagerDeliveryAdapter  extends FragmentStatePagerAdapter {
    Context context ;
    ArrayList<Restaurant> listOfRestaurants ;

    public ViewPagerDeliveryAdapter(FragmentManager fm ,Context context, ArrayList<Restaurant> listOfRestaurants) {
        super(fm);
        this.context = context;
        this.listOfRestaurants = listOfRestaurants ;
    }

    @Override
    public Fragment getItem(int position) {

        ItemViewPagerDeliveryFragment itemFragment  = new ItemViewPagerDeliveryFragment();


        Bundle args = new Bundle();
       String personJsonString = Utils.getGsonParser().toJson(listOfRestaurants.get(position));
        args.putString(RESTAURANT_KEY, personJsonString);
        itemFragment.setArguments(args);



        return  itemFragment ;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }


    @Override
    public int getCount() {
        return this.listOfRestaurants.size();
    }


}
