package com.example.uberfood.fragments;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uberfood.R;
import com.example.uberfood.activities.MenuActivity;
import com.example.uberfood.adapters.OrdersFragmentAdapter;
import com.example.uberfood.models.Restaurant;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;


public class ItemViewPagerDeliveryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView ;
    CircularImageView imageRestaurant ;
    AppCompatTextView nameRestaurant ;


    public ItemViewPagerDeliveryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }


    public static OrdersFragment newInstance(String param1, String param2) {
        OrdersFragment fragment = new OrdersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.item_view_pager_delivery, container, false);
        imageRestaurant = rootView.findViewById(R.id.image_item_delivery);
        nameRestaurant = rootView.findViewById(R.id.restaurant_name_delivery_item);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , MenuActivity.class);
                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View , String>(imageRestaurant,"image_item_delivery");
                pairs[1] = new Pair<View , String>(nameRestaurant,"restaurant_name_tansition");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity() , pairs);
                startActivity(intent, options.toBundle());
            }
        });
        return rootView ;
    }



}
