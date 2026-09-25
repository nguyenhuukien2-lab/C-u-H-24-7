package com.example.roadside.data.repository;

import android.content.Context;

import com.example.roadside.data.api.ApiClient;
import com.example.roadside.data.api.ApiService;
import com.example.roadside.data.db.AppDatabase;
import com.example.roadside.data.models.User;
import com.example.roadside.utils.SharedPrefsHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/** Handles OTP login/registration and session persistence for LoginActivity / AuthViewModel. */
public class AuthRepository {

    public interface AuthCallback<T> {
        void onSuccess(T result);
        void onError(String message);
    }

    private final ApiService apiService;
    private final AppDatabase database;
    private final SharedPrefsHelper prefs;

    public AuthRepository(Context context) {
        this.apiService = ApiClient.getApiService();
        this.database = AppDatabase.getInstance(context);
        this.prefs = new SharedPrefsHelper(context);
    }

    public void requestOtp(String phoneNumber, AuthCallback<Void> callback) {
        String body = "{\"phoneNumber\":\"" + phoneNumber + "\"}";
        apiService.requestOtp(body).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onError("Không thể gửi mã OTP. Vui lòng thử lại.");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public void verifyOtp(String phoneNumber, String otpCode, AuthCallback<User> callback) {
        String body = "{\"phoneNumber\":\"" + phoneNumber + "\",\"otp\":\"" + otpCode + "\"}";
        apiService.verifyOtp(body).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    User user = response.body();
                    prefs.saveUserId(user.getId());
                    prefs.savePhoneNumber(user.getPhoneNumber());
                    new Thread(() -> database.userDao().insert(user)).start();
                    callback.onSuccess(user);
                } else {
                    callback.onError("Mã OTP không chính xác.");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public boolean isLoggedIn() {
        return prefs.isLoggedIn();
    }

    public void logout() {
        prefs.clearSession();
        new Thread(() -> database.userDao().clear()).start();
    }
}
