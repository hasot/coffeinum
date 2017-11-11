package com.example.torte.coffeimun2.activity;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.torte.coffeimun2.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private TorteMap map;
    private final LatLng rostovLocation = new LatLng(47.2186297, 39.7103795);
    private final float defaultMapZoom = 16f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = new TorteMap(googleMap);

        map.GoToRostov();

        LatLng[] markerPositions = getMarkerPositions();
        for (int i = 0; i < markerPositions.length; ++i){
            map.AddMarker(markerPositions[i]);
        }
    }

    private static LatLng[] getMarkerPositions() {
        return new LatLng[]{
                new LatLng(47.218, 39.710),
                new LatLng(47.219, 39.711),
        };
    }
}
