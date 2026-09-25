package com.example.roadside;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.roadside.ui.auth.LoginActivity;
import com.example.roadside.ui.home.HomeActivity;
import com.example.roadside.utils.SharedPrefsHelper;

/** Launcher activity: routes straight to Home if a session exists, else Login. */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean loggedIn = new SharedPrefsHelper(this).isLoggedIn();
        Intent intent = new Intent(this, loggedIn ? HomeActivity.class : LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
