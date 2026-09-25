package com.example.roadside.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.roadside.data.api.ApiClient;
import com.example.roadside.data.api.ApiService;
import com.example.roadside.data.db.AppDatabase;
import com.example.roadside.data.models.Request;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/** Creates and tracks roadside-assistance requests (RequestFormActivity, TrackingActivity). */
public class RequestRepository {

    public interface RequestCallback<T> {
        void onSuccess(T result);
        void onError(String message);
    }

    private final ApiService apiService;
    private final AppDatabase database;

    public RequestRepository(Context context) {
        this.apiService = ApiClient.getApiService();
        this.database = AppDatabase.getInstance(context);
    }

    public void createRequest(Request request, RequestCallback<Request> callback) {
        apiService.createRequest(request).enqueue(new Callback<Request>() {
            @Override
            public void onResponse(Call<Request> call, Response<Request> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Request created = response.body();
                    new Thread(() -> database.requestDao().insert(created)).start();
                    callback.onSuccess(created);
                } else {
                    callback.onError("Không thể tạo yêu cầu cứu hộ.");
                }
            }

            @Override
            public void onFailure(Call<Request> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public LiveData<Request> observeRequest(String requestId) {
        return database.requestDao().getRequest(requestId);
    }

    public LiveData<List<Request>> observeAllRequests() {
        return database.requestDao().getAllRequests();
    }

    public void cancelRequest(String requestId, RequestCallback<Void> callback) {
        apiService.cancelRequest(requestId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onError("Không thể hủy yêu cầu.");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }
}
