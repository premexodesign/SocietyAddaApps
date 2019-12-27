package com.example.navigation_drawer.ui.mybankaccount;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ShareViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ShareViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is My Bank Account fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}