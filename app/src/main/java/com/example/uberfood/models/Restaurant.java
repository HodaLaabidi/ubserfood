package com.example.uberfood.models;

import com.google.firebase.firestore.GeoPoint;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

public class Restaurant {


    String _id ;
    String adress_building ;
    String adress_street ;
    String adress_zipcode ;
    GeoPoint adress_coord ;
    String name ;
    long avgprc ;
    ArrayList opendays ;
    String close_time ;
    String open_time ;
    String matricule_fiscale ;
    String phone_Number ;
    String website ;
    String adress_state ;
    String description ;
    Double number_of_orders ;
    String email ;
    String inscription_date ;
    String  menu ;
    String logo ;
    // i m using phone_Number twice !

    public Restaurant() {
    }

    public String getImage() {
        return logo;
    }

    public void setImage(String image) {
        this.logo = image;
    }

    public Restaurant(String _id,String adress_street, String image , String adress_building, String adress_zipcode, GeoPoint adress_coord_longitude, String name, long avgprc, ArrayList opendays, String close_time, String open_time, String matricule_fiscale, String phone_number, String webSite, String adress_state, String description, Double number_of_orders, String email, String inscription_date, String menu) {
        this._id = _id;
        this.adress_street = adress_street;
        this.logo = image ;
        this.adress_building = adress_building;
        this.adress_zipcode = adress_zipcode;
        this.adress_coord = adress_coord_longitude;
        this.name = name;
        this.avgprc = avgprc;
        this.opendays = opendays;
        this.close_time = close_time;
        this.open_time = open_time;
        this.matricule_fiscale = matricule_fiscale;
        this.phone_Number = phone_number;
        this.website = webSite;
        this.adress_state = adress_state;
        this.description = description;
        this.number_of_orders = number_of_orders;
        this.email = email;
        this.inscription_date = inscription_date;
        this.menu = menu;
    }


    public String getId() {
        return _id;
    }

    public String getAdressBuilding() {
        return adress_building;
    }

    public void setAdressBuilding(String adress_building) {
        this.adress_building = adress_building;
    }

    public String getAdressZipcode() {
        return adress_zipcode;
    }

    public String getAdressStreet() {
        return adress_street;
    }

    public void setAdressStreet(String adress_street) {
        this.adress_street = adress_street;
    }

    public void setAdressZipcode(String adress_zipcode) {
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
        return opendays;
    }

    public void setOpendays(ArrayList opendays) {
        this.opendays = opendays;
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
        return matricule_fiscale;
    }

    public void setMatriculeFiscale(String matricule_fiscale) {
        this.matricule_fiscale = matricule_fiscale;
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

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }
}
