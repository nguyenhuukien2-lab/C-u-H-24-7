package com.example.roadside.data.repository;

import com.example.roadside.data.api.ApiClient;
import com.example.roadside.data.api.ApiService;
import com.example.roadside.data.models.Payment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/** Creates and confirms payments for PaymentActivity. */
public class PaymentRepository {

    public interface PaymentCallback<T> {
        void onSuccess(T result);
        void onError(String message);
    }

    private final ApiService apiService = ApiClient.getApiService();

    public void createPayment(Payment payment, PaymentCallback<Payment> callback) {
        apiService.createPayment(payment).enqueue(new Callback<Payment>() {
            @Override
            public void onResponse(Call<Payment> call, Response<Payment> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Không thể khởi tạo thanh toán.");
                }
            }

            @Override
            public void onFailure(Call<Payment> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public void confirmPayment(String paymentId, PaymentCallback<Void> callback) {
        apiService.confirmPayment(paymentId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onError("Xác nhận thanh toán thất bại.");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }
}
