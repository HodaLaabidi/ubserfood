package com.example.uberfood.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.uberfood.R;
import com.example.uberfood.adapters.MenuItemCategoriesAdapter;
import com.example.uberfood.adapters.OrderActivityAdapter;
import com.example.uberfood.adapters.OrdersFragmentAdapter;
import com.example.uberfood.models.Menu;
import com.example.uberfood.models.Order;
import com.example.uberfood.models.Restaurant;
import com.example.uberfood.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class OrderActivity extends AppCompatActivity {

    LinearLayout arrowBack ;
    LinearLayout cancel , ok ;
    RecyclerView recyclerView;
    LinkedHashMap<Menu, Integer> listOfOrderedMenu = new LinkedHashMap<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initializeViews();

    }

    private void initializeViews() {

        arrowBack = findViewById(R.id.return_icon_order_activity);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        cancel = findViewById(R.id.activity_order_cancel);
        ok = findViewById(R.id.activity_order_ok);
        recyclerView = findViewById(R.id.recycler_view_orders);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        LinearLayoutManager mLayoutManager = new LinearLayoutManager(OrderActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);

        OrderActivityAdapter orderActivityAdapter = new OrderActivityAdapter(OrderActivity.this ,  Utils.listOfOrderedMenu) ;
        recyclerView.setAdapter(orderActivityAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
