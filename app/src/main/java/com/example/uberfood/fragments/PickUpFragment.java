package com.example.uberfood.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.uberfood.R;
import com.example.uberfood.adapters.ViewPagerDeliveryAdapter;
import com.example.uberfood.models.Restaurant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.ArrayList;

import static com.example.uberfood.utils.Constants.RESTAURANT_KEY;

public class PickUpFragment extends Fragment implements DiscreteScrollView.OnItemChangedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ProgressBar progressBar ;
    FirebaseAuth auth ;
    String userId ;
    ViewPager viewPager ;
    private ArrayList<Restaurant> listOfRestaurants = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ViewPagerDeliveryAdapter adapter ;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

   // private DeliveryFragment.OnFragmentInteractionListener mListener;

    public PickUpFragment() {
        // Required empty public constructor
    }

    public static PickUpFragment newInstance(String param1, String param2) {
        PickUpFragment fragment = new PickUpFragment();
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
        // Inflate the layout for this fragment

        final View rootView = inflater.inflate(R.layout.fragment_pick_up, container, false);
        progressBar = rootView.findViewById(R.id.progressBarPickUpFragment);

        auth = FirebaseAuth.getInstance() ;
        final FirebaseUser firebaseUser = auth.getCurrentUser();
        //userId = firebaseUser.getUid();
        progressBar.setVisibility(View.VISIBLE);

        db.collection(RESTAURANT_KEY)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.GONE);
                            for (final QueryDocumentSnapshot document : task.getResult()) {
                                Log.e("FB" , document.getId() + " => " + document.getData());




                                Restaurant restaurant = document.toObject(Restaurant.class);
                                //Restaurant restaurant = new Gson().fromJson(document.getData().toString(), Restaurant.class);
                                Log.e("restaurant values " , restaurant.toString()+" !");

                                if (restaurant.isPick_up()){
                                    listOfRestaurants.add(restaurant) ;
                                    Log.e("restaurants pickup" , restaurant.toString()+" !");
                                }


                                adapter = new ViewPagerDeliveryAdapter(getFragmentManager() , getContext() , listOfRestaurants);

                                viewPager = rootView.findViewById(R.id.viewPagerPickUp);
                                viewPager.setAdapter( adapter);
                                viewPager.setPadding(60, 0 , 60 ,50);
                                viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                    @Override
                                    public void onPageScrolled(int i, float v, int i1) {
                                        adapter.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onPageSelected(int i) {

                                    }

                                    @Override
                                    public void onPageScrollStateChanged(int i) {


                                    }
                                });


                            }


                        } else {

                            progressBar.setVisibility(View.GONE);

                            Log.e("FB" ,"Error getting documents: ", task.getException());
                        }
                    }
                });






        return rootView ;
    }

   /* // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/



    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {

        //int positionInDataSet = infiniteAdapter.getRealPosition(adapterPosition);
        // onItemChanged(data.get(positionInDataSet));
        adapter.notifyDataSetChanged();

    }


}
