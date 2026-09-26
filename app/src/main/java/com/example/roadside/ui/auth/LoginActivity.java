package com.example.roadside.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.roadside.R;
import com.example.roadside.ui.home.HomeActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText etPhone = findViewById(R.id.etPhone);
        findViewById(R.id.btnContinueOtp).setOnClickListener(v -> {
            String phone = etPhone.getText().toString().trim();
            if (phone.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, "Gửi mã OTP thành công!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        });
    }
}
