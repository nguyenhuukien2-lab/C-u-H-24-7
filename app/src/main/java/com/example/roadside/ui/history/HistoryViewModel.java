package com.example.roadside.ui.history;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.roadside.data.models.Rating;
import com.example.roadside.data.models.Request;
import com.example.roadside.data.repository.HistoryRepository;

import java.util.List;

/** Backs HistoryActivity: past requests and pending-rating submission. */
public class HistoryViewModel extends AndroidViewModel {

    private final HistoryRepository historyRepository = new HistoryRepository();

    private final MutableLiveData<List<Request>> history = new MutableLiveData<>();
    private final MutableLiveData<Boolean> ratingSubmitted = new MutableLiveData<>(false);
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>(false);

    public HistoryViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Request>> getHistory() { return history; }
    public LiveData<Boolean> getRatingSubmitted() { return ratingSubmitted; }
    public LiveData<String> getErrorMessage() { return errorMessage; }
    public LiveData<Boolean> getLoading() { return loading; }

    public void loadHistory(String userId) {
        loading.setValue(true);
        historyRepository.getRequestHistory(userId, new HistoryRepository.HistoryCallback<List<Request>>() {
            @Override
            public void onSuccess(List<Request> result) {
                loading.postValue(false);
                history.postValue(result);
            }

            @Override
            public void onError(String message) {
                loading.postValue(false);
                errorMessage.postValue(message);
            }
        });
    }

    public void submitRating(Rating rating) {
        historyRepository.submitRating(rating, new HistoryRepository.HistoryCallback<Rating>() {
            @Override
            public void onSuccess(Rating result) {
                ratingSubmitted.postValue(true);
            }

            @Override
            public void onError(String message) {
                errorMessage.postValue(message);
            }
        });
    }
}
