package com.example.roadside.ui.request;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.roadside.data.models.Request;
import com.example.roadside.data.repository.RequestRepository;

import java.util.UUID;

/** Backs RequestFormActivity: builds and submits a new Request. */
public class RequestViewModel extends AndroidViewModel {

    private final RequestRepository requestRepository;

    private final MutableLiveData<Request> draftRequest =
            new MutableLiveData<>(new Request());
    private final MutableLiveData<Request> createdRequest = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> submitting = new MutableLiveData<>(false);

    public RequestViewModel(@NonNull Application application) {
        super(application);
        this.requestRepository = new RequestRepository(application);
    }

    public LiveData<Request> getDraftRequest() { return draftRequest; }
    public LiveData<Request> getCreatedRequest() { return createdRequest; }
    public LiveData<String> getErrorMessage() { return errorMessage; }
    public LiveData<Boolean> getSubmitting() { return submitting; }

    public void setVehicleType(String vehicleType) {
        Request r = draftRequest.getValue();
        if (r != null) {
            r.setVehicleType(vehicleType);
            draftRequest.setValue(r);
        }
    }

    public void setVehicleInfo(String plateNumber, String vehicleModel) {
        Request r = draftRequest.getValue();
        if (r != null) {
            r.setPlateNumber(plateNumber);
            r.setVehicleModel(vehicleModel);
            draftRequest.setValue(r);
        }
    }

    public void setIssueType(String issueType) {
        Request r = draftRequest.getValue();
        if (r != null) {
            r.setIssueType(issueType);
            draftRequest.setValue(r);
        }
    }

    public void setLocation(double lat, double lng, String address, String note) {
        Request r = draftRequest.getValue();
        if (r != null) {
            r.setLatitude(lat);
            r.setLongitude(lng);
            r.setAddress(address);
            r.setNote(note);
            draftRequest.setValue(r);
        }
    }

    public void submitRequest() {
        Request r = draftRequest.getValue();
        if (r == null) return;
        r.setId(UUID.randomUUID().toString());
        r.setStatus(Request.Status.PENDING);
        r.setCreatedAt(System.currentTimeMillis());

        submitting.setValue(true);
        requestRepository.createRequest(r, new RequestRepository.RequestCallback<Request>() {
            @Override
            public void onSuccess(Request result) {
                submitting.postValue(false);
                createdRequest.postValue(result);
            }

            @Override
            public void onError(String message) {
                submitting.postValue(false);
                errorMessage.postValue(message);
            }
        });
    }
}
