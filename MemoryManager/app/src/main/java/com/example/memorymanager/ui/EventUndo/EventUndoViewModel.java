package com.example.memorymanager.ui.EventUndo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EventUndoViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public EventUndoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}