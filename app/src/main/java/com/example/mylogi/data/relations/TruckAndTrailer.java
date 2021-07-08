package com.example.mylogi.data.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.mylogi.data.entities.TrailerEntity;
import com.example.mylogi.data.entities.TruckEntity;

public class TruckAndTrailer {
    @Embedded
    public TrailerEntity trailerEntity;
    @Relation(
            parentColumn = "trailer_id",
            entityColumn = "attached_trailer"
    )
    public TruckEntity truckEntity;

    public TrailerEntity getTrailerEntity() {
        return trailerEntity;
    }

    public void setTrailerEntity(TrailerEntity trailerEntity) {
        this.trailerEntity = trailerEntity;
    }

    public TruckEntity getTruckEntity() {
        return truckEntity;
    }

    public void setTruckEntity(TruckEntity truckEntity) {
        this.truckEntity = truckEntity;
    }
}
