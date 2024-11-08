package com.example.memorymanager.handle;


import java.util.Date;

public abstract class Event {
    private String title;
    private boolean isRecurring;
    private Date date;
    private String description;
    private Item item;

    public Event(){}
    public Event(String title, boolean isRecurring, Date date, String description, Item item) {
        this.title = title;
        this.isRecurring = isRecurring;
        this.date = date;
        this.description = description;
        this.item = item;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    public void setRecurring(boolean recurring) {
        isRecurring = recurring;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
