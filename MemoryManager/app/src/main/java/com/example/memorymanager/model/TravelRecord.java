package com.example.memorymanager.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TravelRecord {
    int id;
    Location location;

    String information;
    String time;

    public TravelRecord() {
    }
    public TravelRecord(double latitude, double longitude, String description, String information, String time, int id) {
        this.location = new Location(longitude,latitude,description);
        this.information = information;
        this.time = time;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocationDescription() {
        return location.getDescription();
    }

    public void setLocationLatitude(double latitude) { this.location.setLatitude(latitude); }

    public double getLocationLongitude() { return location.getLongitude(); }

    public void setLocationLongitude(double longitude) { this.location.setLongitude(longitude); }
    public String getInformation() {
        return information;
    }

    public void setLocationDescription(String description) {
        this.location.setDescription(description);
    }

    public double getLocationLatitude() { return location.getLatitude(); }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
