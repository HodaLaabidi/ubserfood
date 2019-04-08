package com.example.uberfood.models;

import com.google.firebase.firestore.GeoPoint;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

public class Restaurant {


    private String id;
    private String adress_building ;
    private String adress_street ;
    private long adress_zipcode ;
    private GeoPoint adress_coord ;
    private String name ;
    private long avgprc ;
    private ArrayList<Integer> open_days ;
    private String close_time ;
    private String open_time ;
    private String martricule_fiscale ;
    private String phone_Number ;
    private String website ;
    private String adress_state ;
    private String description ;
    private Double number_of_orders ;
    private String email ;
    private String inscription_date ;
    private Menu  menu ;
    private String logo ;
    private String cuisine ;

    // i m using phone_Number twice !

    public Restaurant() {
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Restaurant(String _id,String adress_street, String cuisine  ,String logo , String adress_building, long adress_zipcode, GeoPoint adress_coord_longitude, String name, long avgprc, ArrayList opendays, String close_time, String open_time, String matricule_fiscale, String phone_number, String webSite, String adress_state, String description, Double number_of_orders, String email, String inscription_date, Menu menu) {
        this.id = _id;
        this.adress_street = adress_street;
        this.logo = logo ;
        this.adress_building = adress_building;
        this.adress_zipcode = adress_zipcode;
        this.adress_coord = adress_coord_longitude;
        this.name = name;
        this.avgprc = avgprc;
        this.open_days = opendays;
        this.close_time = close_time;
        this.open_time = open_time;
        this.martricule_fiscale = matricule_fiscale;
        this.phone_Number = phone_number;
        this.website = webSite;
        this.adress_state = adress_state;
        this.description = description;
        this.number_of_orders = number_of_orders;
        this.email = email;
        this.inscription_date = inscription_date;
        this.menu = menu;
        this.cuisine = cuisine ;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public long getAdress_zipcode() {
        return adress_zipcode;
    }

    public void setAdress_zipcode(long adress_zipcode) {
        this.adress_zipcode = adress_zipcode;
    }

    public GeoPoint getAdress_coord() {
        return adress_coord;
    }

    public void setAdress_coord(GeoPoint adress_coord) {
        this.adress_coord = adress_coord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAvgprc() {
        return avgprc;
    }

    public void setAvgprc(long avgprc) {
        this.avgprc = avgprc;
    }

    public ArrayList<Integer> getOpen_days() {
        return open_days;
    }

    public void setOpen_days(ArrayList<Integer> open_days) {
        this.open_days = open_days;
    }

    public String getClose_time() {
        return close_time;
    }

    public void setClose_time(String close_time) {
        this.close_time = close_time;
    }

    public String getOpen_time() {
        return open_time;
    }

    public void setOpen_time(String open_time) {
        this.open_time = open_time;
    }

    public String getMartricule_fiscale() {
        return martricule_fiscale;
    }

    public void setMartricule_fiscale(String martricule_fiscale) {
        this.martricule_fiscale = martricule_fiscale;
    }

    public String getPhone_Number() {
        return phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        this.phone_Number = phone_Number;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAdress_state() {
        return adress_state;
    }

    public void setAdress_state(String adress_state) {
        this.adress_state = adress_state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getNumber_of_orders() {
        return number_of_orders;
    }

    public void setNumber_of_orders(Double number_of_orders) {
        this.number_of_orders = number_of_orders;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInscription_date() {
        return inscription_date;
    }

    public void setInscription_date(String inscription_date) {
        this.inscription_date = inscription_date;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    @Override
    public String toString() {
        return this.getName() + " " + this.getLogo() + " " + this.getCuisine() + " " + this.getAdress_building() + " " + this.adress_street + " " +this.getAdress_zipcode() + " "
                + " " +this.getPhone_Number()+ " " +this.getDescription()+ " " +this.getEmail()+ " " +this.getWebsite()+ " " +this.getMartricule_fiscale()
                + " " +this.getOpen_time()+ " " +this.getClose_time()+ " " +this.getOpen_days()+ " " +this.getNumber_of_orders()+ " " +this.avgprc + " " ;
    }
}
