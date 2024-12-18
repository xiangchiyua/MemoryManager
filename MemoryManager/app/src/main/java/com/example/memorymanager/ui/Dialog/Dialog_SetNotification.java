package com.example.memorymanager.ui.Dialog;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;


import androidx.annotation.NonNull;

import com.example.memorymanager.R;
import com.example.memorymanager.ui.tools.TemporaryAction;

import java.util.Calendar;
import java.util.Date;


public class Dialog_SetNotification extends android.app.Dialog implements View.OnClickListener {

    private String title,message,cancel,confirm;
    private OnCancelListener cancelListener;
    private OnConfirmListener confirmListener;


    public Dialog_SetNotification(@NonNull Context context) {
        super(context);
    }

    public Dialog_SetNotification(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCancel(String cancel,OnCancelListener listener) {
        this.cancel = cancel;
        this.cancelListener=listener;
        RadioButton radioButton =(RadioButton)findViewById(R.id.radioButton_set_no_notification);
        radioButton.setChecked(true);
        this.hide();
    }

    public void setConfirm(String confirm,OnConfirmListener listener) {
        this.confirm = confirm;
        this.confirmListener=listener;
        EditText editText=(EditText)findViewById(R.id.editText_dialog_setNotification_description);
        TemporaryAction.descriptionOfDialogSetNotification=editText.getText().toString();
        setDate();
        this.hide();
    }

    private Date setDate(){
        EditText editText_month=(EditText)findViewById(R.id.editText_dialog_setNotification_month);
        EditText editText_day=(EditText)findViewById(R.id.editText_dialog_setNotification_day);
        EditText editText_hour=(EditText)findViewById(R.id.editText_dialog_setNotification_hour);
        EditText editText_minute=(EditText)findViewById(R.id.editText_dialog_setNotification_minute);
        Date date=new Date();
        Calendar calendar=Calendar.getInstance();
        try{
            if(!editText_month.getText().toString().isEmpty())
                calendar.set(Calendar.MONTH, Integer.parseInt(editText_month.getText().toString()));
            if(!editText_day.getText().toString().isEmpty())
                calendar.set(Calendar.DATE, Integer.parseInt(editText_day.getText().toString()));
            if(!editText_hour.getText().toString().isEmpty())
                calendar.set(Calendar.HOUR, Integer.parseInt(editText_hour.getText().toString()));
            if(!editText_minute.getText().toString().isEmpty())
                calendar.set(Calendar.MINUTE, Integer.parseInt(editText_minute.getText().toString()));
        }catch (Exception exp){}

        date=calendar.getTime();
        TemporaryAction.dateOfDialogSetNotification=date;
        return date;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_set_notifacation);
        //设置宽度，固定代码
        WindowManager m=getWindow().getWindowManager();
        Display d=m.getDefaultDisplay();
        WindowManager.LayoutParams p=getWindow().getAttributes();
        Point size=new Point();
        d.getSize(size);
        p.width=(int)(size.x);//设置dialog的宽度为当前手机屏幕宽度
        p.height=(int)(size.y);
        getWindow().setAttributes(p);

//        if(!TextUtils.isEmpty(title)){//不为空
//            textView1.setText(title);
//        }
//        if(!TextUtils.isEmpty(message)){//不为空
//            textView2.setText(message);
//        }
//        if(!TextUtils.isEmpty(cancel)){//不为空
//            textView3.setText(cancel);
//        }
//        if(!TextUtils.isEmpty(confirm)){//不为空
//            textView4.setText(confirm);
//        }
//        textView3.setOnClickListener(this);
//        textView4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.tv3:
//                if(cancelListener!=null){
//                    cancelListener.onCancel(this);
//                }
//                dismiss();
//                break;
//            case R.id.tv4:
//                if(confirmListener!=null){
//                    confirmListener.onConfirm(this);
//                }
//                dismiss();
//                break;
//        }
    }

    public interface OnCancelListener{
        void onCancel(Dialog_SetNotification dialog);
    }

    public interface OnConfirmListener{
        void onConfirm(Dialog_SetNotification dialog);
    }
}
