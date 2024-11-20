package com.example.memorymanager.handle;

import java.util.Date;
import com.example.memorymanager.enums.type;

public class Item {
    private Date reminderDate;
    private String title;
    private String description;
    private int id;
    private type type;
    public Item(Date reminderDate, String title, String description, int id) {
        this.reminderDate = reminderDate;
        this.title = title;
        this.description = description;
        this.id = id;
    }
    public Item(Date reminderDate, String title, String description, int id, com.example.memorymanager.enums.type type) {
        this.reminderDate = reminderDate;
        this.title = title;
        this.description = description;
        this.id = id;
        this.type = type;
    }

    public com.example.memorymanager.enums.type getType() {
        return type;
    }

    public void setType(com.example.memorymanager.enums.type type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(Date reminderDate) {
        this.reminderDate = reminderDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void SendNotification(){
        //调用前端的相关内容以实现弹出弹窗的效果
    }
}
