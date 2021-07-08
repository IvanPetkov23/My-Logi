package com.example.mylogi.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mylogi.data.AppDatabase;
import com.example.mylogi.data.dao.LocationsDAO;
import com.example.mylogi.data.entities.LocationsEntity;

import java.util.List;

public class LocationsRepository {
    private LocationsDAO mLocationsDao;
    private LiveData<List<LocationsEntity>> mAllLocations;

    public LocationsRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mLocationsDao = db.locationsDAO();
        mAllLocations = mLocationsDao.getAllLocations();
    }

    public LiveData<List<LocationsEntity>> getAllLocations() {
        return mAllLocations;
    }

    public void insert(LocationsEntity location) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mLocationsDao.insertLocation(location);
        });
    }
}
