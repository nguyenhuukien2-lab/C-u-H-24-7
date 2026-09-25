package com.example.roadside.data.repository;

import com.example.roadside.data.api.ApiClient;
import com.example.roadside.data.api.ApiService;
import com.example.roadside.data.models.Rating;
import com.example.roadside.data.models.Request;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/** Fetches past requests and submits ratings for HistoryActivity. */
public class HistoryRepository {

    public interface HistoryCallback<T> {
        void onSuccess(T result);
        void onError(String message);
    }

    private final ApiService apiService = ApiClient.getApiService();

    public void getRequestHistory(String userId, HistoryCallback<List<Request>> callback) {
        apiService.getRequestHistory(userId).enqueue(new Callback<List<Request>>() {
            @Override
            public void onResponse(Call<List<Request>> call, Response<List<Request>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Không thể tải lịch sử cứu hộ.");
                }
            }

            @Override
            public void onFailure(Call<List<Request>> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public void submitRating(Rating rating, HistoryCallback<Rating> callback) {
        apiService.submitRating(rating).enqueue(new Callback<Rating>() {
            @Override
            public void onResponse(Call<Rating> call, Response<Rating> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Không thể gửi đánh giá.");
                }
            }

            @Override
            public void onFailure(Call<Rating> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }
}
