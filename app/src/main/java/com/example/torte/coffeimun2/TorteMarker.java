package com.example.torte.coffeimun2;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by torte on 11.11.2017.
 */

@IgnoreExtraProperties
public class TorteMarker {

    public double lat;
    public double lng;
    public String iconName;

    public TorteMarker() {}

    public TorteMarker(double lat, double lng, String iconName){
        this.lat = lat;
        this.lng = lng;
        this.iconName = iconName;
    }
}
