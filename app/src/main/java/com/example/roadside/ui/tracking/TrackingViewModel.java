package com.example.roadside.ui.tracking;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roadside.data.models.Request;
import com.example.roadside.data.repository.RequestRepository;

/** Backs TrackingActivity: observes live status of the active request. */
public class TrackingViewModel extends AndroidViewModel {

    private final RequestRepository requestRepository;

    public TrackingViewModel(@NonNull Application application) {
        super(application);
        this.requestRepository = new RequestRepository(application);
    }

    public LiveData<Request> observeRequest(String requestId) {
        return requestRepository.observeRequest(requestId);
    }

    public void cancelRequest(String requestId, RequestRepository.RequestCallback<Void> callback) {
        requestRepository.cancelRequest(requestId, callback);
    }
}
