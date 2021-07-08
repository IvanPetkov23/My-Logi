package com.example.mylogi.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mylogi.data.entities.TruckEntity;
import com.example.mylogi.data.relations.TruckAndTrailer;
import com.example.mylogi.data.repositories.TrucksRepository;

import java.util.List;

public class TruckViewModel extends AndroidViewModel {

    private final LiveData<List<TruckAndTrailer>> mAllTrucks;
    private TrucksRepository mTruckRepository;

    public TruckViewModel(Application application) {
        super(application);
        mTruckRepository = new TrucksRepository(application);
        mAllTrucks = mTruckRepository.getAllTrucks();
    }

    public LiveData<List<TruckAndTrailer>> getAllTrucks() {
        return mAllTrucks;
    }

    public void insert(TruckEntity truckEntity) {
        mTruckRepository.insert(truckEntity);
    }

    public void insertAllTrucks(List<TruckEntity> trucks) {
        mTruckRepository.insertAllTrucks(trucks);
    }

}
