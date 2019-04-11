package com.example.uberfood.fragments;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
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
import com.example.uberfood.models.User;
import com.example.uberfood.utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import static com.example.uberfood.utils.Constants.MENU_COLLECTION;
import static com.example.uberfood.utils.Constants.RESTAURANT_KEY;
import static com.example.uberfood.utils.Constants.USER_COLLECTION;


public class DeliveryFragment extends Fragment implements DiscreteScrollView.OnItemChangedListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ViewPager viewPager;
    ProgressBar progressBar ;
    private ArrayList<Restaurant> listOfRestaurants = new ArrayList<>();
    FirebaseAuth auth ;
    static HashMap<String,String> MenuReferences  = new HashMap<String, String>();
   // ViewPagerDeliveryAdapter adapter ;
   ViewPagerDeliveryAdapter adapter ;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
   String userId ;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    private InfiniteScrollAdapter infiniteAdapter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //private OnFragmentInteractionListener mListener;

    public DeliveryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeliveryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DeliveryFragment newInstance(String param1, String param2) {
        DeliveryFragment fragment = new DeliveryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment ;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);





    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

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

        final View rootView = inflater.inflate(R.layout.fragment_delivery, container, false);
        progressBar = rootView.findViewById(R.id.progressBarDeliveryFragment);

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
                                if (!listOfRestaurants.contains(restaurant)){
                                    listOfRestaurants.add(restaurant);
                                }


                                adapter = new ViewPagerDeliveryAdapter(getFragmentManager() , getContext() , listOfRestaurants);

                                viewPager = rootView.findViewById(R.id.viewPager);
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

    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {

        int positionInDataSet = infiniteAdapter.getRealPosition(adapterPosition);
       // onItemChanged(data.get(positionInDataSet));
        adapter.notifyDataSetChanged();

    }
}
