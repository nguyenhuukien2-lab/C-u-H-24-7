package com.example.roadside.ui.providers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roadside.R;
import com.example.roadside.data.models.Provider;
import com.example.roadside.utils.PaymentHelper;

import java.util.ArrayList;
import java.util.List;

/** Binds Provider list to item_provider.xml rows in ProviderListActivity. */
public class ProviderAdapter extends RecyclerView.Adapter<ProviderAdapter.ProviderViewHolder> {

    public interface OnProviderChosenListener {
        void onProviderChosen(Provider provider);
    }

    private final List<Provider> providers = new ArrayList<>();
    private final OnProviderChosenListener listener;

    public ProviderAdapter(OnProviderChosenListener listener) {
        this.listener = listener;
    }

    public void submitList(List<Provider> newProviders) {
        providers.clear();
        if (newProviders != null) {
            providers.addAll(newProviders);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProviderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_provider, parent, false);
        return new ProviderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProviderViewHolder holder, int position) {
        Provider provider = providers.get(position);
        holder.tvName.setText(provider.getName());
        holder.tvRatingMeta.setText(String.format("⭐ %.1f (%d đánh giá)",
                provider.getRating(), provider.getRatingCount()));
        holder.tvDistance.setText(String.format("🚗 %.1f km", provider.getDistanceKm()));
        holder.tvEta.setText(String.format("⏱ %d phút tới", provider.getEtaMinutes()));
        holder.tvPrice.setText(PaymentHelper.formatVnd(provider.getPriceEstimate()));
        holder.btnChoose.setOnClickListener(v -> {
            if (listener != null) listener.onProviderChosen(provider);
        });
    }

    @Override
    public int getItemCount() {
        return providers.size();
    }

    static class ProviderViewHolder extends RecyclerView.ViewHolder {
        final TextView tvName;
        final TextView tvRatingMeta;
        final TextView tvDistance;
        final TextView tvEta;
        final TextView tvPrice;
        final Button btnChoose;

        ProviderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvProviderName);
            tvRatingMeta = itemView.findViewById(R.id.tvProviderRatingMeta);
            tvDistance = itemView.findViewById(R.id.tvProviderDistance);
            tvEta = itemView.findViewById(R.id.tvProviderEta);
            tvPrice = itemView.findViewById(R.id.tvProviderPrice);
            btnChoose = itemView.findViewById(R.id.btnChooseProvider);
        }
    }
}
