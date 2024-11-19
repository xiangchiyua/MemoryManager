package com.example.memorymanager;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.memorymanager.handle.*;
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
    }

    private void initPage(Event event){
        TextView textView_title=(TextView) findViewById(R.id.textView_eventInfo_title);
        TextView textView_type=(TextView) findViewById(R.id.textView_eventInfo_type);
        TextView textView_info=(TextView) findViewById(R.id.textView_eventInfo_info);
        textView_title.setText(event.getTitle());
        textView_type.setText(event.getItem().getType().name());
        textView_info.setText(event.getDescription());
    }
}