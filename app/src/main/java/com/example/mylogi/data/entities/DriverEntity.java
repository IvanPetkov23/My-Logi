package com.example.mylogi.data.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "drivers_info", foreignKeys = {@ForeignKey(entity = TruckEntity.class,
        parentColumns = {"truck_id"}, childColumns = {"driven_truck"})}, indices = {@Index("driven_truck")})
public class DriverEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private Long driverId;

    @ColumnInfo(name = "first_name")
    private String driverFirstName;

    @ColumnInfo(name = "middle_name")
    private String driverMiddleName;

    @ColumnInfo(name = "last_name")
    private String driverLastName;

    @ColumnInfo(name = "pid")
    private String driverPID;

    @ColumnInfo(name = "city")
    private String driverCity;

    @ColumnInfo(name = "address")
    private String driverAddress;

    @ColumnInfo(name = "phone_number")
    private String driverNumber;

    @ColumnInfo(name = "email")
    private String driverEmail;

    @ColumnInfo(name = "username")
    private String driverUsername;

    @ColumnInfo(name = "password")
    private String driverPassword;

    @ColumnInfo(name = "image_path")
    private String imagePath;

    @ColumnInfo(name = "driven_truck")
    private Long drivenTruckId;

    @Ignore
    public DriverEntity() {
    }

    public DriverEntity(Long driverId, String driverFirstName, String driverMiddleName,
                        String driverLastName, String driverPID, String driverCity,
                        String driverAddress, String driverNumber, String driverEmail,
                        String driverUsername, String driverPassword, String imagePath, Long drivenTruckId) {

        this.driverId = driverId;
        this.driverFirstName = driverFirstName;
        this.driverMiddleName = driverMiddleName;
        this.driverLastName = driverLastName;
        this.driverPID = driverPID;
        this.driverCity = driverCity;
        this.driverAddress = driverAddress;
        this.driverNumber = driverNumber;
        this.driverEmail = driverEmail;
        this.driverUsername = driverUsername;
        this.driverPassword = driverPassword;
        this.imagePath = imagePath;
        this.drivenTruckId = drivenTruckId;
    }

    public Long getDrivenTruckId() {
        return drivenTruckId;
    }

    public void setDrivenTruckId(Long drivenTruckId) {
        this.drivenTruckId = drivenTruckId;
    }

    @NonNull
    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(@NonNull Long driverId) {
        this.driverId = driverId;
    }

    public String getDriverFirstName() {
        return driverFirstName;
    }

    public void setDriverFirstName(String driverFirstName) {
        this.driverFirstName = driverFirstName;
    }

    public String getDriverMiddleName() {
        return driverMiddleName;
    }

    public void setDriverMiddleName(String driverMiddleName) {
        this.driverMiddleName = driverMiddleName;
    }

    public String getDriverLastName() {
        return driverLastName;
    }

    public void setDriverLastName(String driverLastName) {
        this.driverLastName = driverLastName;
    }

    public String getDriverPID() {
        return driverPID;
    }

    public void setDriverPID(String driverPID) {
        this.driverPID = driverPID;
    }

    public String getDriverCity() {
        return driverCity;
    }

    public void setDriverCity(String driverCity) {
        this.driverCity = driverCity;
    }

    public String getDriverAddress() {
        return driverAddress;
    }

    public void setDriverAddress(String driverAddress) {
        this.driverAddress = driverAddress;
    }

    public String getDriverNumber() {
        return driverNumber;
    }

    public void setDriverNumber(String driverNumber) {
        this.driverNumber = driverNumber;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public void setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
    }

    public String getDriverUsername() {
        return driverUsername;
    }

    public void setDriverUsername(String driverUsername) {
        this.driverUsername = driverUsername;
    }

    public String getDriverPassword() {
        return driverPassword;
    }

    public void setDriverPassword(String driverPassword) {
        this.driverPassword = driverPassword;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "DriverEntity{" +
                "driverId=" + driverId +
                ", driverFirstName='" + driverFirstName + '\'' +
                ", driverMiddleName='" + driverMiddleName + '\'' +
                ", driverLastName='" + driverLastName + '\'' +
                ", driverPID='" + driverPID + '\'' +
                ", driverCity='" + driverCity + '\'' +
                ", driverAddress='" + driverAddress + '\'' +
                ", driverNumber='" + driverNumber + '\'' +
                ", driverEmail='" + driverEmail + '\'' +
                ", driverUsername='" + driverUsername + '\'' +
                ", driverPassword='" + driverPassword + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DriverEntity that = (DriverEntity) o;
        return driverId.equals(that.driverId) &&
                Objects.equals(driverFirstName, that.driverFirstName) &&
                Objects.equals(driverMiddleName, that.driverMiddleName) &&
                Objects.equals(driverLastName, that.driverLastName) &&
                Objects.equals(driverPID, that.driverPID) &&
                Objects.equals(driverCity, that.driverCity) &&
                Objects.equals(driverAddress, that.driverAddress) &&
                Objects.equals(driverNumber, that.driverNumber) &&
                Objects.equals(driverEmail, that.driverEmail) &&
                Objects.equals(driverUsername, that.driverUsername) &&
                Objects.equals(driverPassword, that.driverPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(driverId, driverFirstName, driverMiddleName, driverLastName, driverPID, driverCity, driverAddress, driverNumber, driverEmail, driverUsername, driverPassword);
    }
}
