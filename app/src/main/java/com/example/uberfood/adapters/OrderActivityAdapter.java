package com.example.uberfood.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.uberfood.R;
import com.example.uberfood.activities.MenuActivity;
import com.example.uberfood.models.Menu;
import com.example.uberfood.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;


public class OrderActivityAdapter extends RecyclerView.Adapter<OrderActivityAdapter.MyViewHolder> {




    Context context ;
    HashMap<Menu , Integer> listOfOrderedMenu = new LinkedHashMap<>() ;


    public OrderActivityAdapter(Context context , HashMap<Menu , Integer> listOfOrderedMenu){
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


        final Menu menu = (new ArrayList<Menu>(listOfOrderedMenu.keySet())).get(position);

        final Integer  numberOfOrders= (new ArrayList<Integer>(listOfOrderedMenu.values())).get(position);
        Log.e("from order adapter" , numberOfOrders + menu.getItem_name() + " !");
        holder.orderLabel.setText(menu.getItem_name());
        holder.numberOfOrders.setText("*"+numberOfOrders);
        holder.llNumberOfOrders.bringToFront();
        (holder.llNumberOfOrders.getParent()).requestLayout();
        holder.addOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = holder.numberOfOrders.getText()+"";
                int NumberOrders =  Integer.parseInt(number.substring(1));
                NumberOrders++ ;
                Log.e("number" , NumberOrders+" !");
                holder.numberOfOrders.setText("*"+NumberOrders);
                Utils.price += menu.getPrice();
                notifyDataSetChanged();

                listOfOrderedMenu.put(menu , NumberOrders);
                Utils.listOfOrderedMenu.put(menu , NumberOrders);
                MenuActivity.priceText.setText(Utils.price + " DT");
            }
        });

        holder.deleteOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String number = holder.numberOfOrders.getText()+"";
                int NumberOrders =  Integer.parseInt(number.substring(1));
                NumberOrders-- ;
                Utils.price -= menu.getPrice();
                listOfOrderedMenu.put(menu , NumberOrders);
                Utils.listOfOrderedMenu.put(menu , NumberOrders);
                notifyDataSetChanged();
                MenuActivity.priceText.setText(Utils.price + " DT");
                Log.e("number" , NumberOrders+" !");
                holder.numberOfOrders.setText("*"+NumberOrders);

            }
        });



    }

    @Override
    public int getItemCount() {
        return listOfOrderedMenu.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{


        AppCompatTextView orderLabel , numberOfOrders ;
        LinearLayout llNumberOfOrders ;
        ImageView addOrder , deleteOrder ;


        public MyViewHolder(View itemView){
            super(itemView);

            orderLabel = itemView.findViewById(R.id.order_label);
            numberOfOrders = itemView.findViewById(R.id.number_of_orders);
            addOrder = itemView.findViewById(R.id.add_order);
            deleteOrder = itemView.findViewById(R.id.delete_order);
            llNumberOfOrders = itemView.findViewById(R.id.ll_number_of_orders);

        }
    }
}
