package com.example.roadside.ui.request;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roadside.R;
import com.example.roadside.ui.providers.ProviderListActivity;
import com.example.roadside.utils.Constants;

/** Step 1/3 of the SOS flow: vehicle, issue, photos, location; matches activity_request_form.xml. */
public class RequestFormActivity extends AppCompatActivity {

    private RequestViewModel viewModel;
    private EditText etPlateNumber;
    private EditText etVehicleModel;
    private EditText etLocationNote;
    private String selectedVehicleType = Constants.VEHICLE_CAR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_form);

        viewModel = new ViewModelProvider(this).get(RequestViewModel.class);

        etPlateNumber = findViewById(R.id.etPlateNumber);
        etVehicleModel = findViewById(R.id.etVehicleModel);
        etLocationNote = findViewById(R.id.etLocationNote);

        bindVehicleTypeOptions();

        RecyclerView rvPhotos = findViewById(R.id.rvPhotos);
        rvPhotos.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        // TODO: rvPhotos.setAdapter(new PhotoPickerAdapter(...));

        findViewById(R.id.btnFindTeam).setOnClickListener(v -> onFindTeamClicked());

        observeViewModel();
    }

    private void bindVehicleTypeOptions() {
        findViewById(R.id.optionCar).setOnClickListener(v -> selectVehicleType(Constants.VEHICLE_CAR));
        findViewById(R.id.optionTruck).setOnClickListener(v -> selectVehicleType(Constants.VEHICLE_TRUCK));
        findViewById(R.id.optionMotorbike).setOnClickListener(v -> selectVehicleType(Constants.VEHICLE_MOTORBIKE));
        findViewById(R.id.optionEv).setOnClickListener(v -> selectVehicleType(Constants.VEHICLE_EV));
    }

    private void selectVehicleType(String vehicleType) {
        selectedVehicleType = vehicleType;
        findViewById(R.id.optionCar).setSelected(Constants.VEHICLE_CAR.equals(vehicleType));
        findViewById(R.id.optionTruck).setSelected(Constants.VEHICLE_TRUCK.equals(vehicleType));
        findViewById(R.id.optionMotorbike).setSelected(Constants.VEHICLE_MOTORBIKE.equals(vehicleType));
        findViewById(R.id.optionEv).setSelected(Constants.VEHICLE_EV.equals(vehicleType));
        viewModel.setVehicleType(vehicleType);
    }

    private void onFindTeamClicked() {
        viewModel.setVehicleType(selectedVehicleType);
        viewModel.setVehicleInfo(
                etPlateNumber.getText().toString().trim(),
                etVehicleModel.getText().toString().trim());
        viewModel.setLocation(
                21.0, 105.85, // TODO: replace with LocationHelper result / map pin
                findViewById(R.id.tvAddressLine) instanceof android.widget.TextView
                        ? ((android.widget.TextView) findViewById(R.id.tvAddressLine)).getText().toString()
                        : "",
                etLocationNote.getText().toString().trim());
        viewModel.submitRequest();
    }

    private void observeViewModel() {
        viewModel.getSubmitting().observe(this, submitting ->
                findViewById(R.id.btnFindTeam).setEnabled(!Boolean.TRUE.equals(submitting)));

        viewModel.getCreatedRequest().observe(this, request -> {
            if (request != null) {
                Intent intent = new Intent(this, ProviderListActivity.class);
                intent.putExtra(Constants.EXTRA_REQUEST_ID, request.getId());
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
