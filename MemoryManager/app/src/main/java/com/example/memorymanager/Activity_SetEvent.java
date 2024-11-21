package com.example.memorymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.memorymanager.enums.type;
import com.example.memorymanager.handle.Event;
import com.example.memorymanager.location.LocationService;
import com.example.memorymanager.model.AccountEvent;
import com.example.memorymanager.model.CommonEvent;
import com.example.memorymanager.model.Location;
import com.example.memorymanager.model.TravelRecord;
import com.example.memorymanager.ui.Dialog.Dialog_SetNotification;
import com.example.memorymanager.ui.tools.PagesName;
import com.example.memorymanager.ui.tools.TemporaryAction;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Activity_SetEvent extends AppCompatActivity {

    private LocationService locationService;

    EditText editText_title;

    RadioButton radioButtonAnniversary;
    RadioButton radioButtonaccount;
    RadioButton radioButtonCommon;

    RadioButton radioButton_Need_Notification;
    RadioButton radioButton_No_Notification;

    RadioButton radioButtonUndo;
    RadioButton radioButtonFinished;

    EditText editText_extraInfo;

    //本界面处理的事件
    Event event=null;
    //装载所有消息添加控件的容器
    LinearLayout layoutAllInfo;
    //装载所有添加消息（EditText控件）
    List<EditText> addedInfoList=new ArrayList<>();
    //按钮“添加消息”
    Button button_addInfo;
    //一条备注可能会对应一条位置消息
    EditText editText_NeedLocation;
    HashMap<EditText, Location>locationMap=new HashMap<>();

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

        //初始化位置服务对象
        locationService=new LocationService(Activity_SetEvent.this,location -> {
            TemporaryAction.setLocation(location);
            locationMap.put(editText_NeedLocation,location);
            Toast.makeText(Activity_SetEvent.this,TemporaryAction.getLocation().getDescription(),Toast.LENGTH_LONG).show();
        });


        //初始化选择框RadioButton
        initRadioButtonAndLayout();
        //初始化消息容器ScrollView.LinearLayout
        initInfoLayout();

        event=TemporaryAction.getEventToShow();
        //初始化标题输入框
        editText_title=(EditText)findViewById(R.id.editText_set_title);
        editText_extraInfo=(EditText) findViewById(R.id.editText_extraInfo_page_setEvent);
        if(event!=null){
            editText_title.setText(event.getTitle());
            if(event.getClass()== AccountEvent.class)
                editText_extraInfo.setText(String.valueOf(((AccountEvent)event).getMoney()));
            else if(event.getClass()== CommonEvent.class)
                editText_extraInfo.setText(((CommonEvent)event).getType());
        }
        //初始化返回按钮
        Button button_back=(Button)findViewById(R.id.button_backFrom_page_setEvent);
        button_back.setOnClickListener(skipToPage(TemporaryAction.getPriorPage()));
        //初始化确认按钮
        Button button_setEvent_confirm=(Button) findViewById(R.id.button_setEvent_confirm);
        button_setEvent_confirm.setOnClickListener(skipToPage(PagesName.page_eventInfo));

    }

    //初始化类型选择框控件
    private void initRadioButtonAndLayout(){
        radioButtonAnniversary=(RadioButton) findViewById(R.id.radioButton_set_type_anniversary);
        radioButtonaccount=(RadioButton)findViewById(R.id.radioButton_set_type_account);
        radioButtonCommon=(RadioButton)findViewById(R.id.radioButton_set_type_common);
        if(event!=null && event.getItem().getType().equals(type.Anniversary)) radioButtonAnniversary.setChecked(true);
        else if(event!=null && event.getItem().getType().equals(type.AccountEvent)) radioButtonaccount.setChecked(true);
        else radioButtonCommon.setChecked(true);

        radioButton_Need_Notification=(RadioButton) findViewById(R.id.radioButton_set_need_notification);
        radioButton_No_Notification=(RadioButton) findViewById(R.id.radioButton_set_no_notification);
        if(event!=null && event.isRecurring()) radioButton_Need_Notification.setChecked(true);
        else radioButton_No_Notification.setChecked(true);

        radioButtonUndo=(RadioButton) findViewById(R.id.radioButton_set_undo);
        radioButtonFinished=(RadioButton) findViewById(R.id.radioButton_set_finished);
        if(event!=null && event.getClass()== CommonEvent.class && ((CommonEvent)event).isFinish()) radioButtonFinished.setChecked(true);
        radioButtonUndo.setChecked(true);

        radioButton_Need_Notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"dialog set notification",Toast.LENGTH_LONG).show();
                showDialogSetNotification();
            }
        });
    }

    //初始化消息添加容器ScrollView.LinearLayout
    private void initInfoLayout(){
        layoutAllInfo =(LinearLayout)findViewById(R.id.linear_layout_addInfo_page_setEvent);

        //连接数据库
        if(event!=null){
            List<TravelRecord>alreadyRecordList=TemporaryAction.getEventManager().getRecord(event.getItem().getId());
            for (TravelRecord record: alreadyRecordList) {
                addEditText(record);
            }
        }
        addEditText();

        button_addInfo=(Button) findViewById(R.id.button_add_info_page_setEvent);
        button_addInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEditText();
            }
        });
    }

    //向layout信息容器中添加一条info信息输入框
    private void addEditText(){
        TextView textView=new TextView(this);
        textView.setText("input info");
        textView.setTextSize(20);
        textView.setWidth(200);
        textView.setHeight(200);
        textView.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);

        EditText editText=new EditText(this);
        editText.setWidth(680);
        addedInfoList.add(editText);

        Button buttonPos=new Button(this);
        buttonPos.setText("pos");
        buttonPos.setWidth(120);
        buttonPos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText_NeedLocation=editText;
                locationService.start();
            }
        });

        LinearLayout layout=new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.addView(textView);
        layout.addView(editText);
        layout.addView(buttonPos);

        layoutAllInfo.addView(layout);
    }

    //向layout信息容器中添加一条info信息输入框
    private void addEditText(TravelRecord record){
        TextView textView=new TextView(this);
        textView.setText("input info");
        textView.setTextSize(20);
        textView.setWidth(200);
        textView.setHeight(200);
        textView.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);

        EditText editText=new EditText(this);
        editText.setWidth(680);
        editText.setText(record.getInformation());
        //addedInfoList.add(editText);

        Button buttonPos=new Button(this);
        buttonPos.setText("pos");
        buttonPos.setWidth(120);
        buttonPos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationService.start();
            }
        });

        LinearLayout layout=new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.addView(textView);
        layout.addView(editText);
        layout.addView(buttonPos);

        layoutAllInfo.addView(layout);
    }

    //unfinished function
    //在跳转页面前将本界面的信息打包保存在TemporaryAction中
    private void informationPackage(){
        String title= editText_title.getText().toString();
        Date date=new Date();
        boolean needNotification;
        boolean finished;
        if(event==null){
            if(radioButtonAnniversary.isChecked()) event=TemporaryAction.factoryAnniversary.createEvent();
            else if(radioButtonaccount.isChecked()) event=TemporaryAction.factoryAccount.createEvent();
            else event=TemporaryAction.factoryCommon.createEvent();
            event.setTitle(title);
            event.setDate(date);
        }
        needNotification=radioButton_Need_Notification.isChecked();
        event.setRecurring(needNotification);
        finished=radioButtonFinished.isChecked();
        if(event.getClass()==CommonEvent.class){
            ((CommonEvent)event).setType(editText_extraInfo.getText().toString());
            ((CommonEvent)event).setFinish(finished);
        }
        else if(event.getClass()== AccountEvent.class){
            try{
                ((AccountEvent)event).setMoney(Integer.parseInt(editText_extraInfo.getText().toString()));
            }catch (Exception e){}
        }

        event.getItem().setTitle(event.getTitle());
        if(radioButton_Need_Notification.isChecked()){
            event.getItem().setReminderDate(TemporaryAction.dateOfDialogSetNotification);
            event.getItem().setDescription(TemporaryAction.descriptionOfDialogSetNotification);
        }
        if(radioButtonUndo.isChecked()){
            TemporaryAction.getNotificationService().updateNotifiedEventList(event);
        } else if (radioButtonFinished.isChecked()||radioButton_No_Notification.isChecked()) {
            TemporaryAction.getNotificationService().removeFromNotificationEventList(event);
        }

        List<TravelRecord> recordList=new ArrayList<>();
        for (EditText editText:addedInfoList) {
            TravelRecord record=new TravelRecord();
            record.setId(event.getItem().getId());
            record.setTime(new Date().toString());
            record.setInformation(editText.getText().toString());
            if(locationMap.containsKey(editText)){
                record.setLocationDescription(locationMap.get(editText).getDescription());
                record.setLocationLatitude(locationMap.get(editText).getLatitude());
                record.setLocationLongitude(locationMap.get(editText).getLongitude());
            }
            recordList.add(record);
        }

        int getType=TemporaryAction.getChooseFrom_page_type();
        type _type;
        switch (getType){
            case 0: _type=type.Anniversary; break;
            case 1: _type=type.AccountEvent; break;
            default: _type=type.CommonEvent; break;
        }
        event.getItem().setType(_type);
        TemporaryAction.getEventManager().addEvent(event,recordList,_type);
        TemporaryAction.setEventToShow(event);
        //将Event保存到TemporaryAction中，供page_eventInfo使用
    }

    //弹出修改提醒事件的对话框
    private void showDialogSetNotification(){
        Dialog_SetNotification dialog = new Dialog_SetNotification(Activity_SetEvent.this);
        dialog.setTitle("提示");
        dialog.setMessage("确认取消？");
        dialog.setCancel("取消", new Dialog_SetNotification.OnCancelListener() {
            @Override
            public void onCancel(Dialog_SetNotification dialog) {}
        });
        dialog.setConfirm("确认", new Dialog_SetNotification.OnConfirmListener() {
            @Override
            public void onConfirm(Dialog_SetNotification dialog) {}
        });
        dialog.show();
    }

    //控制页面跳转
    private View.OnClickListener skipToPage(PagesName pagesName){
        if(TemporaryAction.getIfFromPageEventInfo()){
            return new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    informationPackage();
                    //TemporaryAction.setPriorPage(PagesName.page_setEvent);
                    startActivity(new Intent(Activity_SetEvent.this,Activity_EventInfo.class));
                }
            };
        }else{
            return new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    informationPackage();
                    //TemporaryAction.setPriorPage(PagesName.page_setEvent);
                    startActivity(new Intent(Activity_SetEvent.this,Activity_ItemType.class));
                }
            };
        }
    }

}