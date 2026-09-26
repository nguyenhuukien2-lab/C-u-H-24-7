package com.example.roadside.data.repository;

import android.content.Context;

import com.example.roadside.data.api.ApiClient;
import com.example.roadside.data.models.Provider;
import com.example.roadside.data.models.Request;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Connects Android app screens directly to the Supabase backend via Retrofit & ApiService.
 */
public class SupabaseRepository {

    public interface ApiCallback<T> {
        void onSuccess(T result);
        void onError(String error);
    }

    private final Context context;

    public SupabaseRepository(Context context) {
        this.context = context;
    }

    // 1. Request OTP
    public void requestOtp(String phone, ApiCallback<Void> callback) {
        JsonObject json = new JsonObject();
        json.addProperty("phone", phone);
        ApiClient.getApiService().requestOtp(json.toString()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onError("Lỗi gửi OTP từ Supabase");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    // 2. Create Rescue Request
    public void createRequest(Request request, ApiCallback<Request> callback) {
        ApiClient.getApiService().createRequest(request).enqueue(new Callback<Request>() {
            @Override
            public void onResponse(Call<Request> call, Response<Request> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Không thể tạo yêu cầu cứu hộ trên Supabase");
                }
            }

            @Override
            public void onFailure(Call<Request> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    // 3. Get Nearby Providers
    public void getNearbyProviders(double lat, double lng, String vehicleType, ApiCallback<List<Provider>> callback) {
        ApiClient.getApiService().getNearbyProviders(lat, lng, vehicleType).enqueue(new Callback<List<Provider>>() {
            @Override
            public void onResponse(Call<List<Provider>> call, Response<List<Provider>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Không tìm thấy đội cứu hộ quanh đây");
                }
            }

            @Override
            public void onFailure(Call<List<Provider>> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }
}
