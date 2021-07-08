package com.example.mylogi.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mylogi.data.entities.TrailerEntity;

import java.util.List;

@Dao
public interface TrailersDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAllTrailers(List<TrailerEntity> allTrailers);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(TrailerEntity trailer);

    @Query("SELECT * FROM trailer_info")
    LiveData<List<TrailerEntity>> getAllTrailers();

    @Query("SELECT COUNT(*) FROM trailer_info")
    Integer countOfRows();
}
