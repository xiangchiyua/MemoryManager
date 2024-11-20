package com.example.memorymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
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
import com.example.memorymanager.ui.Dialog.Dialog_SetNotification;
import com.example.memorymanager.ui.tools.PagesName;
import com.example.memorymanager.ui.tools.TemporaryAction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Activity_SetEvent extends AppCompatActivity {

    EditText editText_title;

    RadioButton radioButtonAnniversary;
    RadioButton radioButtonaccount;
    RadioButton radioButtonCommon;

    RadioButton radioButton_Need_Notification;
    RadioButton radioButton_No_Notification;

    RadioButton radioButtonUndo;
    RadioButton radioButtonFinished;

    //装载所有消息添加控件的容器
    LinearLayout layoutAllInfo;
    //装载所有添加消息（EditText控件）
    List<EditText> addedInfoList=new ArrayList<>();
    //按钮“添加消息”
    Button button_addInfo;

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

        //初始化标题输入框
        editText_title=(EditText)findViewById(R.id.editText_set_title);
        //初始化返回按钮
        Button button_back=(Button)findViewById(R.id.button_backFrom_page_setEvent);
        button_back.setOnClickListener(skipToPage(TemporaryAction.getPriorPage()));
        //初始化确认按钮
        Button button_setEvent_confirm=(Button) findViewById(R.id.button_setEvent_confirm);
        button_setEvent_confirm.setOnClickListener(skipToPage(PagesName.page_eventInfo));
        //初始化选择框RadioButton
        initRadioButtonAndLayout();
        //初始化消息容器ScrollView.LinearLayout
        initInfoLayout();
    }

    //初始化类型选择框控件
    private void initRadioButtonAndLayout(){
        radioButtonAnniversary=(RadioButton) findViewById(R.id.radioButton_set_type_anniversary);
        radioButtonaccount=(RadioButton)findViewById(R.id.radioButton_set_type_account);
        radioButtonCommon=(RadioButton)findViewById(R.id.radioButton_set_type_common);
        radioButtonAnniversary.setChecked(true);

        radioButton_Need_Notification=(RadioButton) findViewById(R.id.radioButton_set_need_notification);
        radioButton_No_Notification=(RadioButton) findViewById(R.id.radioButton_set_no_notification);
        radioButton_No_Notification.setChecked(true);

        radioButtonUndo=(RadioButton) findViewById(R.id.radioButton_set_undo);
        radioButtonFinished=(RadioButton) findViewById(R.id.radioButton_set_finished);
        radioButtonUndo.setChecked(true);

        radioButton_Need_Notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"dialog set notification",Toast.LENGTH_LONG);

                showDialogSetNotification();
            }
        });
    }

    //初始化消息添加容器ScrollView.LinearLayout
    private void initInfoLayout(){
        layoutAllInfo =(LinearLayout)findViewById(R.id.linear_layout_addInfo_page_setEvent);
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
        for (EditText editText:addedInfoList) {

        }
        Date date=new Date();
        type eventType;
        boolean needNotification;
        boolean finished=false;
        //先连接数据库返回一个页面信息对应的Event事件
        //再将Event保存到TemporaryAction中，供page_eventInfo使用
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
        informationPackage();
        if(TemporaryAction.getIfFromPageEventInfo()){
            return new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TemporaryAction.setPriorPage(PagesName.page_setEvent);
                    startActivity(new Intent(Activity_SetEvent.this,Activity_EventInfo.class));
                }
            };
        }else{
            return new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TemporaryAction.setPriorPage(PagesName.page_setEvent);
                    startActivity(new Intent(Activity_SetEvent.this,Activity_ItemType.class));
                }
            };
        }
    }

}