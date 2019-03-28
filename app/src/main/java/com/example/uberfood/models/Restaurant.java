package com.example.uberfood.models;

public class Restaurant {


    private  String id ;
    private  String name ;
    private  String image ;



    public Restaurant(String id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public Restaurant() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}
