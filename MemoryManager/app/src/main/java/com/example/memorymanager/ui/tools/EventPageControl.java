package com.example.memorymanager.ui.tools;

import com.example.memorymanager.handle.Event;

//被Event展示界面引用，用于更新前端的相关信息
public interface EventPageControl {
    //连接数据库，获取本页面相关的Event事件列表
    //public void connectDatabase();

    /*更新事件列表容器（全部更新），并将更新结果展示到前端Layout控件；
     （仅修改本类的容器和前端控件，不需要关心数据库操作）*/
    public void updateEventLayout();

    /*更新所传入的事件对象（无该对象则创建对应的Event对象），并将更新结果展示到前端Layout控件
     （仅修改本类的容器和前端控件，不需要关心数据库操作）*/
    public void updateEventLayout(Event event);
}
