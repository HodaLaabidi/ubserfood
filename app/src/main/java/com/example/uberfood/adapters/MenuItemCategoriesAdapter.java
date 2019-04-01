package com.example.uberfood.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.uberfood.R;
import com.example.uberfood.activities.MenuActivity;
import com.example.uberfood.activities.OrderActivity;
import com.example.uberfood.models.Menu;

import java.util.ArrayList;




public class MenuItemCategoriesAdapter extends RecyclerView.Adapter<MenuItemCategoriesAdapter.MyViewHolder> {



    ArrayList<Menu> listOfMenus ;
    Context context ;


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
        final  Menu menu = listOfMenus.get(position);
        holder.menuImage.setImageResource(menu.getImage());
        holder.label.setText(menu.getLabel());
        holder.description.setText(menu.getDescription());
        holder.price.setText(menu.getPrice());
        holder.order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* float itemPrice = Float.parseFloat(holder.price.getText().toString());
                float panierPrice = Float.parseFloat(MenuActivity.priceText.getText().toString());
                float finalPrice = itemPrice + panierPrice ;
                MenuActivity.priceText.setText(finalPrice+"");*/


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


