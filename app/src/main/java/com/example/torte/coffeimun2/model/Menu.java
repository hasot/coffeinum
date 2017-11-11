package com.example.torte.coffeimun2.model;

import java.util.HashMap;

public class Menu{

    public String id;
    public String name;
    public String img;
    public HashMap<String , Additives>  additives;

    public Menu(){}

    public String getImage_title() {
        return name;
    }

    public String getImage_ID() {
        return img;
    }

}
