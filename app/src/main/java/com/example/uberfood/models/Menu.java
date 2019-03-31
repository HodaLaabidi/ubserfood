package com.example.uberfood.models;

import java.io.Serializable;

public class Menu implements Serializable  {

    private String category;
    private String label ;
    private String description ;
    private String price ;
    private int image ;

    public Menu() {
    }

    public Menu(String category, String label, String description, String price, int image) {
        this.category = category;
        this.label = label;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
