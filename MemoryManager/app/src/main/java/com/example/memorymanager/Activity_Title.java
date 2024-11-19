package com.example.memorymanager;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.memorymanager.ui.TemporaryAction;

public class Activity_Title extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_title);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //获取界面对应的按钮对象
        Button button_show=(Button)findViewById(R.id.button_show);
        Button button_select=(Button)findViewById(R.id.button_select);
        Button button_add=(Button)findViewById(R.id.button_add);
        //为本界面按钮添加View.OnClickListener类型的响应对象
        button_show.setOnClickListener(SkipToPage("page_type"));
        button_select.setOnClickListener(SkipToPage("page_select"));
        button_add.setOnClickListener(SkipToPage("page_addType"));
    }

    //根据传入的页面名称，更新TemporaryAction的相关信息，并跳转到相应界面
    private View.OnClickListener SkipToPage(String pageName){
        try{
            switch(pageName){
                case "page_type":
                    return new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            TemporaryAction.setChooseFrom_page_title(0);
                            startActivity(new Intent(Activity_Title.this, Activity_Type.class));
                        }
                    };
                case "page_select":
                    return new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            TemporaryAction.setChooseFrom_page_title(1);
                            startActivity(new Intent(Activity_Title.this, Activity_Select.class));
                        }
                    };
                case "page_addType":
                    return new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            TemporaryAction.setChooseFrom_page_title(2);
                            startActivity(new Intent(Activity_Title.this, Activity_Type.class));
                        }
                    };
                default:
                    break;
            }
        }catch (Exception exp){
            Log.getStackTraceString(exp);
        }
        return null;
    }
}