package com.example.uberfood.models;

import com.google.firebase.Timestamp;

import java.util.ArrayList;
import java.util.Date;

public class PlacedOrder {


       private Timestamp actual_delivery_time ;
       private String customer_id ;
       private String delivery_address;
       private String estimated_delivery_time ;
       private Timestamp final_price ;
       private boolean food_ready ;
       private ArrayList<String> menu_item ;
       private Date order_time ;
       double price ;
       String restaurant_id ;


    public PlacedOrder() {
    }




    public PlacedOrder(Timestamp actual_delivery_time, String customer_id, String delivery_address, String estimated_delivery_time, Timestamp final_price, boolean food_ready, ArrayList<String> menu_item, Date order_time, double price, String restaurant_id) {
        this.actual_delivery_time = actual_delivery_time;
        this.customer_id = customer_id;
        this.delivery_address = delivery_address;
        this.estimated_delivery_time = estimated_delivery_time;
        this.final_price = final_price;
        this.food_ready = food_ready;
        this.menu_item = menu_item;
        this.order_time = order_time;
        this.price = price;
        this.restaurant_id = restaurant_id;
    }

    public Timestamp getActual_delivery_time() {
        return actual_delivery_time;
    }

    public void setActual_delivery_time(Timestamp actual_delivery_time) {
        this.actual_delivery_time = actual_delivery_time;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getDelivery_address() {
        return delivery_address;
    }

    public void setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address;
    }

    public String getEstimated_delivery_time() {
        return estimated_delivery_time;
    }

    public void setEstimated_delivery_time(String estimated_delivery_time) {
        this.estimated_delivery_time = estimated_delivery_time;
    }

    public Timestamp getFinal_price() {
        return final_price;
    }

    public void setFinal_price(Timestamp final_price) {
        this.final_price = final_price;
    }

    public boolean isFood_ready() {
        return food_ready;
    }

    public void setFood_ready(boolean food_ready) {
        this.food_ready = food_ready;
    }

    public ArrayList<String> getMenu_item() {
        return menu_item;
    }

    public void setMenu_item(ArrayList<String> menu_item) {
        this.menu_item = menu_item;
    }

    public Date getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Date order_time) {
        this.order_time = order_time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }
}
