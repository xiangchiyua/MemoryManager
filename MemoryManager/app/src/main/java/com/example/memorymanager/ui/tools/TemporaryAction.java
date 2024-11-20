package com.example.memorymanager.ui.tools;

import com.example.memorymanager.controller.EventManager;
import com.example.memorymanager.controller.NotificationService;
import com.example.memorymanager.handle.Event;
import com.example.memorymanager.model.Location;

//本类起到类似Cache的作用，保存使用软件期间一些比较重要的操作
public class TemporaryAction {

    //if click return from certain page, then jump to the following page:
    private static PagesName priorPage=PagesName.page_title;
    public static void setPriorPage(PagesName page){ priorPage=page; }
    public static PagesName getPriorPage(){ return priorPage; }

    //page_type;
    // click "Anniversary", this num will be set to 0;
    // click "account", set to 1;
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

    //check if the prior page is page_eventInfo when at page_setEvent
    private static boolean fromPageEventInfo=false;
    public static void setIfFromPageEventInfo(boolean yes){ fromPageEventInfo=yes; }
    public static Boolean getIfFromPageEventInfo(){ return fromPageEventInfo; }

    public static EventManager getEventManager(){
        return EventManager.getInstance();
    }

    public static NotificationService getNotificationService(){
        return NotificationService.getService();
    }

    //check radioButtons in page_select,
    // item[0] -> type of event; 0=Anniversary, 1=account, 2=common;
    // item[1] -> whether finished; 0=finished, 1=undo;
    // item[2] -> whether need notification; 0=need, 1=no_need;
    public static int[] checkedRadioInPageSelect={-1,-1,-1};
    private static String editTextTitleInPageSelect="";
    public static void setEditTextTitleInPageSelect(String title){ editTextTitleInPageSelect=title; }
    public static String getEditTextTitleInPageSelect(){ return editTextTitleInPageSelect; }

    //获取的位置信息（Location）
    private static Location location=new Location();
    public static Location getLocation(){ return location; }
    public static void setLocation(Location l){ location=l; }
}
