package com.example.mylogi.data.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "truck_info", foreignKeys = {@ForeignKey(entity = TrailerEntity.class,
        parentColumns = {"trailer_id"}, childColumns = {"attached_trailer"})}, indices = {@Index("attached_trailer")})
public class TruckEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "truck_id")
    private Long truckId;

    @ColumnInfo(name = "truck_brand")
    private String truckBrand;

    @ColumnInfo(name = "truck_model")
    private String truckModel;

    @ColumnInfo(name = "truck_mileage")
    private String truckMileage;
    @ColumnInfo(name = "registration_number")
    private String registrationNumber;
    @ColumnInfo(name = "attached_trailer")
    private Long attachedTrailerId;

    public TruckEntity(@NonNull Long truckId, String truckBrand, String registrationNumber, Long attachedTrailerId) {
        this.truckId = truckId;
        this.truckBrand = truckBrand;
        this.registrationNumber = registrationNumber;
        this.attachedTrailerId = attachedTrailerId;
    }

    public TruckEntity() {
    }

    @Ignore
    public TruckEntity(String truckBrand) {
        this.truckBrand = truckBrand;
    }

    public String getTruckMileage() {
        return truckMileage;
    }

    public void setTruckMileage(String truckMileage) {
        this.truckMileage = truckMileage;
    }

    public String getTruckModel() {
        return truckModel;
    }

    public void setTruckModel(String truckModel) {
        this.truckModel = truckModel;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Long getAttachedTrailerId() {
        return attachedTrailerId;
    }

    public void setAttachedTrailerId(Long attachedTrailerId) {
        this.attachedTrailerId = attachedTrailerId;
    }

    @NonNull
    public Long getTruckId() {
        return truckId;
    }

    public void setTruckId(@NonNull Long truckId) {
        this.truckId = truckId;
    }

    public String getTruckBrand() {
        return truckBrand;
    }

    public void setTruckBrand(String truckBrand) {
        this.truckBrand = truckBrand;
    }


}
