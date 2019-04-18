package com.example.uberfood.models;

public class Photo {

    String downloadURL ;
    String nom ;

    public Photo() {
    }

    public Photo(String downloadURL, String nom) {
        this.downloadURL = downloadURL;
        this.nom = nom;
    }

    public String getDownloadURL() {
        return downloadURL;
    }

    public void setDownloadURL(String downloadURL) {
        this.downloadURL = downloadURL;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
