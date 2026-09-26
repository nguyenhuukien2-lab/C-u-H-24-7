package com.example.roadside.data.api;

import com.example.roadside.data.models.Payment;
import com.example.roadside.data.models.Provider;
import com.example.roadside.data.models.Rating;
import com.example.roadside.data.models.Request;
import com.example.roadside.data.models.User;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/** REST contract between CustomerApp and the ResQ247 dispatch backend. */
public interface ApiService {

    // --- Auth ---
    @POST("auth/otp/request")
    Call<Void> requestOtp(@Body String phoneNumberJson);

    @POST("auth/otp/verify")
    Call<User> verifyOtp(@Body String otpPayloadJson);

    @POST("auth/login")
    Call<User> login(@Body JsonObject credentials);

    @POST("auth/register")
    Call<User> register(@Body User user);

    // --- Requests ---
    @POST("requests")
    Call<Request> createRequest(@Body Request request);

    @GET("requests/{id}")
    Call<Request> getRequest(@Path("id") String requestId);

    @PUT("requests/{id}/cancel")
    Call<Void> cancelRequest(@Path("id") String requestId);

    @GET("users/{userId}/requests")
    Call<List<Request>> getRequestHistory(@Path("userId") String userId);

    // --- Providers ---
    @GET("providers/nearby")
    Call<List<Provider>> getNearbyProviders(@Query("lat") double lat,
                                             @Query("lng") double lng,
                                             @Query("vehicleType") String vehicleType);

    @PUT("requests/{id}/provider")
    Call<Void> assignProvider(@Path("id") String requestId, @Query("providerId") String providerId);

    // --- Payment ---
    @POST("payments")
    Call<Payment> createPayment(@Body Payment payment);

    @PUT("payments/{id}/confirm")
    Call<Void> confirmPayment(@Path("id") String paymentId);

    // --- Rating ---
    @POST("ratings")
    Call<Rating> submitRating(@Body Rating rating);
}
