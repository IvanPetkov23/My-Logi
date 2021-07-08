package com.example.mylogi.data.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.mylogi.data.entities.DriverEntity;
import com.example.mylogi.data.entities.LocationsEntity;

public class DriverAndLocation {
    @Embedded
    public DriverEntity driverEntity;
    @Relation(
            parentColumn = "id",
            entityColumn = "driver_id"
    )
    public LocationsEntity locationsEntity;

    public DriverEntity getDriverEntity() {
        return driverEntity;
    }

    public void setDriverEntity(DriverEntity driverEntity) {
        this.driverEntity = driverEntity;
    }

    public LocationsEntity getLocationsEntity() {
        return locationsEntity;
    }

    public void setLocationsEntity(LocationsEntity locationsEntity) {
        this.locationsEntity = locationsEntity;
    }
}
