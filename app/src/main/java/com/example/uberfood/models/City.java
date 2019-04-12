package com.example.uberfood.models;

import java.util.ArrayList;

public class City {

    String name ;
    ArrayList<Quarter> quarters ;

    public City() {
    }

    public City(String name, ArrayList<Quarter> quarters) {
        this.name = name;
        this.quarters = quarters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Quarter> getQuarters() {
        return quarters;
    }

    public void setQuarters(ArrayList<Quarter> quarters) {
        this.quarters = quarters;
    }
}
