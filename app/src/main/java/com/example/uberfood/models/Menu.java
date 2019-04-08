package com.example.uberfood.models;

import java.io.Serializable;

public class Menu implements Serializable  {

    private String category_name;
    String id ;
    private boolean active ;
    private String description ;
    private String item_name ;
    private double price ;
    private String recipe;

    public Menu() {
    }

    public Menu(String category, String description, int price, String recipe , boolean active , String item_name , String id) {
        this.category_name = category;
        this.id = id ;
        this.active = active ;
        this.item_name = item_name ;
        this.description = description;
        this.price = price;
        this.recipe = recipe ;

    }

    public String getId() {
        return id;
    }



    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



    @Override
    public String toString() {
        return this.item_name + " "+ this.active +" " + this.recipe + " "+ this.category_name + " "+ this.description + " "+ this.price + " ";
    }
}
