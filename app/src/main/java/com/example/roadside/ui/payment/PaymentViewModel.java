package com.example.roadside.ui.payment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.roadside.data.models.Payment;
import com.example.roadside.data.repository.PaymentRepository;

/** Backs PaymentActivity: cost breakdown, method selection, confirmation. */
public class PaymentViewModel extends AndroidViewModel {

    private final PaymentRepository paymentRepository = new PaymentRepository();

    private final MutableLiveData<Payment> payment = new MutableLiveData<>(new Payment());
    private final MutableLiveData<Boolean> confirmed = new MutableLiveData<>(false);
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> processing = new MutableLiveData<>(false);

    public PaymentViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Payment> getPayment() { return payment; }
    public LiveData<Boolean> getConfirmed() { return confirmed; }
    public LiveData<String> getErrorMessage() { return errorMessage; }
    public LiveData<Boolean> getProcessing() { return processing; }

    public void selectMethod(Payment.Method method) {
        Payment p = payment.getValue();
        if (p != null) {
            p.setMethod(method);
            payment.setValue(p);
        }
    }

    public void confirmPayment() {
        Payment p = payment.getValue();
        if (p == null || p.getId() == null) {
            errorMessage.setValue("Chưa có thông tin thanh toán.");
            return;
        }
        processing.setValue(true);
        paymentRepository.confirmPayment(p.getId(), new PaymentRepository.PaymentCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
                processing.postValue(false);
                confirmed.postValue(true);
            }

            @Override
            public void onError(String message) {
                processing.postValue(false);
                errorMessage.postValue(message);
            }
        });
    }
}
