package com.example.roadside.ui.payment;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.roadside.R;
import com.example.roadside.data.models.Payment;
import com.example.roadside.utils.Constants;
import com.example.roadside.utils.PaymentHelper;

/** Cost breakdown + payment-method confirmation; matches activity_payment.xml. */
public class PaymentActivity extends AppCompatActivity {

    private PaymentViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        String requestId = getIntent().getStringExtra(Constants.EXTRA_REQUEST_ID);
        viewModel = new ViewModelProvider(this).get(PaymentViewModel.class);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
        bindPaymentMethods();

        findViewById(R.id.btnConfirmPayment).setOnClickListener(v -> viewModel.confirmPayment());

        observeViewModel();
        // TODO: load the real Payment for requestId via PaymentRepository and
        // push it into the ViewModel instead of relying on the static preview.
    }

    private void bindPaymentMethods() {
        findViewById(R.id.methodMomo).setOnClickListener(v -> selectMethod(Payment.Method.MOMO));
        findViewById(R.id.methodVnpay).setOnClickListener(v -> selectMethod(Payment.Method.VNPAY));
        findViewById(R.id.methodCard).setOnClickListener(v -> selectMethod(Payment.Method.CARD));
        findViewById(R.id.methodCash).setOnClickListener(v -> selectMethod(Payment.Method.CASH));
    }

    private void selectMethod(Payment.Method method) {
        viewModel.selectMethod(method);
        findViewById(R.id.methodMomo).setSelected(method == Payment.Method.MOMO);
        findViewById(R.id.methodVnpay).setSelected(method == Payment.Method.VNPAY);
        findViewById(R.id.methodCard).setSelected(method == Payment.Method.CARD);
        findViewById(R.id.methodCash).setSelected(method == Payment.Method.CASH);
    }

    private void observeViewModel() {
        viewModel.getPayment().observe(this, payment -> {
            if (payment == null) return;
            TextView tvTotal = findViewById(R.id.tvTotalAmount);
            tvTotal.setText(PaymentHelper.formatVnd(PaymentHelper.calculateTotal(payment)));
        });

        viewModel.getConfirmed().observe(this, confirmed -> {
            if (Boolean.TRUE.equals(confirmed)) {
                Toast.makeText(this, "Thanh toán thành công!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        viewModel.getErrorMessage().observe(this, message -> {
            if (message != null) {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
