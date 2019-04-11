package com.example.uberfood.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uberfood.R;
import com.example.uberfood.models.Menu;
import com.example.uberfood.models.Restaurant;

import java.util.ArrayList;
import java.util.LinkedHashMap;




public class SearchHomeFragmentAdapter extends RecyclerView.Adapter<SearchHomeFragmentAdapter.MyViewHolder> {




    Context context ;
    ArrayList<Restaurant> listOfRestaurants = new ArrayList<>();


    public SearchHomeFragmentAdapter(Context context , ArrayList<Restaurant> listOfRestaurants ){
        this.listOfRestaurants.addAll(listOfRestaurants);
        this.context = context ;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.item_search_restaurant, parent, false);
        return new SearchHomeFragmentAdapter.MyViewHolder(item);
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {


        Restaurant restaurant = listOfRestaurants.get(position);
        holder.name.setText(restaurant.getName());








    }

    public void update(ArrayList<Restaurant> listOfRestaurants){

        listOfRestaurants.clear();
        this.listOfRestaurants = listOfRestaurants;

        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return listOfRestaurants.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{


        AppCompatTextView name;


        public MyViewHolder(View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.restaurant_search_name);


        }
    }
}
