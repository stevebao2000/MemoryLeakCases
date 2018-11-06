package com.steve.memoryleakcases;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class LocationActivity extends AppCompatActivity implements LocationListener {

    private static final int PARAM_ACCESS_COARSE_LOCATION = 2329; // any integer.
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
        checkAndRequestPermission(Manifest.permission.ACCESS_COARSE_LOCATION, PARAM_ACCESS_COARSE_LOCATION);

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                TimeUnit.MINUTES.toMillis(5), 100, this);
    }

    private void checkAndRequestPermission(String requestPermission, int permission_param) {

        if(android.os.Build.VERSION.SDK_INT >=android.os.Build.VERSION_CODES.M) {
            if (checkSelfPermission(requestPermission) == PackageManager.PERMISSION_DENIED) {
                String[] permissions = {requestPermission};
                requestPermissions(permissions, permission_param);
            }
        }
    }

    @Override
    protected void onDestroy() {
        // prevent memory leak. remove or stop the service before the activity gets destroyed.
        // locationManager.removeUpdates(this);
        super.onDestroy();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PARAM_ACCESS_COARSE_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                    Toast.makeText(this, "Location access permission denided", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
