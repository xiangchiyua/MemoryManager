package com.example.memorymanager.controller;

import com.example.memorymanager.handle.Event;

import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private static EventManager instance;
    private List<Event> events;

    private EventManager() {
        events = new ArrayList<>();
    }

    public static EventManager getInstance() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
    }

    public void addEvent(Event event) {

    }

    public void removeEvent(Event event) {

    }

    public void updateEvent() {

    }

    public List<Event> getEvent() {

    }
}
