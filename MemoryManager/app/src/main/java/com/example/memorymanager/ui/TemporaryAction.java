package com.example.memorymanager.ui;

import android.graphics.Region;

import com.example.memorymanager.controller.EventManager;
import com.example.memorymanager.controller.NotificationService;
import com.example.memorymanager.handle.Event;

//本类起到类似Cache的作用，保存使用软件期间一些比较重要的操作
public class TemporaryAction {

    //if click return from certain page, then jump to the following page:
    private static PagesName priorPage=PagesName.page_title;
    public static void setPriorPage(PagesName page){ priorPage=page; }
    public static PagesName getPriorPage(){ return priorPage; }

    //page_type;
    // click "Anniversary", this num will be set to 0;
    // click "study", set to 1;
    // click "common", set to 2;
    private static int chooseFrom_page_type=0;
    public static void setChooseFrom_page_type(int type){ chooseFrom_page_type=type%3; }
    public static int getChooseFrom_page_type(){ return chooseFrom_page_type; }

    //page_title;
    // click "show", this num will be set to 0;
    // click "select", set to 1;
    // click "add", set to 2;
    private static int chooseFrom_page_title=0;
    public static void setChooseFrom_page_title(int action){
        chooseFrom_page_title=action%3;
    }
    public static int getChooseFrom_page_title(){
        return chooseFrom_page_title;
    }

    //page_itemType or page_setEvent or page_itemSelect
    //this is the Event object that should be shown in page_eventInfo
    private static Event eventToShow=null;
    public static void setEventToShow(Event event){ eventToShow=event; }
    public static Event getEventToShow(){ return eventToShow; }

    public static EventManager getEventManager(){
        return EventManager.getInstance();
    }

    public static NotificationService getNotificationService(){
        return NotificationService.getService();
    }
}
