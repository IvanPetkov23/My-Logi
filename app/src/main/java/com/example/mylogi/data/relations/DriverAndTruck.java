package com.example.mylogi.data.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.mylogi.data.entities.DriverEntity;
import com.example.mylogi.data.entities.TruckEntity;

public class DriverAndTruck {
    @Embedded
    public TruckEntity truckEntity;
    @Relation(
            parentColumn = "truck_id",
            entityColumn = "driven_truck"
    )
    public DriverEntity driverEntity;

    public TruckEntity getTruckEntity() {
        return truckEntity;
    }

    public void setTruckEntity(TruckEntity truckEntity) {
        this.truckEntity = truckEntity;
    }

    public DriverEntity getDriverEntity() {
        return driverEntity;
    }

    public void setDriverEntity(DriverEntity driverEntity) {
        this.driverEntity = driverEntity;
    }
}
