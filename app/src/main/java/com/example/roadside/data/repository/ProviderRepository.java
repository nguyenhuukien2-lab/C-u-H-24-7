package com.example.roadside.data.repository;

import com.example.roadside.data.api.ApiClient;
import com.example.roadside.data.api.ApiService;
import com.example.roadside.data.models.Provider;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/** Looks up and assigns rescue-team providers for ProviderListActivity. */
public class ProviderRepository {

    public interface ProviderCallback<T> {
        void onSuccess(T result);
        void onError(String message);
    }

    private final ApiService apiService = ApiClient.getApiService();

    public void getNearbyProviders(double lat, double lng, String vehicleType,
                                   ProviderCallback<List<Provider>> callback) {
        apiService.getNearbyProviders(lat, lng, vehicleType).enqueue(new Callback<List<Provider>>() {
            @Override
            public void onResponse(Call<List<Provider>> call, Response<List<Provider>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Không tìm thấy đội cứu hộ phù hợp.");
                }
            }

            @Override
            public void onFailure(Call<List<Provider>> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public void assignProvider(String requestId, String providerId, ProviderCallback<Void> callback) {
        apiService.assignProvider(requestId, providerId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onError("Không thể chọn đội cứu hộ này.");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }
}
