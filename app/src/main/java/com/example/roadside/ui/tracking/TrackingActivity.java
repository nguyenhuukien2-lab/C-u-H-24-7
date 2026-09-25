package com.example.roadside.ui.tracking;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.roadside.R;
import com.example.roadside.data.models.Request;
import com.example.roadside.data.repository.RequestRepository;
import com.example.roadside.ui.payment.PaymentActivity;
import com.example.roadside.utils.Constants;

/** Step 3/3: live map + status stepper while the technician approaches; matches activity_tracking.xml. */
public class TrackingActivity extends AppCompatActivity {

    private TrackingViewModel viewModel;
    private String requestId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);

        requestId = getIntent().getStringExtra(Constants.EXTRA_REQUEST_ID);
        viewModel = new ViewModelProvider(this).get(TrackingViewModel.class);

        findViewById(R.id.btnCallTechnician).setOnClickListener(v -> callTechnician());
        findViewById(R.id.btnCancelRequest).setOnClickListener(v -> cancelRequest());
        findViewById(R.id.btnShareLocation).setOnClickListener(v -> shareLocation());

        if (requestId != null) {
            viewModel.observeRequest(requestId).observe(this, this::renderRequest);
        }
    }

    private void renderRequest(Request request) {
        if (request == null) return;

        TextView tvStatus = findViewById(R.id.tvTrackingStatus);
        if (request.getStatus() == Request.Status.IN_PROGRESS) {
            tvStatus.setText("Kỹ thuật viên đang xử lý sự cố");
        } else if (request.getStatus() == Request.Status.ARRIVED) {
            tvStatus.setText("Đội cứu hộ đã đến nơi");
        }

        if (request.getStatus() == Request.Status.COMPLETED) {
            Intent intent = new Intent(this, PaymentActivity.class);
            intent.putExtra(Constants.EXTRA_REQUEST_ID, request.getId());
            startActivity(intent);
            finish();
        }
    }

    private void callTechnician() {
        // TODO: replace with the assigned technician's real phone number.
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Constants.HOTLINE_NUMBER)));
    }

    private void shareLocation() {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, "Mình đang chờ cứu hộ ResQ247, theo dõi vị trí tại đây: ...");
        startActivity(Intent.createChooser(share, "Chia sẻ vị trí"));
    }

    private void cancelRequest() {
        if (requestId == null) return;
        viewModel.cancelRequest(requestId, new RequestRepository.RequestCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
                Toast.makeText(TrackingActivity.this, "Đã hủy yêu cầu", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onError(String message) {
                Toast.makeText(TrackingActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
