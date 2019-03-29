package com.example.uberfood.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uberfood.R;
import com.example.uberfood.utils.Utils;
import com.gigamole.infinitecycleviewpager.VerticalInfiniteCycleViewPager;

import static com.example.uberfood.utils.Utils.setupItem;

public class HorizontalPagerAdapter extends PagerAdapter {

    private final Utils.LibraryObject[] LIBRARIES = new Utils.LibraryObject[]{
            new Utils.LibraryObject(
                    R.drawable.city,
                    "City"
            ),
            new Utils.LibraryObject(
                    R.drawable.orders,
                    "Orders"
            ),
            new Utils.LibraryObject(
                    R.drawable.slider_1,
                    "Food"
            ),
            new Utils.LibraryObject(
                    R.drawable.slider_2,
                    "Restaurants"
            )
    };

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private boolean mIsTwoWay;

    public HorizontalPagerAdapter(final Context context, final boolean isTwoWay) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mIsTwoWay = isTwoWay;
    }

    @Override
    public int getCount() {
        return mIsTwoWay ? 6 : LIBRARIES.length;
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view;

            view = mLayoutInflater.inflate(R.layout.item_delivery_view_pager, container, false);
            setupItem(view, LIBRARIES[position]);


        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        container.removeView((View) object);
    }
}