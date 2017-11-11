package com.example.torte.coffeimun2;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by torte on 11.11.2017.
 */

public class TorteMap {

    private final LatLng rostovLocation = new LatLng(47.2186297, 39.7103795);
    private final float defaultMapZoom = 16f;

    private GoogleMap map;

    public TorteMap(GoogleMap map){
        this.map = map;
    }

    public void GoToRostov() {
        map.moveCamera(CameraUpdateFactory.newLatLng(rostovLocation));
        map.animateCamera(CameraUpdateFactory.zoomTo(defaultMapZoom));
    }

    public void AddMarker(TorteMarker marker)
    {
        LatLng position = new LatLng(marker.lat, marker.lng);

        MarkerOptions options = new MarkerOptions()
                .position(position)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.test_marker_icon));

        map.addMarker(options);
    }
}
