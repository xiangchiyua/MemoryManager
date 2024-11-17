package com.example.memorymanager.controller;

import android.content.Context;

import com.example.memorymanager.handle.Event;
import com.example.memorymanager.enums.type;
import com.example.memorymanager.model.AccountEvent;
import com.example.memorymanager.model.Anniversary;
import com.example.memorymanager.model.AnniversaryEvent;
import com.example.memorymanager.model.CommonEvent;
import com.example.memorymanager.tool.SQLHelper;

import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private static EventManager instance;
    private List<Event> events;

    private EventManager() {
        events = new ArrayList<>();
    }
    Context context;
    SQLHelper sqlHelper = new SQLHelper(context);

    public static EventManager getInstance() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
    }

    public void addEvent(Event event) {
        type type = event.getItem().getType();
        sqlHelper.insertItem(event.getItem());
        switch (type){
            case AccountEvent : sqlHelper.insertAccountEvent((AccountEvent) event);
            case CommonEvent : sqlHelper.insertCommonEvent((CommonEvent) event);
            case Anniversary : sqlHelper.insertAnniversary((AnniversaryEvent) event);
        }
    }

    public void removeEvent(Event event) {
        type type = event.getItem().getType();
        sqlHelper.deleteItemById(event.getItem().getId());
        switch (type){
            case AccountEvent : sqlHelper.deleteAccountEventById(event.getItem().getId());
            case CommonEvent : sqlHelper.deleteCommonEventById(event.getItem().getId());
            case Anniversary : sqlHelper.deleteAnniversaryById(event.getItem().getId());
        }
    }

    public void updateEvent() {

    }

    public List<Event> getEvent() {
        events.addAll(sqlHelper.queryAccountEvent());
        events.addAll(sqlHelper.queryCommonEvent());
        events.addAll(sqlHelper.queryAnniversary());
        return events;
    }
}
