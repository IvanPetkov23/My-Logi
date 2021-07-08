package com.example.mylogi.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mylogi.data.entities.TrailerEntity;
import com.example.mylogi.data.repositories.TrailerRepository;

import java.util.List;

public class TrailerViewModel extends AndroidViewModel {
    private final LiveData<List<TrailerEntity>> mAllTrailers;
    private TrailerRepository mTrailerRepository;

    public TrailerViewModel(@NonNull Application application) {
        super(application);
        mTrailerRepository = new TrailerRepository(application);
        mAllTrailers = mTrailerRepository.getAllTrailers();
    }

    public LiveData<List<TrailerEntity>> getmAllTrailers() {
        return mAllTrailers;
    }

    public void insertAllTrailers(List<TrailerEntity> trailers) {
        mTrailerRepository.insertAllTrailers(trailers);
    }
}
