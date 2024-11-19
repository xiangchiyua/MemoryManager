package com.example.memorymanager.ui;

public enum PagesName {
    //标题界面
    page_title,

    //事件类型选择界面
    // page_type与page_addType为同一个页面，type跳转到page_itemType，addType跳转到page_setEvent
    page_type,

    //输入搜索事件条件
    page_select,

    //同page_type
    page_addType,

    //展示某一个事件的信息
    page_eventInfo,

    //初始化或修改事件信息
    page_setEvent,

    //根据选择的事件类型（from_page_title）跳转到本事件列表界面
    page_itemType,

    //根据搜索条件（from_page_select）跳转到本事件搜索列表界面
    page_itemSelect
}
