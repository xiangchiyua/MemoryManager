package com.example.memorymanager.ui.EventAll;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EventAllViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public EventAllViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}