package com.example.uberfood.adapters;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.uberfood.R;
import com.example.uberfood.fragments.CustomCarouselFragment;
import com.example.uberfood.utils.CustomLinearLayout;

public class CustomCarouselPageAdapter extends FragmentPagerAdapter implements ViewPager.PageTransformer {


        public final static float BIG_SCALE = 1.0f;
    public final static int PAGES = 3;
    public final static int FIRST_PAGE = 0  ;
        public final static float SMALL_SCALE = 0.7f;
        public final static float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;

        private Activity mContext;
        private FragmentManager mFragmentManager;
        private float mScale;

        public CustomCarouselPageAdapter(Activity context, FragmentManager fragmentManager) {
            super(fragmentManager);
            this.mFragmentManager = fragmentManager;
            this.mContext = context;
        }

        @Override
        public Fragment getItem(int position) {


            // make the first mViewPager bigger than others
            if (position == FIRST_PAGE)
                mScale = BIG_SCALE;
            else
                mScale = SMALL_SCALE;

            return CustomCarouselFragment.newInstance(mContext, position, mScale);
        }

        @Override
        public int getCount() {
            return PAGES;
        }

        @Override
        public void transformPage(View page, float position) {
            CustomLinearLayout myLinearLayout = (CustomLinearLayout) page.findViewById(R.id.item_root);
            float scale = BIG_SCALE;
            if (position > 0) {
                scale = scale - position * DIFF_SCALE;
            } else {
                scale = scale + position * DIFF_SCALE;
            }
            if (scale < 0) scale = 0;
            myLinearLayout.setScaleBoth(scale);
        }
    }

