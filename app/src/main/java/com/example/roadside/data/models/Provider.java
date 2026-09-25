package com.example.roadside.data.models;

import java.util.List;

public class Provider {
    private String id;
    private String name;
    private String photoUrl;
    private float rating;
    private int ratingCount;
    private double distanceKm;
    private int etaMinutes;
    private double priceEstimate;
    private boolean verified;
    private List<String> capabilities;
    private String vehiclePlate;
    private String phone = "19006868";

    public Provider() { }

    public Provider(int id, String name, String phone, double rating, double distance) {
        this.id = String.valueOf(id);
        this.name = name;
        this.phone = phone;
        this.rating = (float) rating;
        this.distanceKm = distance;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }

    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }

    public int getRatingCount() { return ratingCount; }
    public void setRatingCount(int ratingCount) { this.ratingCount = ratingCount; }

    public double getDistanceKm() { return distanceKm; }
    public void setDistanceKm(double distanceKm) { this.distanceKm = distanceKm; }
    public double getDistance() { return distanceKm; }

    public int getEtaMinutes() { return etaMinutes; }
    public void setEtaMinutes(int etaMinutes) { this.etaMinutes = etaMinutes; }

    public double getPriceEstimate() { return priceEstimate; }
    public void setPriceEstimate(double priceEstimate) { this.priceEstimate = priceEstimate; }

    public boolean isVerified() { return verified; }
    public void setVerified(boolean verified) { this.verified = verified; }

    public List<String> getCapabilities() { return capabilities; }
    public void setCapabilities(List<String> capabilities) { this.capabilities = capabilities; }

    public String getVehiclePlate() { return vehiclePlate; }
    public void setVehiclePlate(String vehiclePlate) { this.vehiclePlate = vehiclePlate; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
