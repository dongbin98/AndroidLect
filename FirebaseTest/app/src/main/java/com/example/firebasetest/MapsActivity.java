package com.example.firebasetest;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.firebasetest.databinding.ActivityMapsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference();
    int count;
    LatLng[] array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        databaseReference.child("Member").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    count = (int) snapshot.getChildrenCount();
                    array = new LatLng[count];
                    LatLng hi;
                    for (int i = 1; i < count; i++) {
                        if(snapshot.child(Integer.toString(i)).exists()) {
                            Bus read = snapshot.child(Integer.toString(i)).getValue(Bus.class);
                            array[i - 1] = new LatLng(Double.parseDouble(read.gpsY), Double.parseDouble(read.gpsX));
                            Log.d("array number", Integer.toString(i - 1));
                            Log.d("location X", read.gpsX);
                            Log.d("location Y", read.gpsY);
                            mMap.addMarker(new MarkerOptions().position(array[i - 1]).title(read.stationNm));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(array[i - 1], 15f));
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("FireBaseData", "loadPost:onCancelled", error.toException());
            }
        });
        //Camera3 = mMap.addMarker(new MarkerOptions().position(Location3).icon(BitmapDescriptorFactory.fromResource(R.drawable.cam)));
        //Camera3.setTag(3);
        mMap.setOnMarkerClickListener(this);
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