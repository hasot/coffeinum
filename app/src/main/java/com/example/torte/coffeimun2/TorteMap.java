package com.example.torte.coffeimun2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by torte on 11.11.2017.
 */

public class TorteMap {

    private final LatLng rostovLocation = new LatLng(47.2186297, 39.7103795);
    private final float defaultMapZoom = 16f;

    private GoogleMap googleMap;

    public TorteMap(GoogleMap map){
        this.googleMap = map;
    }

    public void GoToRostov() {
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(rostovLocation));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(defaultMapZoom));
    }

    public void AddMarker(final TorteMarker marker)
    {
        ImageLoader.AddListener(marker.iconName, bitmap -> TorteMap.this.AddMarker(bitmap, marker));
    }

    private void AddMarker(Bitmap bitmap, TorteMarker marker) {
        LatLng position = new LatLng(marker.lat, marker.lng);

        MarkerOptions options = new MarkerOptions()
                .position(position)
                .icon(BitmapDescriptorFactory.fromBitmap(bitmap));

        googleMap.addMarker(options);
    }
}
