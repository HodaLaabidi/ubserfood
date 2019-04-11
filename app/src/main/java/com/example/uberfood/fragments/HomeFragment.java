package com.example.uberfood.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.uberfood.R;
import com.example.uberfood.adapters.SearchActivityViewPager;
import com.example.uberfood.adapters.SearchHomeFragmentAdapter;
import com.example.uberfood.factories.DialogBuilderFactory;
import com.example.uberfood.models.Restaurant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.tooltip.Tooltip;
import java.util.ArrayList;
import lib.kingja.switchbutton.SwitchMultiButton;
import static com.example.uberfood.utils.Constants.RESTAURANT_KEY;

public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Context context;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ViewPager viewPager;
    RecyclerView recyclerView;
    AppCompatEditText editTextSearch;
    SwitchMultiButton switchMultiButton;
    LinearLayout fiterIcon;
    SearchHomeFragmentAdapter searchHomeFragmentAdapter;
    ArrayList<Restaurant> restaurants = new ArrayList<>();
    static  ArrayList<Restaurant> restaurantsForSearch = new ArrayList<>();


    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    private void initializeViews() {

        viewPager.setCurrentItem(0);
        viewPager.setAdapter(new SearchActivityViewPager(getFragmentManager(), 2));
    }

    @Override
    public void onResume() {
        super.onResume();

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                initializeViews();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager = rootView.findViewById(R.id.home_fragment_view_pager);
        editTextSearch = rootView.findViewById(R.id.restaurant_search_name);
        recyclerView = rootView.findViewById(R.id.search_recylcer_view);
        switchMultiButton = rootView.findViewById(R.id.switch_multi_button_from_home_fragment);
        fiterIcon = rootView.findViewById(R.id.filter_icon);
        initializeViews();
        setClickableLayouts();
        searchFunctionnality();

        return rootView;
    }

    private void searchFunctionnality() {

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);



        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count) {

                Log.e("editable search", s + "");

                if ( s.toString().length() == 0){

                } else {

                }



                //

                // CollectionReference ref = FirebaseFirestore.getInstance().collection(RESTAURANT_KEY);
                /*    Task<QuerySnapshot> query = ref.whereArrayContains("name", s+"").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (final QueryDocumentSnapshot document : task.getResult()) {
                                    Log.e("FB2", document.getId() + " => " + document.getData());


                                    Restaurant restaurant = document.toObject(Restaurant.class);
                                    restaurantsForSearch.add(restaurant);
                                }

                                Log.e("restaurantsForSearch size =" , restaurantsForSearch.size()+"!");
                                searchHomeFragmentAdapter = new SearchHomeFragmentAdapter(getContext(), restaurantsForSearch);

                                LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                                recyclerView.setLayoutManager(mLayoutManager);
                                recyclerView.setAdapter(searchHomeFragmentAdapter);

                                searchHomeFragmentAdapter.notifyDataSetChanged();
                            }
                        }
                    });*/

               /* CollectionReference ref = FirebaseFirestore.getInstance().collection(RESTAURANT_KEY);


                Query query = ref.whereEqualTo("name", s+"");

               query.get()  .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for ( QueryDocumentSnapshot document : task.getResult()) {
                                Restaurant restaurant = document.toObject(Restaurant.class);
                                Log.e("rest name", restaurant.getName() + "!");
                                restaurantsForSearch.add(restaurant);
                            }

                            Log.e("restaurantsForSearch size =", restaurantsForSearch.size() + "!");


                            searchHomeFragmentAdapter.update(restaurantsForSearch);
                            restaurantsForSearch.clear();


                        }
                    }
                });



*/
/*
                CollectionReference ref = FirebaseFirestore.getInstance().collection(RESTAURANT_KEY);


                Query query = ref.whereEqualTo("name", s + "");
                query.addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                        for (DocumentSnapshot document : queryDocumentSnapshots) {
                            Restaurant restaurant = document.toObject(Restaurant.class);
                            Log.e("rest name", restaurant.getName() + "!");
                            restaurantsForSearch.add(restaurant);
                        }

                        Log.e("restaurantsForSearch size =", restaurantsForSearch.size() + "!");


                        searchHomeFragmentAdapter.update(restaurantsForSearch);
                        restaurantsForSearch.clear();
                    }
                });*/






                CollectionReference ref = FirebaseFirestore.getInstance().collection(RESTAURANT_KEY);

                //ref.whereEqualTo("name" , s+"")





                        //ref.whereEqualTo("name" , s+"")

                       ref.get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                              if ( task.isSuccessful()){
                                  for (QueryDocumentSnapshot document : task.getResult()){
                                      Log.e("data" , document.getData()+"");
                                      Restaurant restaurant = document.toObject(Restaurant.class);
                                      if (restaurant.getName().contains(s+"")){
                                          restaurantsForSearch.add(restaurant);
                                          Log.e("rest name " , restaurant.getName());
                                      }

                                  }
                              }
                            }
                        });


                Query query = ref.whereEqualTo("name", s + "");
              query.addSnapshotListener(new EventListener<QuerySnapshot>() {
                         @Override
                         public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                             for (DocumentSnapshot doc : queryDocumentSnapshots) {
                                 Restaurant restaurant = doc.toObject(Restaurant.class);
                                 Log.e("rest name" , restaurant.getName()+"!");
                                 restaurantsForSearch.add(restaurant);
                             }

                             Log.e("restaurantsForSearch size =" , restaurantsForSearch.size()+"!");



                             searchHomeFragmentAdapter = new SearchHomeFragmentAdapter(getContext(), restaurantsForSearch);



                             recyclerView.setAdapter(searchHomeFragmentAdapter);
                             searchHomeFragmentAdapter.update(restaurantsForSearch);
                             restaurantsForSearch.clear();


                         }
                     });

               /* CollectionReference ref = FirebaseFirestore.getInstance().collection(RESTAURANT_KEY);


                Query query = ref.whereEqualTo("name", s + "");
                  query
                            .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                @Override
                                public void onEvent(@Nullable QuerySnapshot snapshots,
                                                    @Nullable FirebaseFirestoreException e) {
                                    if (e != null) {
                                        System.err.println("Listen failed:" + e);
                                        return;
                                    }


                                    for (DocumentSnapshot doc : snapshots) {
                                        Restaurant restaurant = doc.toObject(Restaurant.class);
                                        restaurantsForSearch.add(restaurant);
                                        Log.e("rest name" , restaurant.getName()+"!");
                                    }

                                    Log.e("restaurantsForSearch size =" , restaurantsForSearch.size()+"!");
                                    searchHomeFragmentAdapter = new SearchHomeFragmentAdapter(getContext(), restaurantsForSearch);

                                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                                    recyclerView.setLayoutManager(mLayoutManager);
                                    recyclerView.setAdapter(searchHomeFragmentAdapter);

                                    searchHomeFragmentAdapter.notifyDataSetChanged();
                                }
                            });
*/


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });
    }

    private void setClickableLayouts() {


        fiterIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Tooltip.Builder builder = new Tooltip.Builder(v, R.style.Tooltip)
                        .setCancelable(true)
                        .setArrowEnabled(false)
                        .setDismissOnClick(false)
                        .setCornerRadius(20f)
                        .setGravity(Gravity.TOP)
                        .setText(R.string.filter_tooltip_text);
                builder.show();
                DialogBuilderFactory.showFilterDialog(getContext());
            }
        });

        switchMultiButton.setOnSwitchListener(new SwitchMultiButton.OnSwitchListener() {
            @Override
            public void onSwitch(int position, String tabText) {


                if (position == 0) {


                    viewPager.setCurrentItem(0);

                } else {
                    viewPager.setCurrentItem(1);
                }

            }
        });


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {


                if (position == 0) {

                    switchMultiButton.clearSelection();
                    switchMultiButton.setSelectedTab(0);


                } else {
                    switchMultiButton.clearSelection();
                    switchMultiButton.setSelectedTab(1);


                }


            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


}
