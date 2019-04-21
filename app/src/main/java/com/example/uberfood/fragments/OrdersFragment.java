package com.example.uberfood.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.example.uberfood.R;
import com.example.uberfood.adapters.OrdersFragmentAdapter;
import com.example.uberfood.adapters.ViewPagerDeliveryAdapter;
import com.example.uberfood.models.PlacedOrder;
import com.example.uberfood.models.Restaurant;
import com.example.uberfood.utils.ConnectivityService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import static com.example.uberfood.utils.Constants.PLACED_ORDER_KEY;
import static com.example.uberfood.utils.Constants.RESTAURANT_KEY;

public class OrdersFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ProgressBar progressBar;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView ;
    LinearLayout llNoInternetConnexion , llResultsNotFound ;
    ScrollView scrollView ;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<PlacedOrder> listOfPlacedOrder = new ArrayList<>();



    public OrdersFragment() {
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

        View rootView = inflater.inflate(R.layout.fragment_orders, container, false);
        progressBar = rootView.findViewById(R.id.progressBarOrdersFragment);
        llNoInternetConnexion = rootView.findViewById(R.id.ll_no_internet_connexion_fragment_orders);
        llResultsNotFound = rootView.findViewById(R.id.ll_no_results_found_fragment_orders);
        scrollView = rootView.findViewById(R.id.scroll_view_fragment_orders);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyler_view_orders_fragment);
        initializeFragment();
        return rootView ;
    }


    public void initializeFragment() {




        if (ConnectivityService.isOnline(getContext())){
            scrollView.setVisibility(View.VISIBLE);
            llResultsNotFound.setVisibility(View.GONE);
            llNoInternetConnexion.setVisibility(View.GONE);


            getValuesFromBackend();

        } else {
            scrollView.setVisibility(View.GONE);
            llNoInternetConnexion.setVisibility(View.VISIBLE);
            llResultsNotFound.setVisibility(View.GONE);

        }



    }

    private void getValuesFromBackend() {

       progressBar.setVisibility(View.VISIBLE);

        db.collection(PLACED_ORDER_KEY)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.GONE);
                            for (final QueryDocumentSnapshot document : task.getResult()) {
                                Log.e("FB" , document.getId() + " => " + document.getData());




                                PlacedOrder placedOrder = document.toObject(PlacedOrder.class);
                                //Restaurant restaurant = new Gson().fromJson(document.getData().toString(), Restaurant.class);
                                Log.e("placedOrder values " , placedOrder.toString()+" !");
                                if (!listOfPlacedOrder.contains(placedOrder)){
                                    listOfPlacedOrder.add(placedOrder);
                                }

                                if (listOfPlacedOrder.size() == 0){
                                    scrollView.setVisibility(View.GONE);
                                    llResultsNotFound.setVisibility(View.VISIBLE);
                                    llNoInternetConnexion.setVisibility(View.GONE);

                                } else {

                                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                                    recyclerView.setLayoutManager(mLayoutManager);

                                    OrdersFragmentAdapter ordersFragmentAdapter = new OrdersFragmentAdapter(getContext() , listOfPlacedOrder);
                                    recyclerView.setAdapter(ordersFragmentAdapter);
                                }






                            }


                        } else {

                            progressBar.setVisibility(View.GONE);


                            Log.e("FB" ,"Error getting documents: ", task.getException());
                        }
                    }
                });



    }

}
