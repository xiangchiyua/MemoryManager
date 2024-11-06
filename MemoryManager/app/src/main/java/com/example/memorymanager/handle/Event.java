package com.example.memorymanager.handle;


import java.util.Date;

public class Event {
    private String title;
    private boolean isRecurring;
    private Date date;
    private String description;
    private Item item;

    public Event(String title, boolean isRecurring, Date date, String description, Item item) {
        this.title = title;
        this.isRecurring = isRecurring;
        this.date = date;
        this.description = description;
        this.item = item;
    }
}
