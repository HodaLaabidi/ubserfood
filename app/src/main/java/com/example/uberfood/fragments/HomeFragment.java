package com.example.uberfood.fragments;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.uberfood.R;
import com.example.uberfood.adapters.SearchActivityViewPager;
import com.example.uberfood.adapters.SearchHomeFragmentAdapter;
import com.example.uberfood.models.Restaurant;
import com.example.uberfood.utils.ConnectivityService;
import com.example.uberfood.utils.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jrizani.jrspinner.JRSpinner;
import lib.kingja.switchbutton.SwitchMultiButton;

import static com.example.uberfood.utils.Constants.PERMISSIONS_LOCATION;
import static com.example.uberfood.utils.Constants.RESTAURANT_KEY;
import static com.example.uberfood.utils.Utils.hasPermissions;

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
    public static RelativeLayout rlSearchLocation , rlFilter;
    LinearLayout locationIcon ;
    LocationManager locationManager ;
    RecyclerView recyclerView;
    ImageView closeSearchLocationIcon ;
    AppCompatEditText editTextSearch;
    SwitchMultiButton switchMultiButton;
    LinearLayout fiterIcon , myCurrentLocation;
    ImageView iconCloseFilter ;
    JRSpinner searchCitySpinner , searchQuarterSpinner;
    public static LinearLayout searchToolbar;
    public static LinearLayout llFragmentHome , llNoResultsFound;

    SearchHomeFragmentAdapter searchHomeFragmentAdapter ;
    ArrayList<Restaurant> restaurants = new ArrayList<>();
    ArrayList<Restaurant> restaurantsForSearch = new ArrayList<>();


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


        llFragmentHome.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
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
                if (editTextSearch.getText().toString().trim() != ""){
                    setRecyclerViewForSearchResults(editTextSearch.getText().toString());
                }


            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        if (locationManager == null) {
            locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        }

        viewPager = rootView.findViewById(R.id.home_fragment_view_pager);
        myCurrentLocation = rootView.findViewById(R.id.my_current_location);
        editTextSearch = rootView.findViewById(R.id.restaurant_search_name);
        recyclerView = rootView.findViewById(R.id.search_recylcer_view);
        llFragmentHome = rootView.findViewById(R.id.ll_fragment_home);
        locationIcon = rootView.findViewById(R.id.location_icon);
        rlFilter = rootView.findViewById(R.id.rl_filter);
        llNoResultsFound = rootView.findViewById(R.id.ll_no_results_found_from_fragment_home);
        iconCloseFilter = rootView.findViewById(R.id.close_filter);
        closeSearchLocationIcon = rootView.findViewById(R.id.close_search_location_icon);
        searchToolbar = rootView.findViewById(R.id.search_toolbar);
        searchQuarterSpinner = rootView.findViewById(R.id.spn_search_quarter);
        searchCitySpinner  = rootView.findViewById(R.id.spn_search_city);
        rlSearchLocation = rootView.findViewById(R.id.rl_search_location);
        switchMultiButton = rootView.findViewById(R.id.switch_multi_button_from_home_fragment);
        fiterIcon = rootView.findViewById(R.id.filter_icon);
        initializeViews();
        setClickableLayouts();
        searchFunctionnality();

        return rootView;
    }

    private void searchFunctionnality() {





        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override

            public void onTextChanged(final CharSequence s, int start, int before, int count) {


                setRecyclerViewForSearchResults( s.toString());

                        };


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override

                public void afterTextChanged(final Editable s) {


            }

        });
    }

    private void setRecyclerViewForSearchResults(final String string) {

        Log.e("editable search", string + "");
        restaurantsForSearch.clear();


        CollectionReference ref = FirebaseFirestore.getInstance().collection(RESTAURANT_KEY);

        ref.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull final Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    restaurantsForSearch.clear();
                    restaurantsForSearch = new ArrayList<>();
                    Log.e("test22", task.getResult().getDocuments().toString());
                    for (final QueryDocumentSnapshot document : task.getResult()) {
                        Restaurant restaurant = document.toObject(Restaurant.class);
                        if (restaurant.getName() != null) {
                            if (restaurant.getName().contains(string )){
                                restaurantsForSearch.add(restaurant);
                                Log.e("resttest ", restaurant.getName());
                            }

                        }


                    }

                    for (int i = 0 ; i < restaurantsForSearch.size() ; i++){
                        Log.e("testrestaurant"+i, restaurantsForSearch.get(i).getName()+"!");
                    }
                    if (string.length() == 0 ) {

                        llNoResultsFound.setVisibility(View.GONE);
                        llFragmentHome.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                        viewPager.setVisibility(View.VISIBLE);
                        switchMultiButton.setVisibility(View.VISIBLE);
                        restaurantsForSearch.clear();

                    } else {
                        if (restaurantsForSearch.size() == 0) {
                            switchMultiButton.setVisibility(View.VISIBLE);
                            viewPager.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.GONE);
                            llFragmentHome.setVisibility(View.VISIBLE);
                            llNoResultsFound.setVisibility(View.VISIBLE);
                            restaurantsForSearch.clear();

                        } else {
                            switchMultiButton.setVisibility(View.GONE);
                            viewPager.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                            llNoResultsFound.setVisibility(View.GONE);
                            llFragmentHome.setVisibility(View.VISIBLE);
                            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setHasFixedSize(true);
                            searchHomeFragmentAdapter = new SearchHomeFragmentAdapter(getContext(), restaurantsForSearch);
                            recyclerView.setAdapter(searchHomeFragmentAdapter);
                            searchHomeFragmentAdapter.notifyDataSetChanged();
                            restaurantsForSearch.clear();
                        }
                    }





                }
            }});

    }

    private boolean containsRestaurantObject(Restaurant restaurant , ArrayList<Restaurant> listOfRestaurants) {
        int count =  0 ;
        for (int i = 0 ; i <listOfRestaurants.size() ; i++){
            if (listOfRestaurants.get(i).getName().trim().equals(restaurant.getName()) && listOfRestaurants.get(i).getId().trim().equals(restaurant.getId()) ){
                count++;
            }



        }
        if (count != listOfRestaurants.size()){
            return true;
        } else {
            return false ;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void setClickableLayouts() {


        locationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (rlSearchLocation.getVisibility() == View.GONE){

                    openFilter(rlSearchLocation, getContext());

                }

            }
        });

        //

        // layout clicklistener:  i will use method openFiler and closeFilter after modifications

        // add rlFilter animation




        fiterIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                           openFilterWithoutSearch(rlFilter, context);

               }

                /*Tooltip.Builder builder = new Tooltip.Builder(v, R.style.Tooltip)
                        .setCancelable(true)
                        .setArrowEnabled(false)
                        .setDismissOnClick(false)
                        .setCornerRadius(20f)
                        .setGravity(Gravity.TOP)
                        .setText(R.string.filter_tooltip_text);
                builder.show();
                DialogBuilderFactory.showFilterDialog(getContext());
            }*/

                // add the animation here !
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


            setSearchLocationLayout();
    }

    private void openFilterWithoutSearch(final View view, final Context context) {


        Animation animation = AnimationUtils.loadAnimation(context , R.anim.slide_up);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {



                AnimationUtils.loadAnimation(context,
                        R.anim.slide_up);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.VISIBLE);
                llFragmentHome.setVisibility(View.GONE);
                searchToolbar.setVisibility(View.GONE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });



        view.startAnimation(animation);


        iconCloseFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                closeFilterWithoutSearch(view , context);
            }
        });

    }

    private void closeFilterWithoutSearch(final View view, final Context context) {






        Animation animation = AnimationUtils.loadAnimation(context , R.anim.slide_down);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                searchToolbar.setVisibility(View.VISIBLE);
                llFragmentHome.setVisibility(View.VISIBLE);

                AnimationUtils.loadAnimation(context,
                        R.anim.slide_down);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });



        view.startAnimation(animation);


    }

    private void setSearchLocationLayout() {




        final String[] cities = new String[5];
        cities[0] = "Tunis";
        cities[1] = "Ariana";
        cities[2] = "Manouba";
        cities[3] = "Ben arous";
        cities[4] = "Bardoo";

        searchCitySpinner.setItems(cities);
        searchCitySpinner.setExpandTint(R.color.colorOrange);
        searchCitySpinner.setOnItemClickListener(new JRSpinner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                searchCitySpinner.setText(cities[position]);
                Log.e("position = " , position +" !");

            }
        });

        searchCitySpinner.setOnSelectMultipleListener(new JRSpinner.OnSelectMultipleListener() {
            @Override
            public void onMultipleSelected(List<Integer> selectedPosition) {




                // if i choose select i will open the new dialog search popup

                // search pop up with dialogflow rq

            }
        });

        searchQuarterSpinner.setItems(cities);
        searchQuarterSpinner.setExpandTint(R.color.colorOrange);
        searchQuarterSpinner.setOnSelectMultipleListener(new JRSpinner.OnSelectMultipleListener() {
            @Override
            public void onMultipleSelected(List<Integer> selectedPosition) {




                // if i choose select i will open the new dialog search popup

                //

            }
        });





        searchQuarterSpinner.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    searchQuarterSpinner.setItems(cities);
                    searchQuarterSpinner.setExpandTint(R.color.colorOrange);
                    searchQuarterSpinner.setOnSelectMultipleListener(new JRSpinner.OnSelectMultipleListener() {
                        @Override
                        public void onMultipleSelected(List<Integer> selectedPosition) {


                            // Log.e( "position" , position );
                            //



                        }
                    });
                }
            }
        });






    }


    private void openFilter(final View view, final Context context) {


        // use this animation to set up filter layout





        Animation animation = AnimationUtils.loadAnimation(context , R.anim.slide_up);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {



                AnimationUtils.loadAnimation(context,
                        R.anim.slide_up);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.VISIBLE);
                llFragmentHome.setVisibility(View.GONE);
                searchToolbar.setVisibility(View.GONE);
                myCurrentLocation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Log.e("test on click" , "ok");

                        if (hasPermissions(getContext(), Constants.MY_PERMISSIONS_REQUEST_STORAGE, PERMISSIONS_LOCATION)) {

                            Log.e("test gps" , "ok !!");
                            if (ConnectivityService.displayGpsStatus(locationManager)) {
                                Log.e("test gps" , "ok");
                                /*progressBar.setVisibility(View.VISIBLE);
                                myLocationManager.getLocation(getContext(), new MyLocationManager.LocationResult() {
                                    @Override
                                    public void gotLocation(Location location) {
                                        if (location != null) {

                                            Utils.latitude = location.getLatitude();
                                            Utils.longitude = location.getLongitude();
                                            runOnUiThread(new Runnable() {
                                                public void run() {
                                                    try {
                                                        addresses = geocoder.getFromLocation(Utils.latitude, Utils.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                                                        address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

                                                        commerceAdresse.setText(address);
                                                        Utils.adress = address;

                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                    if (address.equalsIgnoreCase("")) {
                                                        //   ivLocationFound.setImageResource(R.drawable.text_warning);
                                                    } else {
                                                        //   ivLocationFound.setImageResource(R.drawable.tick_green);
                                                    }
                                                    progressBar.setVisibility(View.GONE);
                                                    //WidgetUtils.enableUserInteraction(getActivity());

                                                }
                                            });

                                        }
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                progressBar.setVisibility(View.GONE);
                                                //WidgetUtils.enableUserInteraction(getActivity());

                                            }
                                        });

                                    }
                                }); */
                            }else {
                                showGpsDialog();
                                Log.e("test dialog gps" , "ok");
                            }
                        }

                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });



        view.startAnimation(animation);


        closeSearchLocationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeFilter(rlSearchLocation , context);
            }
        });

    }



        private void showGpsDialog() {

            final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());

            View viewAlerte = this.getLayoutInflater().inflate(R.layout.active_gps, null);


            View layoutActiveGps = viewAlerte.findViewById(R.id.rl_active_gps);
            dialogBuilder.setView(viewAlerte);


            dialogBuilder.create();
            final AlertDialog alertDialog = dialogBuilder.show();
            layoutActiveGps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    if (alertDialog.isShowing())
                        alertDialog.dismiss();


                }
            });



    }


    public static void closeFilter(final View view  , final Context context) {



        Animation animation = AnimationUtils.loadAnimation(context , R.anim.slide_down);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                searchToolbar.setVisibility(View.VISIBLE);
                llFragmentHome.setVisibility(View.VISIBLE);

                AnimationUtils.loadAnimation(context,
                        R.anim.slide_down);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });



        view.startAnimation(animation);




    }


}
