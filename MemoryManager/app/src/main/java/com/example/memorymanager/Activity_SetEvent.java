package com.example.memorymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.memorymanager.ui.PagesName;
import com.example.memorymanager.ui.TemporaryAction;

public class Activity_SetEvent extends AppCompatActivity {

    RadioButton radioButtonAnniversary;
    RadioButton radioButtonStudy;
    RadioButton radioButtonCommon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_set_event);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //初始化返回按钮
        Button button_back=(Button)findViewById(R.id.button_backFrom_page_setEvent);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TemporaryAction.setPriorPage(PagesName.page_setEvent);
                startActivity(new Intent(Activity_SetEvent.this, Activity_Type.class));
            }
        });
        //初始化确认按钮
        Button button_setEvent_confirm=(Button) findViewById(R.id.button_setEvent_confirm);
        button_setEvent_confirm.setOnClickListener(skipToPageEventInfo());
        //初始化选择框RadioButton
        initRadioButton();
    }

    private void initRadioButton(){
        radioButtonAnniversary=(RadioButton) findViewById(R.id.radioButton_type_anniversary);
        radioButtonStudy=(RadioButton)findViewById(R.id.radioButton_type_study);
        radioButtonCommon=(RadioButton)findViewById(R.id.radioButton_type_common);

        radioButtonAnniversary.setChecked(true);
    }

    private View.OnClickListener skipToPageEventInfo(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TemporaryAction.setPriorPage(PagesName.page_setEvent);
                startActivity(new Intent(Activity_SetEvent.this,Activity_EventInfo.class));
            }
        };
    }
}