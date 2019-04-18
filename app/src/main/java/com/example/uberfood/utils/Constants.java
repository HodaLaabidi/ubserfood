package com.example.uberfood.utils;

import android.Manifest;

public class Constants {



    public static final String USER_COLLECTION = "users" ;

    public static final String MENU_COLLECTION = "menu" ;

    public static final String PLACED_ORDER_KEY = "placed_order";

    public static final  String RESTAURANT_KEY ="Restaurants";
    public  static  long countImg = 0;


    public static final  String GALLERY_COLLECTION = "gallery";


    public static final int MY_PERMISSIONS_REQUEST_STORAGE = 130;


    public static final String[] PERMISSIONS_PHOTO = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };



    public static  final String[] PERMISSIONS_LOCATION = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };



}
