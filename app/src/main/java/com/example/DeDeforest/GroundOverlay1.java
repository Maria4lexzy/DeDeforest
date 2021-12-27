package com.example.DeDeforest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import DeDeforest.R;

public class GroundOverlay1 extends  AppCompatActivity
        implements
        SeekBar.OnSeekBarChangeListener,
        OnMapReadyCallback,
        GoogleMap.OnMyLocationButtonClickListener,
        ActivityCompat.OnRequestPermissionsResultCallback,
        GoogleMap.OnMyLocationClickListener,
        GoogleMap.OnGroundOverlayClickListener {
    private static final int TRANSPARENCY_MAX = 100;

    private static final LatLng NEWARK = new LatLng(40.714086, -74.228697);
    private LatLng centerLocation;

    private static final LatLng NEAR_NEWARK =
            new LatLng(NEWARK.latitude - 0.001, NEWARK.longitude - 0.025);

    private final List<BitmapDescriptor> images = new ArrayList<BitmapDescriptor>();

    private GroundOverlay groundOverlay;


    private SeekBar transparencyBar;

    private int currentEntry = 0;
    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ground_overlay1);

        transparencyBar = findViewById(R.id.transparencySeekBar);
        transparencyBar.setMax(TRANSPARENCY_MAX);
        transparencyBar.setProgress(0);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap=map;
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
        setOverlayImage(mMap);
        enableMyLocation();

        // Override the default content description on the view, for accessibility mode.
        // Ideally this string would be localised.
        map.setContentDescription("Google Map with ground overlay.");
    }
public void setOverlayImage(GoogleMap mMap){
    // Register a listener to respond to clicks on GroundOverlays.
    mMap.setOnGroundOverlayClickListener(this);

    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(NEWARK, 11));

    images.clear();
    images.add(BitmapDescriptorFactory.fromResource(R.drawable.newark_nj_1922));
    images.add(BitmapDescriptorFactory.fromResource(R.drawable.newark_prudential_sunny));

    // Add a large overlay at Newark on top of the smaller overlay.
    groundOverlay = mMap.addGroundOverlay(new GroundOverlayOptions()
            .image(images.get(currentEntry)).anchor(0, 1)
            .position(NEWARK, 8600f, 6500f)
            .bearing(0)
            .clickable(true));
    transparencyBar.setOnSeekBarChangeListener(this);
}
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (groundOverlay != null) {
            groundOverlay.setTransparency((float) progress / (float) TRANSPARENCY_MAX);
        }
    }

    public void switchImage(View view) {
        currentEntry = (currentEntry + 1) % images.size();
        groundOverlay.setImage(images.get(currentEntry));
    }

    /**
     * Toggles the visibility between 100% and 50% when a {@link GroundOverlay} is clicked.
     */
    @Override
    public void onGroundOverlayClick(GroundOverlay groundOverlay) {
        // Toggle transparency value between 0.0f and 0.5f. Initial default value is 0.0f.
        groundOverlay.setTransparency(0.5f - groundOverlay.getTransparency());
    }
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
            }
        } else {
            String myPermission[]={"android.permission.ACCESS_FINE_LOCATION"};
            // Permission to access the location is missing. Show rationale and request permission
            ActivityCompat.requestPermissions(this,myPermission, 200);
        }
    }
    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }
    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }


    }