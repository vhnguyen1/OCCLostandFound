package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final double LATITUDE = 33.669663;
    private static final double LONGITUDE = -117.909362;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment googleMapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.googleMapFragment);

        googleMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker at the ASOCC office
        LatLng coordinate = new LatLng(LATITUDE, LONGITUDE);
        mMap.addMarker(new MarkerOptions().position(coordinate)
                .title(getString(R.string.office_title))).showInfoWindow();

        //Move the default camera position to the marker
        CameraPosition cameraPosition =
                new CameraPosition.Builder().target(coordinate).zoom(14.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.moveCamera(cameraUpdate);
    }

    public void showCampusMap(View view) {
        startActivity(new Intent(MapActivity.this, CampusMapActivity.class));
    }
}
