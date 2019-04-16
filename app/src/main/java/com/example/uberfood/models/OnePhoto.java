package com.example.uberfood.models;

import java.util.HashMap;

import static com.example.uberfood.utils.Constants.countImg;



    public class OnePhoto {




        HashMap<String , String> photo;
        String id ;
        long id_ad ;

        public OnePhoto() {
        }

        public long getId_ad() {
            return id_ad;
        }

        public OnePhoto( HashMap<String , String> photo, String id) {
            this.photo = photo;
            this.id = id;
            id_ad = countImg ++ ;

        }

        public  HashMap<String , String> getPhoto() {
            return photo;
        }

        public void setPhoto( HashMap<String , String> photo) {
            this.photo = photo;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

