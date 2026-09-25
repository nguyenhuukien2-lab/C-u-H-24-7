package com.example.roadside.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.roadside.R;
import com.example.roadside.ui.request.RequestFormActivity;
import com.example.roadside.utils.Constants;

/** Home / SOS dashboard; matches activity_home.xml. */
public class HomeActivity extends AppCompatActivity {

    private HomeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        findViewById(R.id.btnRescueNow).setOnClickListener(v -> startRequestFlow());
        findViewById(R.id.btnHotline).setOnClickListener(v -> callHotline());
        findViewById(R.id.btnChangeLocation).setOnClickListener(v -> viewModel.refreshLocation());

        TextView tvLocation = findViewById(R.id.tvCurrentLocation);
        viewModel.getAddressLine().observe(this, tvLocation::setText);

        viewModel.refreshLocation();

        bindBottomNav();
    }

    private void startRequestFlow() {
        startActivity(new Intent(this, RequestFormActivity.class));
    }

    private void callHotline() {
        Intent dial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Constants.HOTLINE_NUMBER));
        startActivity(dial);
    }

    private void bindBottomNav() {
        // The included bottom_nav_bar / item_nav_tab views are wired up here
        // (icons, labels, click -> Activity navigation) once the other
        // bottom-nav destinations are finalized.
    }
}
