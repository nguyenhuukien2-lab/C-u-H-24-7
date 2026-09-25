package com.example.roadside.ui.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roadside.R;
import com.example.roadside.data.models.Request;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/** Binds completed Request rows to item_request.xml in HistoryActivity. */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private final List<Request> requests = new ArrayList<>();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    public void submitList(List<Request> newRequests) {
        requests.clear();
        if (newRequests != null) {
            requests.addAll(newRequests);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_request, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        Request request = requests.get(position);
        holder.tvService.setText(request.getIssueType());
        holder.tvDate.setText(dateFormat.format(new Date(request.getCreatedAt())));
        holder.tvVehicle.setText(request.getVehicleModel() + " • " + request.getPlateNumber());
        holder.tvLocation.setText("📍 " + request.getAddress());
        holder.tvStatus.setText(request.getStatus() == Request.Status.CANCELLED ? "✕ Đã hủy" : "✓ Đã xong");
    }

    @Override
    public int getItemCount() {
        return requests.size();
    }

    static class HistoryViewHolder extends RecyclerView.ViewHolder {
        final TextView tvService;
        final TextView tvDate;
        final TextView tvVehicle;
        final TextView tvLocation;
        final TextView tvStatus;

        HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvService = itemView.findViewById(R.id.tvRequestService);
            tvDate = itemView.findViewById(R.id.tvRequestDate);
            tvVehicle = itemView.findViewById(R.id.tvRequestVehicle);
            tvLocation = itemView.findViewById(R.id.tvRequestLocation);
            tvStatus = itemView.findViewById(R.id.tvRequestStatus);
        }
    }
}
