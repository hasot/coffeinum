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
        StorageReference storage = FirebaseStorage.getInstance().getReference();
        StorageReference iconReference = storage.child(marker.iconName + ".png");
        iconReference.getBytes(1024 * 1024).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap icon = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                LatLng position = new LatLng(marker.lat, marker.lng);

                MarkerOptions options = new MarkerOptions()
                        .position(position)
                        .icon(BitmapDescriptorFactory.fromBitmap(icon));

                googleMap.addMarker(options);
            }
        });
    }
}
