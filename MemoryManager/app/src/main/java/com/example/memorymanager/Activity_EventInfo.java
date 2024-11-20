package com.example.memorymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.memorymanager.handle.*;
import com.example.memorymanager.ui.tools.PagesName;
import com.example.memorymanager.ui.tools.TemporaryAction;

import java.util.ArrayList;
import java.util.List;

public class Activity_EventInfo extends AppCompatActivity {

    TextView textView_title;
    TextView textView_type;
    TextView textView_date;
    LinearLayout layout_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_event_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //根据Temporary中保存的待处理事件，初始化界面
        if(TemporaryAction.getEventToShow()!=null)
            initPage(TemporaryAction.getEventToShow());

        //设置修改事件信息按钮
        Button button_event_reset=(Button) findViewById(R.id.button_event_reset);
        button_event_reset.setOnClickListener(skiptoPage(0));

        //设置返回按钮
        Button button_back=(Button) findViewById(R.id.button_backFrom_page_eventInfo);
        button_back.setOnClickListener(skiptoPage(1));
    }

    //初始化界面
    private void initPage(Event event){
        textView_title=(TextView) findViewById(R.id.textView_eventInfo_title);
        textView_type=(TextView) findViewById(R.id.textView_eventInfo_type);
        textView_date=(TextView)findViewById(R.id.textView_eventInfo_date);
        layout_info=(LinearLayout)findViewById(R.id.linear_layout_eventInfo_page_eventInfo);

        //连接数据库获取相应事件的info列表
        List<String>infoList=getInfoOfEvent(event);

        textView_title.setText(event.getTitle());
        textView_type.setText(event.getItem().getType().name());
        textView_date.setText(event.getDate().toString());

        for (String info: infoList) {
            addEventInfo(info);
        }
    }

    //unfinished
    //连接数据库获取相应事件的info列表
    private List<String> getInfoOfEvent(Event event){
        if(event==null)
            return new ArrayList<String>();
        int eventId=event.getItem().getId();

        //待修改
        List<String>result=test();
        return result;
    }


    //待删
    private List<String> test(){
        List<String>result=new ArrayList<>();
        result.add("information 1");
        result.add("information 2");
        result.add("information 3");
        result.add("information 4");
        result.add("information 5");
        result.add("information 6");
        result.add("information 7");
        result.add("information 8");
        result.add("information 9");
        result.add("information 10");
        result.add("information 11");
        result.add("information 12");
        return result;
    }


    //向事件信息列表容器中添加一条记录
    private void addEventInfo(String info){
        TextView textDate=new TextView(this);
        textDate.setText("Date;");
        textDate.setTextSize(20);
        textDate.setWidth(200);
        textDate.setHeight(200);
        textDate.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);

        TextView textInfo =new TextView(this);
        textInfo.setWidth(690);
        textInfo.setText(info);
        textInfo.setTextSize(20);
        textInfo.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);

        Button button_pos=new Button(this);
        button_pos.setText("pos");
        button_pos.setWidth(100);

        LinearLayout layout=new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.addView(textDate);
        layout.addView(textInfo);
        layout.addView(button_pos);

        layout_info.addView(layout);
    }

    //界面跳转
    private View.OnClickListener skiptoPage(int action){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(action==0){
                    TemporaryAction.setIfFromPageEventInfo(true);
                    startActivity(new Intent(Activity_EventInfo.this,Activity_SetEvent.class));
                }
                else{
                    if(TemporaryAction.getPriorPage().equals(PagesName.page_itemType)
                        || TemporaryAction.getPriorPage().equals(PagesName.page_type)){
//                        TemporaryAction.setPriorPage(PagesName.page_eventInfo);
                        TemporaryAction.setIfFromPageEventInfo(true);
                        startActivity(new Intent(Activity_EventInfo.this, Activity_ItemType.class));
                    }
                    else if(TemporaryAction.getPriorPage().equals(PagesName.page_itemSelect)
                        || TemporaryAction.getPriorPage().equals(PagesName.page_select)){
//                        TemporaryAction.setPriorPage(PagesName.page_eventInfo);
                        TemporaryAction.setIfFromPageEventInfo(true);
                        startActivity(new Intent(Activity_EventInfo.this, Activity_ItemSelect.class));
                    }
                }
            }
        };
    }
}