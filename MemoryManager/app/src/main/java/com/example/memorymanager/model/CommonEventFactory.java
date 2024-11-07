package com.example.memorymanager.model;

import com.example.memorymanager.handle.Factory;

public class CommonEventFactory implements Factory {
    public CommonEvent createEvent() {
        return new CommonEvent();
    }
}
