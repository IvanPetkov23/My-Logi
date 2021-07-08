package com.example.mylogi.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mylogi.data.entities.LocationsEntity;

import java.util.List;

@Dao
public interface LocationsDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertLocation(LocationsEntity locationsEntity);

    @Query("SELECT * FROM locations")
    LiveData<List<LocationsEntity>> getAllLocations();

    @Query("SELECT COUNT(*) FROM locations")
    Integer countOfRows();
}
