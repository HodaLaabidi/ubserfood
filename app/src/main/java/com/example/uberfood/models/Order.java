package com.example.uberfood.models;

import java.util.ArrayList;
import java.util.Date;

public class Order {


    String _id;
    String adress_building;
    String adress_street;
    String adress_zipcode;
    String adress_coord_longitude;
    String customer_id;
    String restaurant_id;
    Date date ;
    float total ;
    ArrayList plates ;
    ArrayList description;

    public Order() {
    }

    public Order(String _id, String adress_building, String adress_street, String adress_zipcode, String adress_coord_longitude, String customer_id, String restaurant_id, Date date, float total, ArrayList plates, ArrayList description) {
        this._id = _id;
        this.adress_building = adress_building;
        this.adress_street = adress_street;
        this.adress_zipcode = adress_zipcode;
        this.adress_coord_longitude = adress_coord_longitude;
        this.customer_id = customer_id;
        this.restaurant_id = restaurant_id;
        this.date = date;
        this.total = total;
        this.plates = plates;
        this.description = description;
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAdress_building() {
        return adress_building;
    }

    public void setAdress_building(String adress_building) {
        this.adress_building = adress_building;
    }

    public String getAdress_street() {
        return adress_street;
    }

    public void setAdress_street(String adress_street) {
        this.adress_street = adress_street;
    }

    public String getAdress_zipcode() {
        return adress_zipcode;
    }

    public void setAdress_zipcode(String adress_zipcode) {
        this.adress_zipcode = adress_zipcode;
    }

    public String getAdress_coord_longitude() {
        return adress_coord_longitude;
    }

    public void setAdress_coord_longitude(String adress_coord_longitude) {
        this.adress_coord_longitude = adress_coord_longitude;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public ArrayList getPlates() {
        return plates;
    }

    public void setPlates(ArrayList plates) {
        this.plates = plates;
    }

    public ArrayList getDescription() {
        return description;
    }

    public void setDescription(ArrayList description) {
        this.description = description;
    }
}