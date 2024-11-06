package com.example.memorymanager.handle;

import java.util.Date;

public class Item {
    private Date reminderDate;
    private String title;
    private String description;

    public Item(Date reminderDate, String title, String description) {
        this.reminderDate = reminderDate;
        this.title = title;
        this.description = description;
    }
}
