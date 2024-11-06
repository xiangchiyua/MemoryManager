package com.example.memorymanager.model;

import com.example.memorymanager.handle.Event;
import com.example.memorymanager.handle.Item;

import java.util.Date;

public class CommonEvent extends Event {
    private String type;
    private Date time;
    private boolean isFinish;

    public CommonEvent(String title, boolean isRecurring, Date date, String description, Item item, String type, Date time, boolean isFinish) {
        super(title, isRecurring, date, description, item);
        this.type = type;
        this.time = time;
        this.isFinish = isFinish;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }
}
