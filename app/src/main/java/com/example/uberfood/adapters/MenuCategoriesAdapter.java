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
import com.example.uberfood.activities.MenuActivity;
import com.example.uberfood.models.Menu;

import java.util.ArrayList;


public class MenuCategoriesAdapter extends RecyclerView.Adapter<MenuCategoriesAdapter.MyViewHolder> {



    ArrayList<Menu> listOfMenus ;
    Context context ;


    public MenuCategoriesAdapter(Context context , ArrayList<Menu> listOfMenus){
        this.listOfMenus = listOfMenus;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.item_menu, parent, false);
        return new MenuCategoriesAdapter.MyViewHolder(item);
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {


        final  Menu menu = listOfMenus.get(position);
        holder.category.setText(menu.getCategory());
        Log.e("menu from adapter" , menu.toString());
        holder.recyclerCategoryItem.setVisibility(View.GONE);
        holder.itemMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.recyclerCategoryItem.getVisibility() == View.VISIBLE ){
                    holder.recyclerCategoryItem.setVisibility(View.GONE);
                    holder.expandImageItemMenu.setImageResource(R.drawable.expand_less);
                } else {
                    holder.recyclerCategoryItem.setVisibility(View.VISIBLE);
                    holder.expandImageItemMenu.setImageResource(R.drawable.expand_more);
                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
                    holder.recyclerCategoryItem.setLayoutManager(mLayoutManager);
                    MenuItemCategoriesAdapter menuCategoriesAdapter = new MenuItemCategoriesAdapter(context, listOfMenus);
                    holder.recyclerCategoryItem.setAdapter(menuCategoriesAdapter);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return listOfMenus.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        AppCompatTextView category ;
        LinearLayout expandLayoutItemMenu;
        ImageView expandImageItemMenu;
        LinearLayout itemMenu ;
        RecyclerView recyclerCategoryItem ;


        public MyViewHolder(View itemView){
            super(itemView);

           category = itemView.findViewById(R.id.category_item_menu);
            expandLayoutItemMenu = itemView.findViewById(R.id.expand_layout_item_menu);
            expandImageItemMenu = itemView.findViewById(R.id.expand_image_item_menu);
            itemMenu = itemView.findViewById(R.id.item_menu);
            recyclerCategoryItem = itemView.findViewById(R.id.recyler_view_category_item);
        }
    }
}

