package com.example.mylogi.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylogi.R;
import com.example.mylogi.data.relations.DriverAndTruck;
import com.example.mylogi.data.relations.TruckAndTrailer;

import java.util.List;

public class TruckListAdapter extends RecyclerView.Adapter<TruckListAdapter.TruckViewHolder> {
    private final LayoutInflater mInflater;
    private List<TruckAndTrailer> mTrucks;

    public TruckListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TruckListAdapter.TruckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item_truck, parent, false);
        return new TruckListAdapter.TruckViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TruckListAdapter.TruckViewHolder holder, int position) {
        if (mTrucks != null) {
            TruckAndTrailer current = mTrucks.get(position);
            holder.brandTextView.setText(current.truckEntity.getTruckModel());
            holder.mileageTextView.setText(current.truckEntity.getTruckMileage() + " km.");
            holder.trailerTextView.setText(current.truckEntity.getRegistrationNumber());
            switch (current.truckEntity.getTruckBrand()) {
                case "Mercedes":
                    holder.brandLogoImageView.setImageResource(R.drawable.ic_mercedes);
                    break;
                case "Volvo":
                    holder.brandLogoImageView.setImageResource(R.drawable.ic_volvo);
                    break;
                case "Iveco":
                    holder.brandLogoImageView.setImageResource(R.drawable.ic_iveco);
                    break;
                case "Scania":
                    holder.brandLogoImageView.setImageResource(R.drawable.ic_scania);
                    break;
                case "MAN":
                    holder.brandLogoImageView.setImageResource(R.drawable.ic_man);
                    break;
            }

        }
    }

    public void setTrucks(List<TruckAndTrailer> trucks) {
        mTrucks = trucks;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mTrucks != null)
            return mTrucks.size();
        else return 0;
    }

    static class TruckViewHolder extends RecyclerView.ViewHolder {
        private final TextView brandTextView;
        private final TextView trailerTextView;
        private final TextView mileageTextView;
        private final ImageView brandLogoImageView;

        private TruckViewHolder(View itemView) {
            super(itemView);
            brandTextView = itemView.findViewById(R.id.brandTextView);
            trailerTextView = itemView.findViewById(R.id.trailerTextView);
            mileageTextView = itemView.findViewById(R.id.mileageTextView);
            brandLogoImageView = itemView.findViewById(R.id.brandLogoImageView);

        }
    }
}

