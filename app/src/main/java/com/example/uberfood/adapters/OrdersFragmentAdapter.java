package com.example.uberfood.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uberfood.R;
import com.example.uberfood.models.Restaurant;

import java.util.ArrayList;




public class OrdersFragmentAdapter extends RecyclerView.Adapter<OrdersFragmentAdapter.MyViewHolder> {


    Context context ;
    ArrayList<Restaurant> listOfRestaurants ;

    public OrdersFragmentAdapter(Context context, ArrayList<Restaurant> listOfRestaurants) {
        this.context = context;
        this.listOfRestaurants = listOfRestaurants;
    }


        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
            View item = null;


            item = LayoutInflater.from(context).inflate(R.layout.orders_item, parent, false);


            return new OrdersFragmentAdapter.MyViewHolder(item);
        }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    }



        @Override
        public int getItemCount () {
            //return listOfRestaurants.size();

            return 5;
        }


        public class MyViewHolder extends RecyclerView.ViewHolder {


            public MyViewHolder(View itemView) {
                super(itemView);


            }
        }
    }
