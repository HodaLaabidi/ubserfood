package com.example.uberfood.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.uberfood.R;
import com.example.uberfood.activities.RestaurantInformationsActivity;
import com.example.uberfood.models.Menu;
import com.example.uberfood.models.Restaurant;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import me.relex.circleindicator.CircleIndicator;


public class SearchHomeFragmentAdapter extends RecyclerView.Adapter<SearchHomeFragmentAdapter.MyViewHolder> {

    Context context ;
    static ArrayList<Restaurant> listOfRestaurants = new ArrayList<>();


    public SearchHomeFragmentAdapter(Context context , ArrayList<Restaurant> listOfRestaurants ) {
        this.listOfRestaurants.addAll(listOfRestaurants);
        this.context = context;
        Log.e("test", this.listOfRestaurants.size() + " !!");
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.item_search_restaurant, parent, false);
        Log.e("onCreateViewHolder" , " called");
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        if (listOfRestaurants.size() != 0){
            Log.e("test7"  , "ok !!!");
            final Restaurant restaurant = listOfRestaurants.get(position);
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent (context , RestaurantInformationsActivity.class);
                   if (restaurant.getId() != null){
                       if (restaurant.getId().trim() != ""){
                           Bundle bundle = new Bundle();
                           bundle.putString("id_restaurant" , restaurant.getId());
                           bundle.putString("from_search_layout_adapter" , "ok");
                           intent.putExtras(bundle);
                           context.startActivity(intent);
                       }
                   }

                }
            });

            if (restaurant.getLogo() != null){
                if (restaurant.getLogo() != ""){
                    Glide.with(context)
                            .load(restaurant.getLogo())
                            .into(myViewHolder.logo);
                }
            }
            Log.e("test" , position+"!");
            Log.e("test8"  , restaurant.getName()+" !!!");
            Log.e("test8" , restaurant.toString()+"!");
            myViewHolder.name.setText(restaurant.getName()+"");
        } else {
            Log.e("test8"  , "ok !!!");
        }
    }


    public void update(ArrayList<Restaurant> listOfRestaurants){

        listOfRestaurants.clear();
        this.listOfRestaurants = listOfRestaurants;

        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        Log.e("size=" , this.listOfRestaurants.size()+"!!!");
        return this.listOfRestaurants.size() ;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{


        AppCompatTextView name;
        CircularImageView logo;


        public MyViewHolder(View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.search_name_from_item_SR);
            logo = itemView.findViewById(R.id.image_item_search_restaurant);


        }
    }
}

