package com.example.roadside.ui.providers;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.roadside.data.models.Provider;
import com.example.roadside.data.repository.ProviderRepository;

import java.util.List;

/** Backs ProviderListActivity: nearby-provider search and selection. */
public class ProvidersViewModel extends AndroidViewModel {

    public enum SortOrder { NEAREST, RATING, PRICE }

    private final ProviderRepository providerRepository = new ProviderRepository();

    private final MutableLiveData<List<Provider>> providers = new MutableLiveData<>();
    private final MutableLiveData<SortOrder> sortOrder = new MutableLiveData<>(SortOrder.NEAREST);
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>(false);
    private final MutableLiveData<String> assignedProviderId = new MutableLiveData<>();

    public ProvidersViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Provider>> getProviders() { return providers; }
    public LiveData<SortOrder> getSortOrder() { return sortOrder; }
    public LiveData<String> getErrorMessage() { return errorMessage; }
    public LiveData<Boolean> getLoading() { return loading; }
    public LiveData<String> getAssignedProviderId() { return assignedProviderId; }

    public void loadNearbyProviders(double lat, double lng, String vehicleType) {
        loading.setValue(true);
        providerRepository.getNearbyProviders(lat, lng, vehicleType,
                new ProviderRepository.ProviderCallback<List<Provider>>() {
                    @Override
                    public void onSuccess(List<Provider> result) {
                        loading.postValue(false);
                        providers.postValue(result);
                    }

                    @Override
                    public void onError(String message) {
                        loading.postValue(false);
                        errorMessage.postValue(message);
                    }
                });
    }

    public void setSortOrder(SortOrder order) {
        sortOrder.setValue(order);
    }

    public void chooseProvider(String requestId, String providerId) {
        providerRepository.assignProvider(requestId, providerId,
                new ProviderRepository.ProviderCallback<Void>() {
                    @Override
                    public void onSuccess(Void result) {
                        assignedProviderId.postValue(providerId);
                    }

                    @Override
                    public void onError(String message) {
                        errorMessage.postValue(message);
                    }
                });
    }
}
