package com.example.uberfood.models;


public class User  {


    String username ;
    String location ;
    String email ;
    String postal_code ;
    String phone_number ;


    public User(String username, String location, String email,  String postalCode, String phoneNumber) {
        this.username = username;
        this.location = location;
        this.email = email;
        this.postal_code = postalCode;
        this.phone_number = phoneNumber;
    }


    public User() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPostalCode() {
        return postal_code;
    }

    public void setPostalCode(String postalCode) {
        this.postal_code = postalCode;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phone_number = phoneNumber;
    }


    @Override
    public String toString() {
        return username+ location +postal_code + phone_number ;
    }
}
