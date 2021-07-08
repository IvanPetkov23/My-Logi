package com.example.mylogi.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mylogi.data.entities.LocationsEntity;
import com.example.mylogi.data.repositories.LocationsRepository;

import java.util.List;

public class LocationsViewModel extends AndroidViewModel {
    private final LiveData<List<LocationsEntity>> mAllLocations;
    private LocationsRepository mRepository;


    public LocationsViewModel(@NonNull Application application) {
        super(application);
        mRepository = new LocationsRepository(application);
        mAllLocations = mRepository.getAllLocations();
    }

    public LiveData<List<LocationsEntity>> getAllLocations() {
        return mAllLocations;
    }

    public void insert(LocationsEntity location) {
        mRepository.insert(location);
    }
}
