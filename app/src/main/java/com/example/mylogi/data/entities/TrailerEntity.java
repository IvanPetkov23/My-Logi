package com.example.mylogi.data.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "trailer_info")
public class TrailerEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "trailer_id")
    private Long trailerId;

    @ColumnInfo(name = "trailer_brand")
    private String truckBrand;

    @Ignore
    public TrailerEntity(String truckBrand) {
        this.truckBrand = truckBrand;
    }

    public TrailerEntity() {
    }

    public TrailerEntity(Long trailerId, String truckBrand) {
        this.trailerId = trailerId;
        this.truckBrand = truckBrand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrailerEntity that = (TrailerEntity) o;
        return trailerId.equals(that.trailerId) &&
                Objects.equals(truckBrand, that.truckBrand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trailerId, truckBrand);
    }

    @Override
    public String toString() {
        return "TrailerEntity{" +
                "trailerId=" + trailerId +
                ", truckBrand='" + truckBrand + '\'' +
                '}';
    }

    @NonNull
    public Long getTrailerId() {
        return trailerId;
    }

    public void setTrailerId(@NonNull Long trailerId) {
        this.trailerId = trailerId;
    }

    public String getTruckBrand() {
        return truckBrand;
    }

    public void setTruckBrand(String truckBrand) {
        this.truckBrand = truckBrand;
    }
}
