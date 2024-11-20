package com.example.memorymanager.controller;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.memorymanager.handle.Event;
import com.example.memorymanager.enums.type;
import com.example.memorymanager.model.AccountEvent;
import com.example.memorymanager.model.AnniversaryEvent;
import com.example.memorymanager.model.CommonEvent;
import com.example.memorymanager.model.TravelRecord;
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
    SQLHelper sqlHelper;
    SQLiteDatabase db;
    public void initialize(Context context) {
        this.context = context.getApplicationContext(); // 避免内存泄漏
        sqlHelper = new SQLHelper(this.context); // 初始化 SQLHelper
    }

    public void checkDatabase() {
        db = sqlHelper.getWritableDatabase();
        if (sqlHelper.isDatabaseExist(context, "memory.db")) {
            Log.e(TAG, "checkDatabase: checked");
        } else {
            sqlHelper.onCreate(db);
            Log.e(TAG, "checkDatabase: can not find database");
        }
    }


    public static EventManager getInstance() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
    }

    public void addEvent(Event event,List<TravelRecord> travelRecordList) {
        type type = event.getItem().getType();
        //int item_id = (int)sqlHelper.insertItem(event.getItem());
        switch (type){
            case AccountEvent : sqlHelper.insertAccountEvent((AccountEvent) event);
            case CommonEvent : sqlHelper.insertCommonEvent((CommonEvent) event,travelRecordList);
            case Anniversary : sqlHelper.insertAnniversary((AnniversaryEvent) event);
        }
    }

    public void removeEvent(Event event) {
        type type = event.getItem().getType();
        sqlHelper.deleteItemById(event.getItem().getId());
        sqlHelper.deleteRecordById(event.getItem().getId());
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
