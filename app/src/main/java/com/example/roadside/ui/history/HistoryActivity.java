package com.example.roadside.ui.history;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roadside.R;
import com.example.roadside.data.models.Rating;
import com.example.roadside.utils.SharedPrefsHelper;

import java.util.UUID;

/** Ratings + past-requests log; matches activity_history.xml. */
public class HistoryActivity extends AppCompatActivity {

    private HistoryViewModel viewModel;
    private HistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        viewModel = new ViewModelProvider(this).get(HistoryViewModel.class);

        adapter = new HistoryAdapter();
        RecyclerView rvHistory = findViewById(R.id.rvHistory);
        rvHistory.setLayoutManager(new LinearLayoutManager(this));
        rvHistory.setAdapter(adapter);

        findViewById(R.id.btnSubmitRating).setOnClickListener(v -> submitRating());

        observeViewModel();

        String userId = new SharedPrefsHelper(this).getUserId();
        if (userId != null) {
            viewModel.loadHistory(userId);
        }
    }

    private void submitRating() {
        RatingBar ratingBar = findViewById(R.id.ratingBarSatisfaction);
        android.widget.EditText etComment = findViewById(R.id.etRatingComment);

        Rating rating = new Rating();
        rating.setId(UUID.randomUUID().toString());
        rating.setStars((int) ratingBar.getRating());
        rating.setComment(etComment.getText().toString().trim());
        rating.setSubmittedAt(System.currentTimeMillis());

        viewModel.submitRating(rating);
    }

    private void observeViewModel() {
        viewModel.getHistory().observe(this, adapter::submitList);

        viewModel.getRatingSubmitted().observe(this, submitted -> {
            if (Boolean.TRUE.equals(submitted)) {
                Toast.makeText(this, "Cảm ơn bạn đã đánh giá!", Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.getErrorMessage().observe(this, message -> {
            if (message != null) {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
