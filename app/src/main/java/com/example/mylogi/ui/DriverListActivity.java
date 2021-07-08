package com.example.mylogi.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SearchView;

import com.example.mylogi.R;
import com.example.mylogi.adapters.DriverListAdapter;
import com.example.mylogi.viewmodel.DriverViewModel;
import com.example.mylogi.viewmodel.LocationsViewModel;
import com.google.android.gms.location.LocationServices;

public class DriverListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_list);
        //Button button = findViewById(R.id.button);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        SearchView searchView = findViewById(R.id.searchView);


        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        //final DriverListAdapter adapter = new DriverListAdapter(this, );
        // recyclerView.setAdapter(adapter);


        DriverViewModel driverViewModel = ViewModelProviders.of(this)
                .get(DriverViewModel.class);

        LocationsViewModel locationsViewModel = ViewModelProviders.of(this)
                .get(LocationsViewModel.class);


        //driverViewModel.getAllDrivers().observe(this, adapter::setDrivers);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //  driverViewModel.getFilteredDriversList(s)
                //     .observe(DriverListActivity.this, adapter::setDrivers);
                return true;
            }
        });
    }
}