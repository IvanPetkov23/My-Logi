package com.example.mylogi.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mylogi.data.relations.DriverAndTruck;
import com.example.mylogi.data.entities.DriverEntity;
import com.example.mylogi.data.repositories.DriverRepository;

import java.util.List;

public class DriverViewModel extends AndroidViewModel {

    private final LiveData<List<DriverAndTruck>> mAllDrivers;
    private DriverRepository mRepository;

    public DriverViewModel(Application application) {
        super(application);
        mRepository = new DriverRepository(application);
        mAllDrivers = mRepository.getAllDrivers();
    }

    public LiveData<List<DriverAndTruck>> getAllDrivers() {
        return mAllDrivers;
    }

    public LiveData<DriverEntity> getLoggedByUserAndPass(String username, String password) {
        return mRepository.getLoggedByUserAndPass(username, password);
    }

    public LiveData<DriverEntity> getUserById(Long userId) {
        return mRepository.getUserById(userId);
    }

    public LiveData<List<DriverAndTruck>> getFilteredDriversList(String filter) {
        return mRepository.getFilteredDriversList(filter);
    }


    public void insert(DriverEntity driver) {
        mRepository.insert(driver);
    }

    public void insertAllDrivers(List<DriverEntity> allDrivers) {
        mRepository.insertAllDrivers(allDrivers);

    }
}
