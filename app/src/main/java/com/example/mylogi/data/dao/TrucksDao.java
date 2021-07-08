package com.example.mylogi.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.mylogi.data.entities.TruckEntity;
import com.example.mylogi.data.relations.TruckAndTrailer;

import java.util.List;

@Dao
public interface TrucksDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertTruck(TruckEntity truckEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllTrucks(List<TruckEntity> allTrucks);

    @Transaction
    @Query("SELECT * FROM trailer_info")
    LiveData<List<TruckAndTrailer>> getAllTrucks();

    @Query("SELECT * FROM truck_info ORDER BY truck_brand ASC")
    LiveData<List<TruckEntity>> getAllSortedTrucks();

    @Query("SELECT COUNT(*) FROM truck_info")
    Integer countOfRows();


}
