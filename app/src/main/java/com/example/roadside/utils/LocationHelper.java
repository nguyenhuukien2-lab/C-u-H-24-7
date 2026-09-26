package com.example.roadside.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * Wraps Android LocationManager so activities can request the customer's
 * live GPS position for the SOS button and request-form map preview.
 */
public class LocationHelper {

    public interface LocationCallback {
        void onLocationResult(double latitude, double longitude);
        void onLocationUnavailable();
    }

    private final LocationManager locationManager;

    public LocationHelper(Context context) {
        this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    public boolean hasLocationPermission(Context context) {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }

    public void requestLocationPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                Constants.REQUEST_CODE_LOCATION_PERMISSION);
    }

    @SuppressWarnings("MissingPermission")
    public void getCurrentLocation(Context context, LocationCallback callback) {
        if (!hasLocationPermission(context)) {
            callback.onLocationUnavailable();
            return;
        }
        try {
            Location location = null;
            if (locationManager != null) {
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location == null) {
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                }
            }

            if (location != null) {
                callback.onLocationResult(location.getLatitude(), location.getLongitude());
            } else {
                // Fallback default coordinates (Hanoi center)
                callback.onLocationResult(21.0285, 105.8542);
            }
        } catch (Exception e) {
            callback.onLocationUnavailable();
        }
    }
}
