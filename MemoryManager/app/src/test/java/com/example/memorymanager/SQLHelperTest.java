package com.example.memorymanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.test.core.app.ApplicationProvider;

import com.example.memorymanager.handle.Item;
import com.example.memorymanager.model.AccountEvent;
import com.example.memorymanager.tool.SQLHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class SQLHelperTest {

    private SQLHelper sqlHelper;
    private Context context;

    @Before
    public void setUp() {
        // 初始化测试上下文和 SQLHelper
        context = ApplicationProvider.getApplicationContext();
        sqlHelper = new SQLHelper(context);
        SQLiteDatabase db = sqlHelper.getWritableDatabase();
        sqlHelper.onUpgrade(db, 0, 0); // 清空数据库
    }

    @After
    public void tearDown() {
        // 关闭数据库
        sqlHelper.close();
    }

    @Test
    public void testInsertAndQueryItem() {
        // 创建测试数据
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date reminderDate = new Date();
        Item testItem = new Item(reminderDate, "Test Item", "This is a test item.",1);

        // 插入数据
        long itemId = sqlHelper.insertItem(testItem);
        assertNotEquals(-1, itemId);

        // 查询数据
        Item queriedItem = sqlHelper.queryItem((int) itemId);
        assertNotNull(queriedItem);
        assertEquals(testItem.getTitle(), queriedItem.getTitle());
        assertEquals(testItem.getDescription(), queriedItem.getDescription());
    }

    @Test
    public void testInsertAndQueryAccountEvent() throws ParseException {
        // 创建测试 Item 和 AccountEvent
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date reminderDate = dateFormat.parse("2024-01-01 10:00:00");
        Item testItem = new Item(reminderDate, "Test Item", "This is a test item.",1);
        long itemId = sqlHelper.insertItem(testItem);

        AccountEvent testEvent = new AccountEvent();
        testEvent.setTitle("Test Account Event");
        testEvent.setRecurring(false);
        testEvent.setDate(dateFormat.parse("2024-02-01 10:00:00"));
        testEvent.setDescription("This is a test account event.");
        testEvent.setMoney(100);
        testItem.setId((int) itemId);
        testEvent.setItem(testItem);

        // 插入数据
        long eventId = sqlHelper.insertAccountEvent(testEvent);
        assertNotEquals(-1, eventId);

        // 查询数据
        Item queriedItem = sqlHelper.queryItem((int) itemId);
        assertNotNull(queriedItem);
        assertEquals(testItem.getTitle(), queriedItem.getTitle());

        AccountEvent queriedEvent = (AccountEvent) sqlHelper.queryAccountEvent().get(0);
        assertNotNull(queriedEvent);
        assertEquals(testEvent.getTitle(), queriedEvent.getTitle());
        assertEquals(testEvent.getDescription(), queriedEvent.getDescription());
        assertEquals(testEvent.getMoney(), queriedEvent.getMoney());
    }
}
