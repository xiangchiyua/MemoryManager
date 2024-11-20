package com.example.memorymanager.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Location {
    int id;
    String location;

    String information;
    String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getTime() {
        return time;
    }

    public void setTime(Date time_) {
        SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss");
        String time = ft.format(time_);
        this.time = time;
    }
}
