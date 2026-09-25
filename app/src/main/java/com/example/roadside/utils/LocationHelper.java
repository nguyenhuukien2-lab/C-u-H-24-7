package com.example.roadside.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

/**
 * Wraps FusedLocationProviderClient so activities can request the customer's
 * live GPS position for the SOS button and request-form map preview.
 */
public class LocationHelper {

    public interface LocationCallback {
        void onLocationResult(double latitude, double longitude);
        void onLocationUnavailable();
    }

    private final FusedLocationProviderClient fusedLocationClient;

    public LocationHelper(Context context) {
        this.fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
    }

    public boolean hasLocationPermission(Context context) {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }

    public void requestLocationPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                Constants.REQUEST_CODE_LOCATION_PERMISSION);
    }

    @SuppressWarnings("MissingPermission")
    public void getCurrentLocation(Context context, LocationCallback callback) {
        if (!hasLocationPermission(context)) {
            callback.onLocationUnavailable();
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener((OnSuccessListener<Location>) location -> {
                    if (location != null) {
                        callback.onLocationResult(location.getLatitude(), location.getLongitude());
                    } else {
                        callback.onLocationUnavailable();
                    }
                })
                .addOnFailureListener(e -> callback.onLocationUnavailable());
    }
}
