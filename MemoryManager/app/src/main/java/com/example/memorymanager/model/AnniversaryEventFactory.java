package com.example.memorymanager.model;

import com.example.memorymanager.handle.Event;
import com.example.memorymanager.handle.Factory;

public class AnniversaryEventFactory implements Factory {
    @Override
    public Event createEvent() {
        return new AnniversaryEvent();
    }
}
