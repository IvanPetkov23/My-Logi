package com.example.mylogi.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.mylogi.data.relations.DriverAndTruck;
import com.example.mylogi.data.entities.DriverEntity;

import java.util.List;

@Dao
public interface DriversDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDriver(DriverEntity driver);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllDrivers(List<DriverEntity> allDrivers);

    @Query("SELECT * FROM drivers_info ORDER BY first_name and middle_name and last_name ASC")
    LiveData<List<DriverEntity>> getAllSortedDrivers();

    @Query("SELECT * FROM drivers_info WHERE username = :username AND password = :password")
    LiveData<DriverEntity> getDriverByUserAndPass(String username, String password);

    @Transaction
    @Query("SELECT * FROM truck_info")
    LiveData<List<DriverAndTruck>> getAllDrivers();

    @Query("SELECT * FROM drivers_info WHERE id = :userId")
    LiveData<DriverEntity> getUserById(Long userId);

    @Query("SELECT COUNT(*) FROM drivers_info")
    Integer countOfRows();

    @Transaction
    @Query("SELECT * FROM truck_info t INNER JOIN drivers_info d ON d.driven_truck = truck_id" +
            " WHERE d.first_name LIKE '%' || :filter || '%'")
    LiveData<List<DriverAndTruck>> getFilteredDriversList(String filter);


}
