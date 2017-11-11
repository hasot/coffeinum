package com.example.torte.coffeimun2.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dmitry on 11/11/17.
 */
@IgnoreExtraProperties
public class CoffeHouseModel {

    public String id;
    public String name;
    public String head_img;
    public HashMap<String , Menu> menu;

    public CoffeHouseModel() {}


}

