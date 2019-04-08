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
    LinkedHashMap<Menu , Integer> listOfOrderedMenu ;


    public OrderActivityAdapter(Context context , LinkedHashMap<Menu , Integer> listOfOrderedMenu){
        this.listOfOrderedMenu = listOfOrderedMenu;
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
        // this menu returns a null value i will keep it to next day to solve
        //this menu returns a null value but it can be the error from LinkedHashMap<Menu , Integer >
        // the solution is i will  debug it first and i will test if the problem was from inflating the item_order layout it can be a problem from displaying layout % layout_parent
        // i will take a LinkedHashList values and test them at first
        // second i will replace all the values in a static way

        Integer  numberOfOrders= (new ArrayList<Integer>(listOfOrderedMenu.values())).get(position);
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
