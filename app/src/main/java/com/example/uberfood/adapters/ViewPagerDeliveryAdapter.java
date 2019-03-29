package com.example.uberfood.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uberfood.R;
import com.example.uberfood.fragments.ItemViewPagerDeliveryFragment;

public class ViewPagerDeliveryAdapter  extends FragmentStatePagerAdapter {
    Context context ;

    public ViewPagerDeliveryAdapter(FragmentManager fm ,Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int i) {
        return  new ItemViewPagerDeliveryFragment();
    }


    @Override
    public int getCount() {
        return 5;
    }


}
