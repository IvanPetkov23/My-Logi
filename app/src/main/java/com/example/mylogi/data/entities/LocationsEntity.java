package com.example.mylogi.data.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.Objects;

@Entity(tableName = "locations", foreignKeys = {@ForeignKey(entity = DriverEntity.class
        , parentColumns = {"id"}, childColumns = {"driver_id"})}, indices = {@Index("driver_id")})
public class LocationsEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "location_id")
    private Long locationId;

    @ColumnInfo(name = "location_latitude")
    private String locationLatitude;

    @ColumnInfo(name = "location_longitude")
    private String locationLongitude;
    @ColumnInfo(name = "location_datetime")
    private Date locationDateTime;
    @ColumnInfo(name = "driver_id")
    private Long driverId;

    public LocationsEntity() {
    }

    public LocationsEntity(String locationLatitude, String locationLongitude, Long driverId, Date locationDateTime) {
        this.locationLatitude = locationLatitude;
        this.locationLongitude = locationLongitude;
        this.driverId = driverId;
        this.locationDateTime = locationDateTime;

    }

    public Date getLocationDateTime() {
        return locationDateTime;
    }

    public void setLocationDateTime(Date locationDateTime) {
        this.locationDateTime = locationDateTime;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    @NonNull
    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(@NonNull Long locationId) {
        this.locationId = locationId;
    }


    public String getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(String locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public String getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(String locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationsEntity locations = (LocationsEntity) o;
        return locationId.equals(locations.locationId) &&
                Objects.equals(locationLatitude, locations.locationLatitude) &&
                Objects.equals(locationLongitude, locations.locationLongitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, locationLatitude, locationLongitude);
    }

    @Override
    public String toString() {
        return "Locations{" +
                "locationId=" + locationId +
                ", locationLatitude='" + locationLatitude + '\'' +
                ", locationLongitude='" + locationLongitude + '\'' +
                '}';
    }
}
