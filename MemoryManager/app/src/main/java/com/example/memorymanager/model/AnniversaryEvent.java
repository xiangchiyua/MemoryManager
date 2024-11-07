package com.example.memorymanager.model;

import com.example.memorymanager.handle.Event;
import com.example.memorymanager.handle.Item;

import java.util.Date;

public class AnniversaryEvent extends Event {
    private String record;
    private String location;

    public AnniversaryEvent() {}

    public AnniversaryEvent(String title, boolean isRecurring, Date date, String description, Item item, String record, String location) {
        super(title, isRecurring, date, description, item);
        this.record = record;
        this.location = location;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
