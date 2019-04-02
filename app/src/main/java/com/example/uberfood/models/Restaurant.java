package com.example.uberfood.models;

import com.google.firebase.firestore.GeoPoint;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

public class Restaurant {


    String _id ;
    String adress_building ;
    String adress_zipcode ;
    GeoPoint adress_coord_longitude ;
    String name ;
    DecimalFormat avrgprc ;
    ArrayList opendays ;
    Date close_time ;
    Date open_time ;
    String matricule_fiscale ;
    String phone_number ;
    String webSite ;
    String adress_state ;
    String description ;
    Double number_of_orders ;
    String email ;
    Date inscription_date ;
    String  menu ;
    String image ;

    public Restaurant() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Restaurant(String _id, String image , String adress_building, String adress_zipcode, GeoPoint adress_coord_longitude, String name, DecimalFormat avrgprc, ArrayList opendays, Date close_time, Date open_time, String matricule_fiscale, String phone_number, String webSite, String adress_state, String description, Double number_of_orders, String email, Date inscription_date, String menu) {
        this._id = _id;
        this.image = image ;
        this.adress_building = adress_building;
        this.adress_zipcode = adress_zipcode;
        this.adress_coord_longitude = adress_coord_longitude;
        this.name = name;
        this.avrgprc = avrgprc;
        this.opendays = opendays;
        this.close_time = close_time;
        this.open_time = open_time;
        this.matricule_fiscale = matricule_fiscale;
        this.phone_number = phone_number;
        this.webSite = webSite;
        this.adress_state = adress_state;
        this.description = description;
        this.number_of_orders = number_of_orders;
        this.email = email;
        this.inscription_date = inscription_date;
        this.menu = menu;
    }


    public String get_id() {
        return _id;
    }

    public String getAdress_building() {
        return adress_building;
    }

    public void setAdress_building(String adress_building) {
        this.adress_building = adress_building;
    }

    public String getAdress_zipcode() {
        return adress_zipcode;
    }

    public void setAdress_zipcode(String adress_zipcode) {
        this.adress_zipcode = adress_zipcode;
    }

    public GeoPoint getAdress_coord_longitude() {
        return adress_coord_longitude;
    }

    public void setAdress_coord_longitude(GeoPoint adress_coord_longitude) {
        this.adress_coord_longitude = adress_coord_longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DecimalFormat getAvrgprc() {
        return avrgprc;
    }

    public void setAvrgprc(DecimalFormat avrgprc) {
        this.avrgprc = avrgprc;
    }

    public ArrayList getOpendays() {
        return opendays;
    }

    public void setOpendays(ArrayList opendays) {
        this.opendays = opendays;
    }

    public Date getClose_time() {
        return close_time;
    }

    public void setClose_time(Date close_time) {
        this.close_time = close_time;
    }

    public Date getOpen_time() {
        return open_time;
    }

    public void setOpen_time(Date open_time) {
        this.open_time = open_time;
    }

    public String getMatricule_fiscale() {
        return matricule_fiscale;
    }

    public void setMatricule_fiscale(String matricule_fiscale) {
        this.matricule_fiscale = matricule_fiscale;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
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

    public Date getInscription_date() {
        return inscription_date;
    }

    public void setInscription_date(Date inscription_date) {
        this.inscription_date = inscription_date;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }
}
