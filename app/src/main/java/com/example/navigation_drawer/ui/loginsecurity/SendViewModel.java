package com.example.navigation_drawer.ui.loginsecurity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SendViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SendViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Login Security fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}