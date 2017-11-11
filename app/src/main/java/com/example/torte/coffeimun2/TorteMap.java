package com.example.torte.coffeimun2;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by torte on 11.11.2017.
 */

public class TorteMap {

    private final LatLng rostovLocation = new LatLng(47.2186297, 39.7103795);
    private final float defaultMapZoom = 16f;

    private GoogleMap googleMap;
    private ArrayList<Marker> markers = new ArrayList<>();
    private ArrayList<String> cafeIds = new ArrayList<>();

    public TorteMap(GoogleMap map)
    {
        this.googleMap = map;

        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
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

        Marker googleMarker = googleMap.addMarker(options);
        markers.add(googleMarker);
        cafeIds.add(marker.cafeId);
    }

    private final ValueAnimator animator = ValueAnimator.ofFloat(0, 1000);

    public void StartAnimation(String cafeId){
        Marker googleMarker = FindMarker(cafeId);
        if (googleMarker == null) return;

        for (Marker marker : markers)
        {
            if (!marker.equals(googleMarker))
                marker.setVisible(false);
        }

        animator.addUpdateListener(
                valueAnimator -> googleMarker.setAlpha((float)animator.getAnimatedValue() / 1000f));

        animator.start();
    }

    private Marker FindMarker(String cafeId) {
        for (int i = 0; i < markers.size(); ++i)
        {
            if (cafeIds.get(i).equals(cafeId))
                return markers.get(i);
        }
        return null;
    }

    public void StopAnimation()
    {
        for (Marker marker : markers)
            marker.setVisible(true);

        animator.end();
        animator.removeAllUpdateListeners();
    }
}
