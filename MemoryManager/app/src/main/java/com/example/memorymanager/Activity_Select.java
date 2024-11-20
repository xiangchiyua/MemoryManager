package com.example.memorymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.memorymanager.ui.tools.PagesName;
import com.example.memorymanager.ui.tools.TemporaryAction;

public class Activity_Select extends AppCompatActivity {

    EditText editText_title;

    RadioButton radioButton_type_Anniversary;
    RadioButton radioButton_type_account;
    RadioButton radioButton_type_common;

    RadioButton radioButton_need_notification;
    RadioButton radioButton_no_notification;

    RadioButton radioButton_undo;
    RadioButton radioButton_finished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_select);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //初始化标题输入框
        editText_title=(EditText)findViewById(R.id.editText_select_title);
        editText_title.setText("");
        //初始化返回按钮
        Button button_back=(Button) findViewById(R.id.button_backFrom_page_select);
        button_back.setOnClickListener(skipToPage(PagesName.page_title));
        //初始化确认按钮
        Button button_confirm=(Button) findViewById(R.id.button_select_confirm);
        button_confirm.setOnClickListener(skipToPage(PagesName.page_itemSelect));
    }


    //初始化RadioButton选择框控件
    private void initRadioButton(){
        TemporaryAction.checkedRadioInPageSelect[0]=-1;
        TemporaryAction.checkedRadioInPageSelect[1]=-1;
        TemporaryAction.checkedRadioInPageSelect[2]=-1;
        radioButton_type_account=(RadioButton) findViewById(R.id.radioButton_select_type_account);
        radioButton_type_Anniversary=(RadioButton)findViewById(R.id.radioButton_select_type_anniversary);
        radioButton_type_common=(RadioButton) findViewById(R.id.radioButton_select_type_common);
        radioButton_need_notification=(RadioButton) findViewById(R.id.radioButton_select_type_needNotification);
        radioButton_no_notification=(RadioButton) findViewById(R.id.radioButton_set_no_notification);
        radioButton_finished=(RadioButton) findViewById(R.id.radioButton_select_type_finished);
        radioButton_undo=(RadioButton) findViewById(R.id.radioButton_select_type_undo);
    }

    //将界面信息打包到TemporaryAction中（editText_title和checkedRadioInPageSelect[]）
    private void informationPackage(){
        TemporaryAction.setEditTextTitleInPageSelect(editText_title.getText().toString());
        if(radioButton_type_Anniversary.isChecked())
            TemporaryAction.checkedRadioInPageSelect[0]=0;
        else if (radioButton_type_account.isChecked())
            TemporaryAction.checkedRadioInPageSelect[0]=1;
        else if(radioButton_type_common.isChecked())
            TemporaryAction.checkedRadioInPageSelect[0]=2;
        if(radioButton_finished.isChecked())
            TemporaryAction.checkedRadioInPageSelect[1]=0;
        else if(radioButton_undo.isChecked())
            TemporaryAction.checkedRadioInPageSelect[1]=1;
        if(radioButton_need_notification.isChecked())
            TemporaryAction.checkedRadioInPageSelect[2]=0;
        else if(radioButton_no_notification.isChecked())
            TemporaryAction.checkedRadioInPageSelect[2]=1;
    }

    //跳转界面
    private View.OnClickListener skipToPage(PagesName pagesName){
        //先打包信息
        informationPackage();
        //再判断跳转界面
        if(pagesName.equals(PagesName.page_title)){
            return new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TemporaryAction.setPriorPage(PagesName.page_select);
                    startActivity(new Intent(Activity_Select.this,Activity_Title.class));
                }
            };
        } else if (pagesName.equals(PagesName.page_itemSelect)) {
            return new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TemporaryAction.setPriorPage(PagesName.page_select);
                    startActivity(new Intent(Activity_Select.this,Activity_ItemSelect.class));
                }
            };
        }
        return null;
    }
}