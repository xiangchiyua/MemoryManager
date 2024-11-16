package com.example.memorymanager.model;

import com.example.memorymanager.handle.Event;
import com.example.memorymanager.handle.Item;

import java.util.Date;

public class CommonEvent extends Event {
    private String type;
    private boolean isFinish;

    public CommonEvent(){}
    public CommonEvent(String title, boolean isRecurring, Date date, String description, Item item, String type, boolean isFinish) {
        super(title, isRecurring, date, description, item);
        this.type = type;
        this.isFinish = isFinish;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }
}
