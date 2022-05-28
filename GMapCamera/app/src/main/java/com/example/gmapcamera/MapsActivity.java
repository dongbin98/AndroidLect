package com.example.gmapcamera;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.gmapcamera.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback {

    private GoogleMap mMap;
    private static final LatLng Location1 = new LatLng(37.63717057, 127.0290418);
    private static final LatLng Location2 = new LatLng(37.65154554, 127.0122203);
    private static final LatLng Location3 = new LatLng(37.612849, 127.034268);
    private static final LatLng center = new LatLng(37.63, 127.03 );
    private Marker Camera1;
    private Marker Camera2;
    private Marker Camera3;

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
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        Camera1 = mMap.addMarker(new MarkerOptions().position(Location1).icon(BitmapDescriptorFactory.fromResource(R.drawable.cam)));
        Camera1.setTag(1);
        Camera2 = mMap.addMarker(new MarkerOptions().position(Location2).icon(BitmapDescriptorFactory.fromResource(R.drawable.cam)));
        Camera2.setTag(2);
        Camera3 = mMap.addMarker(new MarkerOptions().position(Location3).icon(BitmapDescriptorFactory.fromResource(R.drawable.cam)));
        Camera3.setTag(3);
        mMap.setOnMarkerClickListener(this);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, 13.5f));
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        Integer which = (Integer) marker.getTag();
        if(which == 1)
            Toast.makeText(this, "카메라 1", Toast.LENGTH_SHORT).show();
        else if(which == 2)
            Toast.makeText(this, "카메라 2", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "카메라 3", Toast.LENGTH_SHORT).show();
        return false;
    }
}