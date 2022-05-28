package com.example.mapapp2;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.mapapp2.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.Arrays;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private static final LatLng SEOUL = new LatLng(37.566535, 126.977969);
    private static final LatLng DAEJEON = new LatLng(36.350412, 127.384548);
    private static final LatLng SUWEON = new LatLng(37.263573, 127.-28601);
    private static final LatLng BUSAN = new LatLng(35.179554, 129.075642);
    private static final LatLng KWANGJU = new LatLng(35.159545, 126.852601);

    private Marker mSeoul;
    private Marker mDaejeon;
    private Marker mBusan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

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

        mMap.addPolyline(new PolylineOptions().add(SEOUL, BUSAN, DAEJEON));
        mMap.addPolygon(new PolygonOptions().addAll(createRectangle(SEOUL, 1, 1))
        .addHole(createRectangle(SUWEON, 0.3, 0.3))
        .fillColor(Color.YELLOW).strokeColor(Color.BLUE).strokeWidth(5));
//        mSeoul = mMap.addMarker(new MarkerOptions().position(SEOUL).title("SEOUL"));
//        mSeoul.setTag(0);
//        mDaejeon = mMap.addMarker(new MarkerOptions().position(DAEJEON).title("DAEJEON").icon(BitmapDescriptorFactory.fromResource(R.drawable.flag)));
//        mDaejeon.setTag(0);
//        mBusan = mMap.addMarker(new MarkerOptions().position(BUSAN).title("BUSAN"));
//        mBusan.setTag(0);

//        mMap.setOnMarkerClickListener(this);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 7));
    }
    private List<LatLng> createRectangle(LatLng center, double halfWidth, double halfHeight) {
        return Arrays.asList(
                new LatLng(center.latitude - halfHeight, center.longitude - halfWidth),
                new LatLng(center.latitude - halfHeight, center.longitude + halfWidth),
                new LatLng(center.latitude + halfHeight, center.longitude + halfWidth),
                new LatLng(center.latitude + halfHeight, center.longitude - halfWidth),
                new LatLng(center.latitude - halfHeight, center.longitude - halfWidth));
    }
//    @Override
//    public boolean onMarkerClick(final Marker marker) {
//        Integer clickCount = (Integer) marker.getTag();
//        if(clickCount != null) {
//            clickCount += 1;
//            marker.setTag(clickCount);
//            Toast.makeText(this, marker.getTitle() + " 가 클릭되었음, 클릭횟수: " + clickCount, Toast.LENGTH_SHORT).show();
//        }
//        return false;
//    }
}