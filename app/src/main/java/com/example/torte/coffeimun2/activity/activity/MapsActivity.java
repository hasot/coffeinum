package com.example.torte.coffeimun2.activity.activity;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.torte.coffeimun2.DataBaseModel;
import com.example.torte.coffeimun2.R;
import com.example.torte.coffeimun2.TorteMap;
import com.example.torte.coffeimun2.TorteMarker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private TorteMap map;

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
        googleMap.setOnMarkerClickListener(this);
        map = new TorteMap(googleMap);

        map.GoToRostov();

        LoadMarkers();
    }

    private void LoadMarkers()
    {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase
                .child(DataBaseModel.Markers)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren())
                        {
                            TorteMarker marker = snapshot.getValue(TorteMarker.class);
                            map.AddMarker(marker);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        GoToCoffeHouseActivity();
        return true;
    }

    private void GoToCoffeHouseActivity() {
        Intent intent = new Intent(this, CoffeeHouseActivity.class);
        startActivity(intent);
    }
}
