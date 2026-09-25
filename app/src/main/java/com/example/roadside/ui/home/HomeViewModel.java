package com.example.roadside.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.roadside.utils.LocationHelper;

/** Backs HomeActivity: current GPS position shown above the SOS button. */
public class HomeViewModel extends AndroidViewModel {

    private final LocationHelper locationHelper;

    private final MutableLiveData<Double> latitude = new MutableLiveData<>();
    private final MutableLiveData<Double> longitude = new MutableLiveData<>();
    private final MutableLiveData<String> addressLine = new MutableLiveData<>();
    private final MutableLiveData<Integer> nearbyProviderCount = new MutableLiveData<>(0);

    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.locationHelper = new LocationHelper(application);
    }

    public LiveData<Double> getLatitude() { return latitude; }
    public LiveData<Double> getLongitude() { return longitude; }
    public LiveData<String> getAddressLine() { return addressLine; }
    public LiveData<Integer> getNearbyProviderCount() { return nearbyProviderCount; }

    public void refreshLocation() {
        locationHelper.getCurrentLocation(getApplication(), new LocationHelper.LocationCallback() {
            @Override
            public void onLocationResult(double lat, double lng) {
                latitude.postValue(lat);
                longitude.postValue(lng);
            }

            @Override
            public void onLocationUnavailable() {
                // Keep last known address; caller may prompt for permission.
            }
        });
    }

    public void setAddressLine(String address) {
        addressLine.setValue(address);
    }
}
