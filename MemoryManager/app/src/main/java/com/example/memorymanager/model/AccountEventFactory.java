package com.example.memorymanager.model;

import android.text.Spannable;

import com.example.memorymanager.handle.Event;
import com.example.memorymanager.handle.Factory;

public class AccountEventFactory implements Factory {

    @Override
    public Event createEvent() {
        return new AccountEvent();
    }
}
