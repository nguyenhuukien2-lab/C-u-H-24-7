package com.example.roadside.ui.auth;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.roadside.R;

/**
 * OTP-verification / profile-completion step shown after LoginActivity
 * sends an OTP. Reuses AuthViewModel.verifyOtp(phone, code).
 */
public class RegisterActivity extends AppCompatActivity {

    public static final String EXTRA_PHONE_NUMBER = "extra_phone_number";

    private AuthViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Reuses activity_login.xml's phone-entry chrome; swap in a dedicated
        // OTP-code layout (e.g. activity_otp_verify.xml) when ready.
        setContentView(R.layout.activity_login);

        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        viewModel.getErrorMessage().observe(this, message -> {
            if (message != null) {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
