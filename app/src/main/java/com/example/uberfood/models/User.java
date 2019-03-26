package com.example.uberfood.models;

import java.io.Serializable;

public class User implements Serializable {

    String username ;
    String location ;
    String email ;
    String password ;
    int postalCode ;
    double phoneNumber ;


    public User(String username, String location, String email, String password, int postalCode, double phoneNumber) {
        this.username = username;
        this.location = location;
        this.email = email;
        this.password = password;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public double getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(double phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
