package com.example.roadside.data.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.roadside.data.db.Converters;

@Entity(tableName = "requests")
@TypeConverters(Converters.class)
public class Request {

    public enum Status { PENDING, ACCEPTED, EN_ROUTE, ARRIVED, IN_PROGRESS, COMPLETED, CANCELLED }

    @PrimaryKey
    @NonNull
    private String id = "";
    private int userId;
    private String vehicleType;
    private String plateNumber;
    private String vehicleModel;
    private String issueType;
    private String description;
    private String note;
    private double latitude;
    private double longitude;
    private String address;
    private double estimatedCost;
    private Status status = Status.PENDING;
    private String providerId;
    private long createdAt = System.currentTimeMillis();

    public Request() { }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public void setId(int id) { this.id = String.valueOf(id); }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }

    public String getServiceType() { return vehicleType != null ? vehicleType : issueType; }
    public void setServiceType(String serviceType) { this.vehicleType = serviceType; }

    public String getPlateNumber() { return plateNumber; }
    public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }

    public String getVehicleModel() { return vehicleModel; }
    public void setVehicleModel(String vehicleModel) { this.vehicleModel = vehicleModel; }

    public String getIssueType() { return issueType; }
    public void setIssueType(String issueType) { this.issueType = issueType; }

    public String getDescription() { return description != null ? description : note; }
    public void setDescription(String description) { this.description = description; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public double getEstimatedCost() { return estimatedCost; }
    public void setEstimatedCost(double estimatedCost) { this.estimatedCost = estimatedCost; }

    public double getCost() { return estimatedCost; }
    public void setCost(double cost) { this.estimatedCost = cost; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public void setStatus(String statusStr) {
        if (statusStr != null) {
            try {
                this.status = Status.valueOf(statusStr.toUpperCase());
            } catch (Exception e) {
                this.status = Status.PENDING;
            }
        }
    }

    public String getProviderId() { return providerId; }
    public void setProviderId(String providerId) { this.providerId = providerId; }

    public long getCreatedAt() { return createdAt; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }
    public void setCreatedAt(String createdAtStr) {
        try {
            this.createdAt = Long.parseLong(createdAtStr);
        } catch (Exception e) {
            this.createdAt = System.currentTimeMillis();
        }
    }
}
