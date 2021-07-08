package com.example.mylogi.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mylogi.data.AppDatabase;
import com.example.mylogi.data.dao.TrailersDao;
import com.example.mylogi.data.dao.TrucksDao;
import com.example.mylogi.data.entities.TrailerEntity;

import java.util.List;

public class TrailerRepository {

    private TrailersDao mTrailersDao;
    private LiveData<List<TrailerEntity>> mAllTrailers;

    public TrailerRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mTrailersDao = db.trailersDao();
        mAllTrailers = mTrailersDao.getAllTrailers();
    }

    public LiveData<List<TrailerEntity>> getAllTrailers() {
        return mAllTrailers;
    }

    public void insert(TrailerEntity trailer) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mTrailersDao.insert(trailer);
        });
    }

    public void insertAllTrailers(List<TrailerEntity> trailers) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            for (TrailerEntity trailer : trailers) {
                mTrailersDao.insert(trailer);
            }
        });
    }
}
