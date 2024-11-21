package com.example.memorymanager.controller;

import com.example.memorymanager.handle.Event;
import com.example.memorymanager.handle.Item;

import java.util.ArrayList;
import java.util.List;

public class NotificationService implements Runnable {
    private NotificationService(){}
    private boolean enabled=true;

    private static NotificationService service=new NotificationService();

    private List<Item>notifiedEventItemList=new ArrayList<>();

    static {
        service=new NotificationService();
    }

    public static NotificationService getService(){
        return service;
    }

    public void updateNotifiedEventList(Event event){
        if(event==null)
            return; //此处可以throw一个Exception
        Item item;
        boolean find=false;
        for (Item i:this.notifiedEventItemList) {
            if(i!=null && i.getId()==event.getItem().getId()){
                find=true;
                item=event.getItem();
                break;
            }
        }
        if(!find && event.getItem()!=null)
            notifiedEventItemList.add(event.getItem());
    }

    public void removeFromNotificationEventList(Event event){
        if(event==null)
            return;
        for(Item i:this.notifiedEventItemList){
            if(i.getId()==event.getItem().getId()){
                notifiedEventItemList.remove(i);
            }
        }
    }

    @Override
    public void run() {
        while(enabled){
            try {
                Thread.sleep(5000);
                for (Item item:notifiedEventItemList) {
                    item.SendNotification();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
