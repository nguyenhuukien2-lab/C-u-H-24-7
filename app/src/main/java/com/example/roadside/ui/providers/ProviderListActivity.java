package com.example.roadside.ui.providers;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roadside.R;
import com.example.roadside.ui.tracking.TrackingActivity;
import com.example.roadside.utils.Constants;

/** Step 2/3: nearby-provider list + map preview; matches activity_provider_list.xml. */
public class ProviderListActivity extends AppCompatActivity {

    private ProvidersViewModel viewModel;
    private ProviderAdapter adapter;
    private String requestId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_list);

        requestId = getIntent().getStringExtra(Constants.EXTRA_REQUEST_ID);
        viewModel = new ViewModelProvider(this).get(ProvidersViewModel.class);

        adapter = new ProviderAdapter(this::onProviderChosen);
        RecyclerView rvProviders = findViewById(R.id.rvProviders);
        rvProviders.setLayoutManager(new LinearLayoutManager(this));
        rvProviders.setAdapter(adapter);

        bindFilterChips();
        observeViewModel();

        // TODO: replace with the customer's actual GPS coordinates + chosen vehicle type.
        viewModel.loadNearbyProviders(21.0, 105.85, Constants.VEHICLE_CAR);
    }

    private void bindFilterChips() {
        findViewById(R.id.filterNearest).setOnClickListener(v ->
                viewModel.setSortOrder(ProvidersViewModel.SortOrder.NEAREST));
        findViewById(R.id.filterRating).setOnClickListener(v ->
                viewModel.setSortOrder(ProvidersViewModel.SortOrder.RATING));
        findViewById(R.id.filterPrice).setOnClickListener(v ->
                viewModel.setSortOrder(ProvidersViewModel.SortOrder.PRICE));
    }

    private void onProviderChosen(com.example.roadside.data.models.Provider provider) {
        if (requestId == null) return;
        viewModel.chooseProvider(requestId, provider.getId());
    }

    private void observeViewModel() {
        viewModel.getProviders().observe(this, adapter::submitList);

        viewModel.getAssignedProviderId().observe(this, providerId -> {
            if (providerId != null) {
                Intent intent = new Intent(this, TrackingActivity.class);
                intent.putExtra(Constants.EXTRA_REQUEST_ID, requestId);
                intent.putExtra(Constants.EXTRA_PROVIDER_ID, providerId);
                startActivity(intent);
            }
        });

        viewModel.getErrorMessage().observe(this, message -> {
            if (message != null) {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
