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
    private ArrayList open_days ;
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

    public String getAdressBuilding() {
        return adress_building;
    }

    public void setAdressBuilding(String adress_building) {
        this.adress_building = adress_building;
    }

    public long getAdressZipcode() {
        return adress_zipcode;
    }

    public String getAdressStreet() {
        return adress_street;
    }

    public void setAdressStreet(String adress_street) {
        this.adress_street = adress_street;
    }

    public void setAdressZipcode(long adress_zipcode) {
        this.adress_zipcode = adress_zipcode;
    }


    public GeoPoint getAdressCoord() {
        return adress_coord;
    }

    public void setAdressCoord(GeoPoint adress_coord_longitude) {
        this.adress_coord = adress_coord_longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAvrgprc() {
        return avgprc;
    }

    public void setAvrgprc(long avrgprc) {
        this.avgprc = avrgprc;
    }

    public ArrayList getOpendays() {
        return open_days;
    }

    public void setOpendays(ArrayList opendays) {
        this.open_days = opendays;
    }

    public String getCloseTime() {
        return close_time;
    }

    public void setCloseTime(String close_time) {
        this.close_time = close_time;
    }

    public String getOpenTime() {
        return open_time;
    }

    public void setOpenTime(String open_time) {
        this.open_time = open_time;
    }

    public String getMatriculeFiscale() {
        return martricule_fiscale;
    }

    public void setMatriculeFiscale(String matricule_fiscale) {
        this.martricule_fiscale = matricule_fiscale;
    }

    public String getPhoneNumber() {
        return phone_Number;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_Number = phone_number;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String webSite) {
        this.website = webSite;
    }

    public String getAdressState() {
        return adress_state;
    }

    public void setAdressState(String adress_state) {
        this.adress_state = adress_state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getNumberOfOrders() {
        return number_of_orders;
    }

    public void setNumberOfOrders(Double number_of_orders) {
        this.number_of_orders = number_of_orders;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInscriptionDate() {
        return inscription_date;
    }

    public void setInscriptionDate(String inscription_date) {
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
        return this.getName() + " " + this.getLogo() + " " + this.getCuisine() + " " + this.getAdressBuilding() + " " + this.getAdressStreet() + " " +this.getAdressZipcode() + " "
                + " " +this.getPhoneNumber()+ " " +this.getDescription()+ " " +this.getEmail()+ " " +this.getWebsite()+ " " +this.getMatriculeFiscale()
                + " " +this.getOpenTime()+ " " +this.getOpenTime()+ " " +this.getOpendays()+ " " +this.getNumberOfOrders()+ " " +this.avgprc + " " ;
    }
}
