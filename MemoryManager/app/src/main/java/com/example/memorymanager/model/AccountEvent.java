package com.example.memorymanager.model;

import com.example.memorymanager.handle.Event;
import com.example.memorymanager.handle.Item;

import java.util.Date;

public class AccountEvent extends Event {
    private int money;

    public AccountEvent(String title, boolean isRecurring, Date date, String description, Item item, int money) {
        super(title, isRecurring, date, description, item);
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
