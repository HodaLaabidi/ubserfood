package com.example.uberfood.models;

import java.io.Serializable;

public class Menu implements Serializable  {

    private String category_name;
    private boolean active ;
    private String description ;
    private String item_name ;
    private int price ;
    private String recipe;

    public Menu() {
    }

    public Menu(String category, String description, int price, String recipe , boolean active , String item_name) {
        this.category_name = category;
        this.active = active ;
        this.item_name = item_name ;
        this.description = description;
        this.price = price;
        this.recipe = recipe ;

    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getItemName() {
        return item_name;
    }

    public void setItemName(String item_name) {
        this.item_name = item_name;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getCategory() {
        return category_name;
    }

    public void setCategory(String category) {
        this.category_name = category;
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

    public void setPrice(int price) {
        this.price = price;
    }



    @Override
    public String toString() {
        return this.item_name + " "+ this.active +" " + this.recipe + " "+ this.category_name + " "+ this.description + " "+ this.price + " ";
    }
}
