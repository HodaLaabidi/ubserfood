package com.example.uberfood.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.uberfood.R;
import com.example.uberfood.models.Menu;
import com.example.uberfood.models.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;


public class OrderActivityAdapter extends RecyclerView.Adapter<OrderActivityAdapter.MyViewHolder> {




    Context context ;
    LinkedHashMap<Menu , Integer> listOfOrderedMenu = new LinkedHashMap<>() ;


    public OrderActivityAdapter(Context context , LinkedHashMap<Menu , Integer> listOfOrderedMenu){
        this.listOfOrderedMenu.putAll(listOfOrderedMenu);
        this.context = context ;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
        return new OrderActivityAdapter.MyViewHolder(item);
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {


        Menu menu = (new ArrayList<Menu>(listOfOrderedMenu.keySet())).get(position);

        Integer  numberOfOrders= (new ArrayList<Integer>(listOfOrderedMenu.values())).get(position);
        Log.e("from order adapter" , numberOfOrders + menu.getItem_name() + " !");
        holder.orderLabel.setText(menu.getItem_name());
        holder.numberOfOrders.setText("*"+numberOfOrders);



    }

    @Override
    public int getItemCount() {
        return listOfOrderedMenu.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{


        AppCompatTextView orderLabel , numberOfOrders ;


        public MyViewHolder(View itemView){
            super(itemView);

            orderLabel = itemView.findViewById(R.id.order_label);
            numberOfOrders = itemView.findViewById(R.id.number_of_orders);

        }
    }
}
