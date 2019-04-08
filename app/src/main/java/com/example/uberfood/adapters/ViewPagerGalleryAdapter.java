package com.example.uberfood.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.uberfood.R;
import com.example.uberfood.models.OnePhoto;

import java.util.ArrayList;

public class ViewPagerGalleryAdapter extends PagerAdapter {

    Context context ;
    ArrayList<OnePhoto> listOfPhotos;
    LayoutInflater inflater ;


    public ViewPagerGalleryAdapter(Context context , ArrayList<OnePhoto> listOfPhotos){
        this.context = context ;
        this.listOfPhotos = listOfPhotos ;
        inflater = LayoutInflater.from(context);

    }
    @Override
    public int getCount() {
        return listOfPhotos.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.one_photo_gallery_view_pager ,container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_photo_from_gallery_view_pager);
        Glide.with(context)
                .load(listOfPhotos.get(position).getUrl())
                .into(imageView);
        container.addView(view , 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }
}
