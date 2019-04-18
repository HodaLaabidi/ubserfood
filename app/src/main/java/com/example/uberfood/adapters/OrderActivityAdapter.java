package com.example.uberfood.adapters;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.transition.TransitionManager;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.util.Util;
import com.example.uberfood.R;
import com.example.uberfood.activities.MenuActivity;
import com.example.uberfood.activities.OrderActivity;
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
           holder.price.setText(menu.getPrice()*numberOfOrders + " DT");
                Log.e("from order adapter" , numberOfOrders + menu.getItem_name() + " !");
        holder.orderLabel.setText(menu.getItem_name());
        holder.numberOfOrders.setText("*"+numberOfOrders);
        holder.number.setText("*"+numberOfOrders);
        holder.llNumberOfOrders.bringToFront();
        (holder.llNumberOfOrders.getParent()).requestLayout();
        final Animation animFadeIn = AnimationUtils.loadAnimation(context,R.anim.fade_in);
        Animation animFadeOut = AnimationUtils.loadAnimation(context,R.anim.fade_out);
        holder.addOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*holder.llNumberOfOrders.setVisibility(View.GONE);
                holder.invisiblePrice.startAnimation(animFadeIn);
                holder.price.setVisibility(View.GONE);
                holder.number.setVisibility(View.VISIBLE);
                holder.number.startAnimation(animFadeIn);*/
                String number = holder.numberOfOrders.getText()+"";
                int NumberOrders =  Integer.parseInt(number.substring(1));
                NumberOrders++ ;
                Log.e("number" , NumberOrders+" !");
                holder.numberOfOrders.setText("*"+NumberOrders);
                holder.price.setText(menu.getPrice()*NumberOrders + " DT");
                holder.invisiblePrice.setText(menu.getPrice()*NumberOrders + " DT");
                holder.number.setText("*"+NumberOrders);
                Utils.price += menu.getPrice();
                notifyDataSetChanged();

                listOfOrderedMenu.put(menu , NumberOrders);
                Utils.listOfOrderedMenu.put(menu , NumberOrders);
                MenuActivity.priceText.setText(Utils.price + " DT");
                OrderActivity.calculateValues(Utils.price);
            }
        });

        holder.deleteOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String number = holder.numberOfOrders.getText()+"";
                int NumberOrders =  Integer.parseInt(number.substring(1));
                NumberOrders-- ;
                Utils.price -= menu.getPrice();


               if (NumberOrders == 0){

                   holder.itemView.animate().alpha(0f).setDuration(1000).start();
                   new Handler().postDelayed(new Runnable() {
                       @Override
                       public void run() {

                           listOfOrderedMenu.remove(menu);
                           Utils.listOfOrderedMenu.remove(menu);
                           holder.itemView.setVisibility(View.GONE);
                           notifyItemRemoved(position);
                           notifyItemRangeChanged(position, listOfOrderedMenu.size());
                           MenuActivity.priceText.setText(Utils.price + " DT");
                           OrderActivity.calculateValues(Utils.price);
                           //updateView();

                       }
                   }, 1000);

               } else {
                   listOfOrderedMenu.put(menu , NumberOrders);
                   Utils.listOfOrderedMenu.put(menu , NumberOrders);
                   notifyDataSetChanged();
                   MenuActivity.priceText.setText(Utils.price + " DT");
                   Log.e("number" , NumberOrders+" !");
                   holder.numberOfOrders.setText("*"+NumberOrders);
                   holder.price.setText(menu.getPrice()*NumberOrders + " DT");
                   holder.invisiblePrice.setText(menu.getPrice()*NumberOrders + " DT");
                   holder.number.setText("*"+NumberOrders);
                   OrderActivity.calculateValues(Utils.price);
               }

                // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


            }
        });



    }

    private void updateView() {
        this.listOfOrderedMenu.clear();
        this.listOfOrderedMenu.putAll(Utils.listOfOrderedMenu);
        notifyDataSetChanged();



    }

    @Override
    public int getItemCount() {
        return listOfOrderedMenu.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{


        AppCompatTextView orderLabel , numberOfOrders , number , price , invisiblePrice ;
        LinearLayout llNumberOfOrders ;
        ImageView addOrder , deleteOrder ;


        public MyViewHolder(View itemView){
            super(itemView);

            orderLabel = itemView.findViewById(R.id.order_label);
            numberOfOrders = itemView.findViewById(R.id.number_of_orders);
            addOrder = itemView.findViewById(R.id.add_order);
            deleteOrder = itemView.findViewById(R.id.delete_order);
            llNumberOfOrders = itemView.findViewById(R.id.ll_number_of_orders);
            number = itemView.findViewById(R.id.number_tv);
            price = itemView.findViewById(R.id.price_tv);
            invisiblePrice = itemView.findViewById(R.id.price_invisible);

        }
    }
}
