package com.example.uberfood.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.example.uberfood.R;
import com.example.uberfood.models.OnePhoto;
import com.example.uberfood.models.Photo;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.ArrayList;



public class GalleryInformationsActivityAdapter extends BaseAdapter {

    ImageView ivPhoto;
    private ArrayList<OnePhoto> galleryArrayList;
    ProgressBar progressBar;
    Context context ;

    public GalleryInformationsActivityAdapter(Context context, ArrayList<OnePhoto> galleryArrayList) {
        this.context = context;
        this.galleryArrayList=galleryArrayList;


    }

    @Override
    public int getCount() {
        return galleryArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return galleryArrayList.get(position);
    }

    @Override
    // add a new item to string with id not from firebase

    public long getItemId(int position) {
        return galleryArrayList.get(position).getId_ad();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_photo_menu, parent, false);
        ivPhoto = view.findViewById(R.id.iv_photo);
        progressBar = view.findViewById(R.id.progressBarOnePhotoMenu);

        if(galleryArrayList.size()!=0)
        {
            progressBar.setVisibility(View.GONE);
            Log.e("test1" , galleryArrayList.get(position).toString());
            Log.e("test2" , galleryArrayList.get(position).getPhoto().toString());
            Log.e("test3" ,galleryArrayList.get(position).getId_ad()+"!" );
            Log.e("test4" ,galleryArrayList.get(position).getPhoto().values().toArray()[0] +"!!");



            Glide.with(context)
                    .load(galleryArrayList.get(position).getPhoto().values().toArray()[0])
                    .into(ivPhoto);

            //ivPhoto.setImageUrlWithoutBorderWithoutResizing(imageGalerieArrayList.get(position).getImage(),R.drawable.empty_business_photo,progressBar);

        }

        return view;
    }

    public void refresh(Context context, ArrayList<OnePhoto> galleryArrayList)
    {
        this.context = context;
        this.galleryArrayList= galleryArrayList;
        notifyDataSetChanged();
    }







}
