package com.example.navigation_drawer.ui.myparkingvehicle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SlideshowViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SlideshowViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is My Parking & Vehicle fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}