package com.example.roadside.ui.auth;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.roadside.data.models.User;
import com.example.roadside.data.repository.AuthRepository;

/** Backs LoginActivity/RegisterActivity: OTP request/verify state. */
public class AuthViewModel extends AndroidViewModel {

    private final AuthRepository authRepository;

    private final MutableLiveData<Boolean> otpSent = new MutableLiveData<>(false);
    private final MutableLiveData<User> loggedInUser = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>(false);

    public AuthViewModel(@NonNull Application application) {
        super(application);
        this.authRepository = new AuthRepository(application);
    }

    public LiveData<Boolean> getOtpSent() { return otpSent; }
    public LiveData<User> getLoggedInUser() { return loggedInUser; }
    public LiveData<String> getErrorMessage() { return errorMessage; }
    public LiveData<Boolean> getLoading() { return loading; }

    public void sendOtp(String phoneNumber) {
        loading.setValue(true);
        authRepository.requestOtp(phoneNumber, new AuthRepository.AuthCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
                loading.postValue(false);
                otpSent.postValue(true);
            }

            @Override
            public void onError(String message) {
                loading.postValue(false);
                errorMessage.postValue(message);
            }
        });
    }

    public void verifyOtp(String phoneNumber, String otpCode) {
        loading.setValue(true);
        authRepository.verifyOtp(phoneNumber, otpCode, new AuthRepository.AuthCallback<User>() {
            @Override
            public void onSuccess(User result) {
                loading.postValue(false);
                loggedInUser.postValue(result);
            }

            @Override
            public void onError(String message) {
                loading.postValue(false);
                errorMessage.postValue(message);
            }
        });
    }

    public boolean isLoggedIn() {
        return authRepository.isLoggedIn();
    }
}
