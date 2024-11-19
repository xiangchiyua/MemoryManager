package com.example.memorymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.memorymanager.handle.*;
import com.example.memorymanager.ui.PagesName;
import com.example.memorymanager.ui.TemporaryAction;

public class Activity_EventInfo extends AppCompatActivity {

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

        if(TemporaryAction.getEventToShow()!=null)
            initPage(TemporaryAction.getEventToShow());

        //设置修改事件信息按钮
        Button button_event_reset=(Button) findViewById(R.id.button_event_reset);
        button_event_reset.setOnClickListener(skiptoPage(0));

        //设置返回按钮
        Button button_back=(Button) findViewById(R.id.button_backFrom_page_eventInfo);
        button_back.setOnClickListener(skiptoPage(1));
    }

    private void initPage(Event event){
        TextView textView_title=(TextView) findViewById(R.id.textView_eventInfo_title);
        TextView textView_type=(TextView) findViewById(R.id.textView_eventInfo_type);
        TextView textView_info=(TextView) findViewById(R.id.textView_eventInfo_info);
        textView_title.setText(event.getTitle());
        textView_type.setText(event.getItem().getType().name());
        textView_info.setText(event.getDescription());
    }

    private View.OnClickListener skiptoPage(int action){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(action==0)
                    startActivity(new Intent(Activity_EventInfo.this,Activity_SetEvent.class));
                else{
                    if(TemporaryAction.getPriorPage().equals(PagesName.page_setEvent)
                        || TemporaryAction.getPriorPage().equals(PagesName.page_itemType)){
                        TemporaryAction.setPriorPage(PagesName.page_eventInfo);
                        startActivity(new Intent(Activity_EventInfo.this, Activity_ItemType.class));
                    }
                    else if(TemporaryAction.getPriorPage().equals(PagesName.page_itemSelect)){
                        TemporaryAction.setPriorPage(PagesName.page_eventInfo);
                        startActivity(new Intent(Activity_EventInfo.this, Activity_ItemSelect.class));
                    }
                }
            }
        };
    }
}