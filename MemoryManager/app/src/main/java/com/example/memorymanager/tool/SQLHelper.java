package com.example.memorymanager.tool;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.memorymanager.enums.type;
import com.example.memorymanager.handle.Event;
import com.example.memorymanager.handle.Item;
import com.example.memorymanager.model.AccountEvent;
import com.example.memorymanager.model.AnniversaryEvent;
import com.example.memorymanager.model.CommonEvent;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SQLHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "memory.db";
    private static final String TABLE_NAME_ACCOUNTEVENT = "account_event";
    private static final String TABLE_NAME_COMMONEVENT = "common_event";
    private static final String TABLE_NAME_ANNIVERSARY = "anniversary";
    private static final String TABLE_NAME_ITEM = "item";

    private static final String CREATE_TABLE_ACCOUNTEVENT = "create table "+TABLE_NAME_ACCOUNTEVENT+"(event_id INT PRIMARY KEY AUTOINCREMENT,\n" +
            "title VARCHAR(255) NOT NULL,\n" +
            "is_recurring BOOLEAN NOT NULL,\n" +
            "date DATE,\n" +
            "description TEXT,\n" +
            "item_id INT,\n "+
            "money INT NOT NULL,\n" +
            "FOREIGN KEY (item_id) REFERENCES Item(item_id) ON DELETE SET NULL)";
    private static final String CREATE_TABLE_COMMONEVENT = "create table "+TABLE_NAME_COMMONEVENT+"(event_id INT PRIMARY KEY AUTOINCREMENT,\n" +
            "title VARCHAR(255) NOT NULL,\n" +
            "is_recurring BOOLEAN NOT NULL,\n" +
            "date DATE,\n" +
            "description TEXT,\n" +
            "item_id INT,\n "+
            "type VARCHAR(50),\n" +
            "is_finish BOOLEAN,\n" +
            "FOREIGN KEY (item_id) REFERENCES Item(item_id) ON DELETE SET NULL)";
    private static final String CREATE_TABLE_ANNIVERSARY = "create table "+TABLE_NAME_ANNIVERSARY+"(event_id INT PRIMARY KEY AUTOINCREMENT,\n" +
            "title VARCHAR(255) NOT NULL,\n" +
            "is_recurring BOOLEAN NOT NULL,\n" +
            "date DATE,\n" +
            "description TEXT,\n" +
            "item_id INT,\n "+
            "FOREIGN KEY (item_id) REFERENCES Item(item_id) ON DELETE SET NULL)";
    private static final String CREATE_TABLE_ITEM = "create table "+TABLE_NAME_ITEM+"(item_id INT PRIMARY KEY AUTOINCREMENT,\n" +
            "reminder_date DATE,\n" +
            "title VARCHAR(255),\n" +
            "description TEXT)";

    public SQLHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(CREATE_TABLE_ACCOUNTEVENT);
            db.execSQL(CREATE_TABLE_COMMONEVENT);
            db.execSQL(CREATE_TABLE_ANNIVERSARY);
            db.execSQL(CREATE_TABLE_ITEM);
        }catch (SQLException e) {
            Log.e("DatabaseError", "Error creating tables: " + e.getMessage());
            e.printStackTrace();
        }

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NAME_ACCOUNTEVENT);
        db.execSQL("drop table if exists "+TABLE_NAME_COMMONEVENT);
        db.execSQL("drop table if exists "+TABLE_NAME_ANNIVERSARY);
        db.execSQL("drop table if exists "+TABLE_NAME_ITEM);
        onCreate(db);
    }
    public static boolean isDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }
    public long insertAccountEvent(AccountEvent accountEvent){
        int item_id = (int)insertItem(accountEvent.getItem());
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title",accountEvent.getTitle());
        values.put("is_recurring",accountEvent.isRecurring());
        values.put("date",accountEvent.getDate().toString());
        values.put("description",accountEvent.getDescription());
        values.put("item_id",item_id);
        values.put("money",accountEvent.getMoney());

        return db.insert(TABLE_NAME_ACCOUNTEVENT,null,values);
    }
    public long insertCommonEvent(CommonEvent commonEvent){
        int item_id = (int)insertItem(commonEvent.getItem());
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title",commonEvent.getTitle());
        values.put("is_recurring",commonEvent.isRecurring());
        values.put("date",commonEvent.getDate().toString());
        values.put("description",commonEvent.getDescription());
        values.put("item_id",item_id);
        values.put("type",commonEvent.getType());
        values.put("is_finish",commonEvent.isFinish());

        return db.insert(TABLE_NAME_COMMONEVENT,null,values);
    }
    public long insertAnniversary(AnniversaryEvent anniversary){
        int item_id = (int)insertItem(anniversary.getItem());
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title",anniversary.getTitle());
        values.put("is_recurring",anniversary.isRecurring());
        values.put("date",anniversary.getDate().toString());
        values.put("description",anniversary.getDescription());
        values.put("item_id",item_id);

        return db.insert(TABLE_NAME_ANNIVERSARY,null,values);
    }
    public long insertItem(Item item){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("reminder_date",item.getReminderDate().toString());
        values.put("title",item.getTitle());
        values.put("description",item.getDescription());

        return db.insert(TABLE_NAME_ITEM,null,values);
    }
    public long deleteItemById(int id){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME_ITEM,"item_id = ?",new String[]{String.valueOf(id)});
    }
    public long deleteAccountEventById(int id){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME_ACCOUNTEVENT,"item_id = ?",new String[]{String.valueOf(id)});
    }
    public long deleteCommonEventById(int id){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME_COMMONEVENT,"item_id = ?",new String[]{String.valueOf(id)});
    }
    public long deleteAnniversaryById(int id){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME_ANNIVERSARY,"item_id = ?",new String[]{String.valueOf(id)});
    }
    public Item queryItem(int id){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME_ITEM,null,"where item_id = ?",new String[]{String.valueOf(id)},
                null,null,null);
        Item item = null;
        if(cursor != null){
            while(cursor.moveToNext()){
                SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date_str = cursor.getString(cursor.getColumnIndexOrThrow("reminder_date"));
                Date date;
                try {
                    date = ft.parse(date_str);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));

                item = new Item(date,title,description,id);
                cursor.close();
                db.close();
                
            }

        }
        return item;
    }
    public List<Event> queryAccountEvent(){
        SQLiteDatabase db = getWritableDatabase();
        List<Event> AccountEventList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME_ACCOUNTEVENT,null,null,null,
                null,null,null);

        if(cursor != null){
            while(cursor.moveToNext()){
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                int isRecurring_int = cursor.getInt(cursor.getColumnIndexOrThrow("is_recurring"));
                boolean isRecurring = false;
                if(isRecurring_int==1) isRecurring = true;
                SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date_str = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                Date date;
                try {
                    date = ft.parse(date_str);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
                int item_id = cursor.getInt(cursor.getColumnIndexOrThrow("item_id"));
                int money = cursor.getInt(cursor.getColumnIndexOrThrow("money"));

                AccountEvent accountEvent = new AccountEvent();
                Item item = queryItem(item_id);
                accountEvent.setItem(item);
                accountEvent.setMoney(money);
                accountEvent.setDescription(description);
                accountEvent.setDate(date);
                accountEvent.setTitle(title);
                accountEvent.setRecurring(isRecurring);
                AccountEventList.add(accountEvent);
            }
            cursor.close();
            db.close();
        }
        return AccountEventList;
    }
    public List<Event>queryCommonEvent(){
        SQLiteDatabase db = getWritableDatabase();
        List<Event> CommonEventList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME_COMMONEVENT,null,null,null,
                null,null,null);
        if(cursor != null){
            while(cursor.moveToNext()){
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                int isRecurring_int = cursor.getInt(cursor.getColumnIndexOrThrow("is_recurring"));
                boolean isRecurring = false;
                if(isRecurring_int==1) isRecurring = true;
                SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date_str = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                Date date;
                try {
                    date = ft.parse(date_str);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
                int item_id = cursor.getInt(cursor.getColumnIndexOrThrow("item_id"));
                int is_finish_int = cursor.getInt(cursor.getColumnIndexOrThrow("is_finish"));
                boolean is_finish = false;
                if(is_finish_int==1) is_finish = true;

                CommonEvent commonEvent = new CommonEvent();
                Item item = queryItem(item_id);
                commonEvent.setItem(item);
                commonEvent.setDescription(description);
                commonEvent.setDate(date);
                commonEvent.setTitle(title);
                commonEvent.setRecurring(isRecurring);
                commonEvent.setFinish(is_finish);
                CommonEventList.add(commonEvent);
            }
            cursor.close();
            db.close();
        }
        return CommonEventList;
    }
    public List<Event>queryAnniversary(){
        SQLiteDatabase db = getWritableDatabase();
        List<Event> AnniversaryList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME_ANNIVERSARY,null,null,null,
                null,null,null);
        if(cursor != null){
            while(cursor.moveToNext()){
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                int isRecurring_int = cursor.getInt(cursor.getColumnIndexOrThrow("is_recurring"));
                boolean isRecurring = false;
                if(isRecurring_int==1) isRecurring = true;
                SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date_str = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                Date date;
                try {
                    date = ft.parse(date_str);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
                int item_id = cursor.getInt(cursor.getColumnIndexOrThrow("item_id"));

                AnniversaryEvent anniversary = new AnniversaryEvent();
                Item item = queryItem(item_id);
                anniversary.setItem(item);
                anniversary.setDescription(description);
                anniversary.setDate(date);
                anniversary.setTitle(title);
                anniversary.setRecurring(isRecurring);
                AnniversaryList.add(anniversary);
            }
            cursor.close();
            db.close();
        }
        return AnniversaryList;
    }



}
