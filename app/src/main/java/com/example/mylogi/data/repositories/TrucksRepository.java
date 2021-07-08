package com.example.mylogi.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mylogi.data.AppDatabase;
import com.example.mylogi.data.dao.TrucksDao;
import com.example.mylogi.data.entities.TruckEntity;
import com.example.mylogi.data.relations.TruckAndTrailer;

import java.util.List;

public class TrucksRepository {

    private TrucksDao mTrucksDao;
    private LiveData<List<TruckAndTrailer>> mAllTrucks;

    public TrucksRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mTrucksDao = db.trucksDao();
        mAllTrucks = mTrucksDao.getAllTrucks();
    }

    public LiveData<List<TruckAndTrailer>> getAllTrucks() {
        return mAllTrucks;
    }

    public void insert(TruckEntity truck) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mTrucksDao.insertTruck(truck);
        });
    }

    public void insertAllTrucks(List<TruckEntity> trucks) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mTrucksDao.insertAllTrucks(trucks);
        });
    }

}
