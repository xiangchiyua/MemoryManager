package com.example.memorymanager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

public class Activity_ItemSelect extends AppCompatActivity implements EventPageControl {

    private LinearLayout layout;
    private List<Event>eventList=new ArrayList<>();
    private HashMap<Event,LinearLayout>itemMap=new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TemporaryAction.setEventToShow(null);

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_item_select);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //创建页面输出提示信息
        Toast.makeText(Activity_ItemSelect.this,"page_select created",Toast.LENGTH_LONG).show();

        //初始化返回按钮
        Button button_back=(Button)findViewById(R.id.button_backFrom_page_itemSelect);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TemporaryAction.setPriorPage(PagesName.page_itemSelect);
                startActivity(new Intent(Activity_ItemSelect.this,Activity_Select.class));
            }
        });

        //初始化事件列表容器layout
        layout=(LinearLayout) findViewById(R.id.linear_layout_event_select);
        connectDatabase();
        updateEventLayout();
    }


    //待删
    private void test(){
        Item i=new Item(new Date(),"itemTitle","itemDescription",10, type.AccountEvent);
        Item j=new Item(new Date(),"itemTitle","itemDescription",10, type.CommonEvent);
        Item k=new Item(new Date(),"itemTitle","itemDescription",10, type.Anniversary);
        eventList.add(new AccountEvent("Summer festival",true, new Date(),"empty",k,1));
        eventList.add(new AccountEvent("Autumn festival",true, new Date(),"empty",k,1));
        eventList.add(new AnniversaryEvent("anniversary 4",true, new Date(),"empty",k,"empty","empty"));
        eventList.add(new AnniversaryEvent("anniversary 5",true, new Date(),"empty",k,"empty","empty"));
        eventList.add(new CommonEvent("account 2",true, new Date(),"empty",i,"empty",true));
        eventList.add(new AccountEvent("Spring festival",true, new Date(),"empty",k,1));
        eventList.add(new AccountEvent("Winter festival",true, new Date(),"empty",k,1));
        eventList.add(new AnniversaryEvent("anniversary 1",true, new Date(),"empty",k,"empty","empty"));
        eventList.add(new CommonEvent("common 3",true, new Date(),"empty",j,"empty",true));
        eventList.add(new CommonEvent("common 4",true, new Date(),"empty",j,"empty",true));
        eventList.add(new CommonEvent("common 5",true, new Date(),"empty",j,"empty",true));
        eventList.add(new CommonEvent("common 6",true, new Date(),"empty",j,"empty",true));
        eventList.add(new CommonEvent("account 1",true, new Date(),"empty",i,"empty",true));
        eventList.add(new CommonEvent("account 2",true, new Date(),"empty",i,"empty",true));
    }


    private void connectDatabase(){
        //test();
        //需要修改

        String title=TemporaryAction.getEditTextTitleInPageSelect();
        int type=TemporaryAction.checkedRadioInPageSelect[0]; // 0=anniversary, 1=account, 2=common, -1=disable
        int finished=TemporaryAction.checkedRadioInPageSelect[1]; // 0=finished, 1=undo, -1=disable
        int needNotification=TemporaryAction.checkedRadioInPageSelect[2]; // 0=need, 1=no_need, -1=disable

        eventList=TemporaryAction.getEventManager().getTitleEvent(title);
    }

    /*更新事件列表容器（全部更新），并将更新结果展示到前端Layout控件；
     （仅修改本类的容器和前端控件，不需要关心数据库操作）*/
    @Override
    public void updateEventLayout() {
        for(int i=0;i<eventList.size();i++){
            addEventView(eventList.get(i));
        }
    }

    /*更新所传入的事件对象（无该对象则创建对应的Event对象），并将更新结果展示到前端Layout控件
     （仅修改本类的容器和前端控件，不需要关心数据库操作）*/
    @Override
    public void updateEventLayout(Event event) {
        if(eventList.contains(event)){
            layout.removeView(itemMap.get(event));
        }
        addEventView(event);
    }

    //向Layout控件中添加一个代表Event事件的控件
    @SuppressLint("SetTextI18n")
    private void addEventView(Event event){
        TextView textView_temp_mark=new TextView(this);
        textView_temp_mark.setText("Event:   ");
        textView_temp_mark.setHeight(160);
        textView_temp_mark.setTextSize(20);

        TextView textView_temp_title=new TextView(this);
        textView_temp_title.setText("title: "+event.getTitle());
        textView_temp_title.setHeight(128);
        textView_temp_title.setTextSize(28);

        LinearLayout layout_temp=new LinearLayout(this);
        layout_temp.setOrientation(LinearLayout.HORIZONTAL);
        layout_temp.addView(textView_temp_mark);
        layout_temp.addView(textView_temp_title);
        itemMap.put(event,layout_temp);

        layout_temp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                TemporaryAction.setEventToShow(event);
                TemporaryAction.setPriorPage(PagesName.page_itemSelect);
                startActivity(new Intent(Activity_ItemSelect.this, Activity_EventInfo.class));
            }
        });
        layout.addView(layout_temp);
    }
}