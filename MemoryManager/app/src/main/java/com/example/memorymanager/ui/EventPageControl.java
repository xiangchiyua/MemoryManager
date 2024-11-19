package com.example.memorymanager.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.memorymanager.Activity_EventInfo;
import com.example.memorymanager.enums.type;
import com.example.memorymanager.handle.Event;
import com.example.memorymanager.handle.Item;
import com.example.memorymanager.model.AccountEvent;
import com.example.memorymanager.model.AnniversaryEvent;
import com.example.memorymanager.model.CommonEvent;
import com.example.memorymanager.ui.EventAll.EventAllFragment;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;

//被Event展示界面引用，用于更新前端的相关信息
public interface EventPageControl {
    /*更新事件列表容器（全部更新），并将更新结果展示到前端Layout控件；
     （仅修改本类的容器和前端控件，不需要关心数据库操作）*/
    public void updateEventLayout();

    /*更新所传入的事件对象（无该对象则创建对应的Event对象），并将更新结果展示到前端Layout控件
     （仅修改本类的容器和前端控件，不需要关心数据库操作）*/
    public void updateEventLayout(Event event);
}
