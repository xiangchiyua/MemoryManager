package com.example.memorymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.memorymanager.ui.PagesName;
import com.example.memorymanager.ui.TemporaryAction;

public class Activity_Type extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_type);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //初始化返回按钮
        Button button_back=(Button)findViewById(R.id.button_backFrom_page_type);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TemporaryAction.setPriorPage(PagesName.page_type);
                startActivity(new Intent(Activity_Type.this,Activity_Title.class));
            }
        });
        //获取界面对应的按钮对象
        Button button_anniversary=(Button)findViewById(R.id.button_anniversary);
        Button button_study=(Button)findViewById(R.id.button_study);
        Button button_common=(Button)findViewById(R.id.button_common);
        //为本界面按钮添加View.OnClickListener类型的响应对象
        button_anniversary.setOnClickListener(skipToItems(0));
        button_study.setOnClickListener(skipToItems(1));
        button_common.setOnClickListener(skipToItems(2));
    }

    //根据传入的选择事件类别，更新TemporaryAction的相关信息，并跳转到相应界面
    private View.OnClickListener skipToItems(int choose){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TemporaryAction.setChooseFrom_page_type(choose);
                if(TemporaryAction.getChooseFrom_page_title()==0){
                    TemporaryAction.setPriorPage(PagesName.page_type);
                    startActivity(new Intent(Activity_Type.this, Activity_ItemType.class));
                }
                if(TemporaryAction.getChooseFrom_page_title()==2){
                    TemporaryAction.setPriorPage(PagesName.page_type);
                    startActivity(new Intent(Activity_Type.this, Activity_SetEvent.class));
                }
            }
        };
    }
}