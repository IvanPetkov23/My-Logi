package com.example.mylogi.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylogi.R;
import com.example.mylogi.data.relations.DriverAndTruck;

import java.util.List;

public class DriverListAdapter extends RecyclerView.Adapter<DriverListAdapter.DriverViewHolder> {

    private final LayoutInflater mInflater;
    private List<DriverAndTruck> mDrivers;
    private OnItemListener mOnItemListener;

    public DriverListAdapter(Context context, OnItemListener onItemListener) {
        mInflater = LayoutInflater.from(context);
        this.mOnItemListener = onItemListener;
    }

    @NonNull
    @Override
    public DriverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new DriverViewHolder(itemView, mOnItemListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DriverViewHolder holder, int position) {
        if (mDrivers != null) {
            DriverAndTruck current = mDrivers.get(position);
            holder.driverItemView.setText(current.driverEntity.getDriverFirstName() + " "
                    + current.driverEntity.getDriverLastName());
            holder.driverItemView1.setText(current.truckEntity.getRegistrationNumber());
            holder.imageView.setImageDrawable(Drawable.createFromPath(current.driverEntity.getImagePath()));


        } else {
            holder.driverItemView.setText("No Word");
        }
    }

    public void setDrivers(List<DriverAndTruck> drivers) {
        this.mDrivers = drivers;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mDrivers != null)
            return mDrivers.size();
        else return 0;
    }

    public interface OnItemListener {
        void onItemClick(int position);
    }

    class DriverViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView driverItemView;
        private final TextView driverItemView1;
        private final ImageView imageView;

        OnItemListener onItemListener;

        private DriverViewHolder(View itemView, OnItemListener onItemListener) {
            super(itemView);
            driverItemView = itemView.findViewById(R.id.textVie9);
            driverItemView1 = itemView.findViewById(R.id.textView10);
            imageView = itemView.findViewById(R.id.imageView);
            this.onItemListener = onItemListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemListener.onItemClick(getAdapterPosition());
        }
    }
}
