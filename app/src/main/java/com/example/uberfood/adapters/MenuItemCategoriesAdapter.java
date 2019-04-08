package com.example.uberfood.adapters;

import android.content.Context;
import android.content.Intent;
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
import com.example.uberfood.activities.MenuActivity;
import com.example.uberfood.activities.OrderActivity;
import com.example.uberfood.models.Menu;
import com.example.uberfood.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;


public class MenuItemCategoriesAdapter extends RecyclerView.Adapter<MenuItemCategoriesAdapter.MyViewHolder> {



    ArrayList<Menu> listOfMenus ;
    Context context ;
    Integer number = 0 ;
    public static LinkedHashMap<Menu , Integer> listOfOrderedMenu = new LinkedHashMap<>() ;


    public MenuItemCategoriesAdapter(Context context , ArrayList<Menu> listOfMenus){
        this.listOfMenus = listOfMenus;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.item_category_menu, parent, false);
        return new MenuItemCategoriesAdapter.MyViewHolder(item);
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
         number = 0 ;

        final  Menu menu = listOfMenus.get(position);
        Log.e("menu from adapter" , menu.toString());
        //holder.menuImage.setImageResource(menu.getImage());
        holder.label.setText(menu.getItem_name());
        holder.description.setText(menu.getDescription());
        holder.price.setText(menu.getPrice()+"");

        holder.order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.price += menu.getPrice();
                MenuActivity.priceText.setText(Utils.price + " DT");
                number ++;
                notifyDataSetChanged();
                listOfOrderedMenu.put(menu , number );


            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfMenus.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{


      ImageView menuImage ;
      AppCompatTextView label ;
      AppCompatTextView description ;
      AppCompatTextView price ;
      ImageView order ;
        public MyViewHolder(View itemView){
            super(itemView);
            menuImage = itemView.findViewById(R.id.item_category_menu_image);
            label = itemView.findViewById(R.id.item_menu_label);
            description = itemView.findViewById(R.id.item_menu_description);
            price = itemView.findViewById(R.id.item_menu_price);
            order = itemView.findViewById(R.id.add_icon);

        }
    }
}


