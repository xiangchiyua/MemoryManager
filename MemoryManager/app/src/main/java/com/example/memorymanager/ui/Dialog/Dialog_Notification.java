package com.example.memorymanager.ui.Dialog;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;

import androidx.annotation.NonNull;

import com.example.memorymanager.R;


public class Dialog_Notification extends android.app.Dialog implements View.OnClickListener {

    private String title,message,cancel,confirm;
    private OnCancelListener cancelListener;
    private OnConfirmListener confirmListener;


    public Dialog_Notification(@NonNull Context context) {
        super(context);
    }

    public Dialog_Notification(@NonNull Context context, int themeResId) {
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
        this.hide();
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
        void onCancel(Dialog_Notification dialog);
    }

    public interface OnConfirmListener{
        void onConfirm(Dialog_Notification dialog);
    }
}
