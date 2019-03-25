package com.example.uberfood.fragments;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.uberfood.R;
import com.example.uberfood.utils.CustomLinearLayout;

public class CustomCarouselFragment extends Fragment {



        public static Fragment newInstance(Activity context, int position, float scale) {
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            bundle.putFloat("scale", scale);
            return Fragment.instantiate(context, CustomCarouselFragment.class.getName(), bundle);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            if (container == null) {
                return null;
            }

            LinearLayout linearLayout = (LinearLayout)
                    inflater.inflate(R.layout.item_carousel, container, false);

            int position = this.getArguments().getInt("position");


            CustomLinearLayout root = (CustomLinearLayout) linearLayout.findViewById(R.id.item_root);
            ImageView imageCarousel = linearLayout.findViewById(R.id.image_item_carousel);


            float scale = this.getArguments().getFloat("scale");
            root.setScaleBoth(scale);

            return linearLayout;

    }
}
