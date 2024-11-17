package com.example.memorymanager.model;

import com.example.memorymanager.handle.Event;
import com.example.memorymanager.handle.Item;

import java.util.Date;

public class AnniversaryEvent extends Event {

    public AnniversaryEvent() {}

    public AnniversaryEvent(String title, boolean isRecurring, Date date, String description, Item item, String record, String location) {
        super(title, isRecurring, date, description, item);
    }

}
