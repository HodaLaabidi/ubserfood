package com.example.uberfood.activities;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.uberfood.R;
import com.example.uberfood.adapters.CustomCarouselPageAdapter;
import com.example.uberfood.utils.ConnectivityService;
import com.example.uberfood.utils.Constants;


import static com.example.uberfood.adapters.CustomCarouselPageAdapter.FIRST_PAGE;
import static com.example.uberfood.utils.Constants.PERMISSIONS_LOCATION;
import static com.example.uberfood.utils.Utils.hasPermissions;

public class FirstScreenActivity extends AppCompatActivity {



    ViewPager carouselViewPager ;
    CustomCarouselPageAdapter customCarouselPageAdapter;
    AppCompatTextView signIn;
    LinearLayout searchButton ;
    ImageView imageSearch ;
    AppCompatEditText etSearch ;
    LocationManager locationManager ;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        initializeViews();

        carouselViewPager.setAdapter(customCarouselPageAdapter);
        carouselViewPager.setPageTransformer(false, customCarouselPageAdapter);

        carouselViewPager.setCurrentItem(1);


        carouselViewPager.setOffscreenPageLimit(3);

        carouselViewPager.setPageMargin(-400);

    }

    private void initializeViews() {


        if (locationManager == null) {
            locationManager = (LocationManager)getBaseContext().getSystemService(Context.LOCATION_SERVICE);
        }

        signIn = findViewById(R.id.sign_in);
        imageSearch = findViewById(R.id.image_search);
        carouselViewPager = findViewById(R.id.carousel_view_pager);
        searchButton = findViewById(R.id.search_button);
        etSearch = findViewById(R.id.et_search_restaurants);

        customCarouselPageAdapter = new CustomCarouselPageAdapter(this, this.getSupportFragmentManager());





        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstScreenActivity.this , LoginActivity.class);
               startActivity(intent);
               finish();

            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent =  new Intent(FirstScreenActivity.this , MainActivity.class);
                startActivity(intent);

            }
        });


        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch();
                    return true;
                }
                return false;
            }
        });
        imageSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("test on click" , "ok");

                if (hasPermissions(getBaseContext(), Constants.MY_PERMISSIONS_REQUEST_STORAGE, PERMISSIONS_LOCATION)) {

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
                        showGpsDialog(FirstScreenActivity.this);
                        Log.e("test dialog gps" , "ok");
                    }
                }

            }
        });





    }

    // i have to add some futures and test if the app support uploading views

    public void showGpsDialog(Context context) {

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);

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

    private void performSearch() {
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
