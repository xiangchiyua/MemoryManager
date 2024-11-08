package com.example.memorymanager.tool;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.memorymanager.handle.Item;
import com.example.memorymanager.model.AccountEvent;
import com.example.memorymanager.model.Anniversary;
import com.example.memorymanager.model.CommonEvent;

public class SQLHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "memory.db";
    private static final String TABLE_NAME_ACCOUNTEVENT = "account_event";
    private static final String TABLE_NAME_COMMONEVENT = "common_event";
    private static final String TABLE_NAME_ANNIVERSARY = "anniversary";
    private static final String TABLE_NAME_ITEM = "item";

    private static final String CREATE_TABLE_ACCOUNTEVENT = "create table "+TABLE_NAME_ACCOUNTEVENT+"(event_id INT PRIMARY KEY AUTO_INCREMENT,\n" +
            "title VARCHAR(255) NOT NULL,\n" +
            "is_recurring BOOLEAN NOT NULL,\n" +
            "date DATE,\n" +
            "description TEXT,\n" +
            "item_id INT,\n "+
            "money INT NOT NULL,\n" +
            "FOREIGN KEY (item_id) REFERENCES Item(item_id) ON DELETE SET NULL)";
    private static final String CREATE_TABLE_COMMONEVENT = "create table "+TABLE_NAME_COMMONEVENT+"(event_id INT PRIMARY KEY AUTO_INCREMENT,\n" +
            "title VARCHAR(255) NOT NULL,\n" +
            "is_recurring BOOLEAN NOT NULL,\n" +
            "date DATE,\n" +
            "description TEXT,\n" +
            "item_id INT,\n "+
            "type VARCHAR(50),\n" +
            "is_finish BOOLEAN,\n" +
            "FOREIGN KEY (item_id) REFERENCES Item(item_id) ON DELETE SET NULL)";
    private static final String CREATE_TABLE_ANNIVERSARY = "create table "+TABLE_NAME_ANNIVERSARY+"(event_id INT PRIMARY KEY AUTO_INCREMENT,\n" +
            "title VARCHAR(255) NOT NULL,\n" +
            "is_recurring BOOLEAN NOT NULL,\n" +
            "date DATE,\n" +
            "description TEXT,\n" +
            "item_id INT,\n "+
            "FOREIGN KEY (item_id) REFERENCES Item(item_id) ON DELETE SET NULL)";
    private static final String CREATE_TABLE_ITEM = "create table "+TABLE_NAME_ITEM+"(item_id INT PRIMARY KEY AUTO_INCREMENT,\n" +
            "reminder_date DATE,\n" +
            "title VARCHAR(255)\n" +
            "date DATETIME,\n" +
            "description TEXT)";

    public SQLHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public long insertAccountEvent(AccountEvent accountEvent){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title",accountEvent.getTitle());
        values.put("is_recurring",accountEvent.isRecurring());
        values.put("date",accountEvent.getDate().toString());
        values.put("description",accountEvent.getDescription());
        values.put("item_id",accountEvent.getItem().getId());
        values.put("money",accountEvent.getMoney());

        return db.insert(TABLE_NAME_ACCOUNTEVENT,null,values);
    }
    public long insertCommonEvent(CommonEvent commonEvent){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();


        return db.insert(TABLE_NAME_COMMONEVENT,null,values);
    }
    public long insertAnniversary(Anniversary anniversary){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();


        return db.insert(TABLE_NAME_ANNIVERSARY,null,values);
    }
    public long insertItem(Item item){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();


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


}
