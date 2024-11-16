package com.example.memorymanager.model;

import com.example.memorymanager.handle.Event;
import com.example.memorymanager.handle.Item;

import java.util.Date;

public class Anniversary extends Event {
    private String location;

    public Anniversary(String title, boolean isRecurring, Date date, String description, Item item, String location) {
        super(title, isRecurring, date, description, item);
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
