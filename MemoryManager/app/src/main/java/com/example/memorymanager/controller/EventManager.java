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
            //sqlHelper.onUpgrade(db,1,2);
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

    public void addEvent(Event event, List<TravelRecord> travelRecordList, type type) {
        //type type = event.getItem().getType();
        //int item_id = (int)sqlHelper.insertItem(event.getItem());
        long id;
        switch (type){
            case AccountEvent : id = sqlHelper.insertAccountEvent((AccountEvent) event);break;
            case CommonEvent : id = sqlHelper.insertCommonEvent((CommonEvent) event,travelRecordList);break;
            default : id = sqlHelper.insertAnniversary((AnniversaryEvent) event);break;
        }
        for (TravelRecord record:travelRecordList) {
            record.setId((int)id);
        }
        sqlHelper.insertRecord(travelRecordList,(int)id);
    }
//    public void addRecord(int id,List<TravelRecord> travelRecordList){
//        sqlHelper.insertRecord()
//    }

    public void removeEvent(Event event) {
        type type = event.getItem().getType();
        sqlHelper.deleteItemById(event.getItem().getId());
        sqlHelper.deleteRecordById(event.getItem().getId());
        switch (type){
            case AccountEvent : sqlHelper.deleteAccountEventById(event.getItem().getId());break;
            case CommonEvent : sqlHelper.deleteCommonEventById(event.getItem().getId());break;
            case Anniversary : sqlHelper.deleteAnniversaryById(event.getItem().getId());break;
        }
    }

    public void updateEvent(Event event) {
        type type = event.getItem().getType();
        switch (type){
            case AccountEvent : sqlHelper.updateAccountEvent((AccountEvent) event);break;
            case CommonEvent : sqlHelper.updateCommonEvent((CommonEvent) event);break;
            case Anniversary : sqlHelper.updateAnniversary((AnniversaryEvent) event);break;
        }
    }


    public List<Event> getEvent() {
        events.clear();
        events.addAll(sqlHelper.queryAccountEvent());
        events.addAll(sqlHelper.queryCommonEvent());
        events.addAll(sqlHelper.queryAnniversary());
        return events;
    }
    public List<Event> getAccountEvent() {
        events.clear();
        events.addAll(sqlHelper.queryAccountEvent());
        return events;
    }
    public List<Event> getCommonEvent() {
        events.clear();
        events.addAll(sqlHelper.queryCommonEvent());
        return events;
    }
    public List<Event> getAnniversaryEvent() {
        events.clear();
        events.addAll(sqlHelper.queryAnniversary());
        return events;
    }
    public List<TravelRecord>getRecord(int id){
        List<TravelRecord> travelRecordList = new ArrayList<>();
        travelRecordList = sqlHelper.queryRecord(id);
        return travelRecordList;
    }
    public List<Event>getTitleEvent(String title){
        events.clear();
        events.addAll(sqlHelper.queryAccountEventByIsRecurring(title));
        events.addAll(sqlHelper.queryCommonEventBy(title));
        events.addAll(sqlHelper.queryAnniversaryBy(title));
        return events;
    }
}
