package com.example.memorymanager.ui.notifications;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.memorymanager.Activity_EventInfo;
import com.example.memorymanager.Activity_SetEvent;
import com.example.memorymanager.Activity_Type;
import com.example.memorymanager.databinding.FragmentEventNotificationsBinding;
import com.example.memorymanager.enums.type;
import com.example.memorymanager.handle.Event;
import com.example.memorymanager.handle.Item;
import com.example.memorymanager.model.AccountEvent;
import com.example.memorymanager.model.AnniversaryEvent;
import com.example.memorymanager.model.CommonEvent;
import com.example.memorymanager.ui.tools.EventPageControl;
import com.example.memorymanager.ui.tools.PagesName;
import com.example.memorymanager.ui.tools.TemporaryAction;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class NotificationsFragment extends Fragment implements EventPageControl {

    //本页面对应的Event类型的事件的容器
    private List<Event> eventList=new ArrayList<>();
    private LinearLayout layout;
    private HashMap<Event,LinearLayout> itemTable =new HashMap<>();

    private FragmentEventNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentEventNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textEventNotifications;

        //创建页面输出提示信息
        Toast.makeText(super.getContext(),"fragment_event_notifications created",Toast.LENGTH_LONG).show();
        //获取本界面的LinearLayout事件列表控件
        layout=binding.linearLayoutEventNotifications;
        //初始化返回按钮
        initBackButton();
        //调用连接数据库的方法来更新本类的eventList
        connectDatabase();
        //初始化界面
        updateEventLayout();
        Button button=binding.buttonTest3;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventList.get(4).setTitle("changed title");
                updateEventLayout(eventList.get(4));
            }
        });

        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        eventList.clear();
        itemTable.clear();
        super.onDestroyView();
        binding = null;
    }


    //待删
    private void test(){
        Item i=new Item(new Date(),"itemTitle","itemDescription",10, type.AccountEvent);
        eventList.add(new AccountEvent("Summer festival",true, new Date(),"empty",i,1));
        eventList.add(new AccountEvent("Autumn festival",true, new Date(),"empty",i,1));
        eventList.add(new AnniversaryEvent("anniversary 4",true, new Date(),"empty",i,"empty","empty"));
        eventList.add(new CommonEvent("common 3",true, new Date(),"empty",i,"empty",true));
        eventList.add(new CommonEvent("common 4",true, new Date(),"empty",i,"empty",true));
        eventList.add(new CommonEvent("common 5",true, new Date(),"empty",i,"empty",true));
        eventList.add(new CommonEvent("common 6",true, new Date(),"empty",i,"empty",true));
    }


    //unfinished function
    //初始化本界面时将调用此方法调用连接数据库的相关方法来获取事件列表
    private void connectDatabase(){
        //需要修改
        //test();
        int type=TemporaryAction.getChooseFrom_page_type();
        switch (type){
            case 0:
                eventList=TemporaryAction.getEventManager().getAnniversaryEvent();
                break;
            case 1:
                eventList=TemporaryAction.getEventManager().getAccountEvent();
                break;
            case 2:
                eventList=TemporaryAction.getEventManager().getCommonEvent();
                break;
            default: break;
        }
    }

    //初始化返回按钮和事件添加按钮
    private void initBackButton(){
        Button button_back=binding.buttonBackFromPageItemType;
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TemporaryAction.setIfFromPageEventInfo(false);
                TemporaryAction.setPriorPage(PagesName.page_itemType);
                startActivity(new Intent(NotificationsFragment.super.getContext(), Activity_Type.class));
            }
        });
        Button button_add=binding.buttonAddPageItemType;
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TemporaryAction.setIfFromPageEventInfo(false);
                TemporaryAction.setPriorPage(PagesName.page_itemType);
                startActivity(new Intent(NotificationsFragment.super.getContext(), Activity_SetEvent.class));
            }
        });
    }

    //implement interface EventPageControl

    /*更新事件列表容器（全部更新），并将更新结果展示到前端Layout控件；
     （仅修改本类的容器和前端控件，不需要关心数据库操作）*/
    @Override
    public void updateEventLayout(){
        for(int i=0;i<eventList.size();i++){
            addEventView(eventList.get(i));
        }
    }

    /*更新所传入的事件对象（无该对象则创建对应的Event对象），并将更新结果展示到前端Layout控件
     （仅修改本类的容器和前端控件，不需要关心数据库操作）*/
    @Override
    public void updateEventLayout(Event event){
        if(itemTable.containsKey(event)){
            layout.removeView(itemTable.get(event));
        }
        addEventView(event);
    }

    //向Layout控件中添加一个代表Event事件的控件
    @SuppressLint("SetTextI18n")
    private void addEventView(Event event){
        TextView textView_temp_mark=new TextView(super.getContext());
        textView_temp_mark.setText("Event:   ");
        textView_temp_mark.setHeight(160);
        textView_temp_mark.setTextSize(20);

        TextView textView_temp_title=new TextView(super.getContext());
        textView_temp_title.setText("title: "+event.getTitle());
        textView_temp_title.setHeight(128);
        textView_temp_title.setTextSize(28);

        LinearLayout layout_temp=new LinearLayout(super.getContext());
        layout_temp.setOrientation(LinearLayout.HORIZONTAL);
        layout_temp.setBackgroundColor(100);
        layout_temp.addView(textView_temp_mark);
        layout_temp.addView(textView_temp_title);
        itemTable.put(event,layout_temp);

        layout_temp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                TemporaryAction.setEventToShow(event);
                TemporaryAction.setIfFromPageEventInfo(false);
                TemporaryAction.setPriorPage(PagesName.page_itemType);
                startActivity(new Intent(NotificationsFragment.super.getContext(), Activity_EventInfo.class));
            }
        });
        layout.addView(layout_temp);
    }
}