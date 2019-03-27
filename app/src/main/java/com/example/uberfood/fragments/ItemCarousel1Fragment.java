package com.example.uberfood.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.uberfood.R;
import com.example.uberfood.utils.CustomLinearLayout;

public class ItemCarousel1Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

   // private DeliveryFragment.OnFragmentInteractionListener mListener;

    public ItemCarousel1Fragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(Activity context, int position, float scale) {
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bundle.putFloat("scale", scale);
        return Fragment.instantiate(context, ItemCarousel1Fragment.class.getName(), bundle);
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
        if (container == null) {
            return null;
        }

        View view =
                inflater.inflate(R.layout.item_carousel_1, container, false);

        int position = this.getArguments().getInt("position");


        CustomLinearLayout root = (CustomLinearLayout) view.findViewById(R.id.item_root);
        ImageView imageCarousel = view.findViewById(R.id.image_item_carousel_1);


        float scale = this.getArguments().getFloat("scale");
        root.setScaleBoth(scale);

        return view;
    }



}
