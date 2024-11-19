package com.example.memorymanager.ui.EventAll;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.memorymanager.Activity_EventInfo;
import com.example.memorymanager.databinding.FragmentEventAllBinding;
import com.example.memorymanager.enums.type;
import com.example.memorymanager.handle.Event;
import com.example.memorymanager.handle.Item;
import com.example.memorymanager.model.AccountEvent;
import com.example.memorymanager.model.AnniversaryEvent;
import com.example.memorymanager.model.CommonEvent;
import com.example.memorymanager.ui.EventPageControl;
import com.example.memorymanager.ui.TemporaryAction;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class EventAllFragment extends Fragment implements EventPageControl {

    //本页面对应的Event类型的事件的容器
    private List<Event>eventList=new ArrayList<>();
    private LinearLayout layout;
    private HashMap<Event,LinearLayout> itemTable =new HashMap<>();

    private FragmentEventAllBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        EventAllViewModel eventAllViewModel =
                new ViewModelProvider(this).get(EventAllViewModel.class);

        binding = FragmentEventAllBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textEventAll;
        //界面事件列表的layout控件对象
        layout=binding.linearLayoutEventAll;
        //界面初始化
        test();
        updateEventLayout();
        Button button=binding.buttonTest;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventList.get(3).setTitle("changed title");
                updateEventLayout(eventList.get(3));
            }
        });

        eventAllViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        eventList.clear();
        itemTable.clear();
        super.onDestroyView();
        binding = null;
    }


    private void test(){
        Item i=new Item(new Date(),"itemTitle","itemDescription",10, type.AccountEvent);
        eventList.add(new AccountEvent("Spring festival",true, new Date(),"empty",i,1));
        eventList.add(new AccountEvent("Summer festival",true, new Date(),"empty",i,1));
        eventList.add(new AccountEvent("Autumn festival",true, new Date(),"empty",i,1));
        eventList.add(new AnniversaryEvent("anniversary 1",true, new Date(),"empty",i,"empty","empty"));
        eventList.add(new AnniversaryEvent("anniversary 2",true, new Date(),"empty",i,"empty","empty"));
        eventList.add(new AnniversaryEvent("anniversary 3",true, new Date(),"empty",i,"empty","empty"));
        eventList.add(new CommonEvent("common 1",true, new Date(),"empty",i,"empty",true));
    }


    /*更新事件列表容器（全部更新），并将更新结果展示到前端Layout控件；
     （仅修改本类的容器和前端控件，不需要关心数据库操作）*/
    @Override
    public void updateEventLayout(){
        for(int i=0;i<eventList.size();i++){
            addEventView(eventList.get(i));
        }
    }

    /*更新所传入的事件对象（无该对象则创建对应的Event对象），并将更新结果展示到前端Layout控件
     （仅修改本类的容器和前端控件，不需要关心数据库操作）*/
    @Override
    public void updateEventLayout(Event event){
        if(itemTable.containsKey(event)){
            layout.removeView(itemTable.get(event));
        }
        addEventView(event);
    }

    //向Layout控件中添加一个代表Event事件的控件
    @SuppressLint("SetTextI18n")
    private void addEventView(Event event){
        TextView textView_temp_mark=new TextView(super.getContext());
        textView_temp_mark.setText("Event:   ");
        textView_temp_mark.setHeight(160);
        textView_temp_mark.setTextSize(20);

        TextView textView_temp_title=new TextView(super.getContext());
        textView_temp_title.setText("title: "+event.getTitle());
        textView_temp_title.setHeight(128);
        textView_temp_title.setTextSize(28);

        LinearLayout layout_temp=new LinearLayout(super.getContext());
        layout_temp.setOrientation(LinearLayout.HORIZONTAL);
        layout_temp.setBackgroundColor(100);
        layout_temp.addView(textView_temp_mark);
        layout_temp.addView(textView_temp_title);
        itemTable.put(event,layout_temp);

        layout_temp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                TemporaryAction.setEventToShow(event);
                startActivity(new Intent(EventAllFragment.super.getContext(), Activity_EventInfo.class));
            }
        });
        layout.addView(layout_temp);
    }
}