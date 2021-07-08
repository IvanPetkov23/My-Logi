package com.example.mylogi.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mylogi.data.AppDatabase;
import com.example.mylogi.data.dao.DriversDao;
import com.example.mylogi.data.relations.DriverAndTruck;
import com.example.mylogi.data.entities.DriverEntity;

import java.util.List;

public class DriverRepository {

    private DriversDao mDriversDao;
    private LiveData<List<DriverAndTruck>> mAllDrivers;


    public DriverRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mDriversDao = db.driversDao();
        mAllDrivers = mDriversDao.getAllDrivers();
    }

    public LiveData<List<DriverAndTruck>> getAllDrivers() {
        return mAllDrivers;
    }

    public LiveData<DriverEntity> getLoggedByUserAndPass(String username, String password) {
        return mDriversDao.getDriverByUserAndPass(username, password);
    }

    public LiveData<DriverEntity> getUserById(Long userId) {
        return mDriversDao.getUserById(userId);
    }

    public LiveData<List<DriverAndTruck>> getFilteredDriversList(String filter) {
        return mDriversDao.getFilteredDriversList(filter);
    }

    public void insert(DriverEntity driver) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mDriversDao.insertDriver(driver);
        });
    }

    public void insertAllDrivers(List<DriverEntity> allDrivers) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mDriversDao.insertAllDrivers(allDrivers);
        });
    }
}

