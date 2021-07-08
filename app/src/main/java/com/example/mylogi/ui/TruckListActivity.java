package com.example.mylogi.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mylogi.R;
import com.example.mylogi.adapters.DriverListAdapter;
import com.example.mylogi.adapters.TruckListAdapter;
import com.example.mylogi.viewmodel.TruckViewModel;

public class TruckListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truck_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final TruckListAdapter adapter = new TruckListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        TruckViewModel truckViewModel = ViewModelProviders.of(this).get(TruckViewModel.class);
        truckViewModel.getAllTrucks().observe(this, adapter::setTrucks);
    }
}