package com.example.uberfood.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.uberfood.R;
import com.example.uberfood.adapters.SearchActivityViewPager;
import com.example.uberfood.factories.DialogBuilderFactory;
import com.tooltip.Tooltip;

import lib.kingja.switchbutton.SwitchMultiButton;

public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Context context ;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ViewPager viewPager ;
    SwitchMultiButton switchMultiButton ;
    LinearLayout fiterIcon ;



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
        viewPager.setAdapter(new SearchActivityViewPager(getFragmentManager() , 2));
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

       View rootView =  inflater.inflate(R.layout.fragment_home, container, false);

        viewPager = rootView.findViewById(R.id.home_fragment_view_pager);
        switchMultiButton = rootView.findViewById(R.id.switch_multi_button_from_home_fragment);
        fiterIcon = rootView.findViewById(R.id.filter_icon);
        initializeViews();
        setClickableLayouts();

        return rootView ;
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


                if (position == 0){


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


                if (position == 0){

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
