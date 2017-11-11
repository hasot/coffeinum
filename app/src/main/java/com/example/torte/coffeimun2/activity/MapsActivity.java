package com.example.torte.coffeimun2.activity;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.torte.coffeimun2.DataBaseModel;
import com.example.torte.coffeimun2.R;
import com.example.torte.coffeimun2.TorteMap;
import com.example.torte.coffeimun2.TorteMarker;
import com.example.torte.coffeimun2.model.Order;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private TorteMap map;

    private DatabaseReference dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        dataBase = FirebaseDatabase.getInstance().getReference();

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
        SubscribeToOrderChange();
    }

    private void SubscribeToOrderChange()
    {
        dataBase
                .child(DataBaseModel.Orders)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        Order order = GetFirstOrDefaultOrder(dataSnapshot);
                        if (order != null)
                        {
                            map.StartAnimation(order.id_house);
                        }
                        else
                        {
                            map.StopAnimation();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        ShowToastError("Error of order change load");
                    }
                });
    }

    private static Order GetFirstOrDefaultOrder(DataSnapshot dataSnapshot)
    {
        for (DataSnapshot snapshot : dataSnapshot.getChildren())
        {
            return snapshot.getValue(Order.class);
        }
        return null;
    }

    private void LoadMarkers()
    {
        dataBase
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
                        ShowToastError("Error, when load markers");
                    }
                });
    }

    private void ShowToastError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onMarkerClick(Marker marker)
    {
        GoToCoffeHouseActivity(marker);
        return true;
    }

    private void GoToCoffeHouseActivity(Marker marker) {
        String cafeId = map.FindCafeId(marker);
        if (cafeId != null)
        {
            Intent intent = new Intent(this, CoffeeHouseActivity.class);
            intent.putExtra(DataBaseModel.cafeIdFromMapsActivity, cafeId);
            startActivity(intent);
        }
        else
        {
            ShowToastError("Cant send cafe ID, maaaaan");
        }
    }
}
