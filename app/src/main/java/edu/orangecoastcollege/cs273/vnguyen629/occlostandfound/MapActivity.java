package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static final double latitude = 33.669658;
    public static final double longtitude = -117.909386;

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

        //Use Latitude and Longtitude to create marker on map
        mMap = googleMap;
        LatLng coordinate = new LatLng(latitude,longtitude);
        mMap.addMarker(new MarkerOptions().position(coordinate).title(getString(R.string.office_title)));

        //Change the camera view to the marker
        CameraPosition cameraPosition = new CameraPosition.Builder().target(coordinate).zoom(14.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.moveCamera(cameraUpdate);
    }
}
