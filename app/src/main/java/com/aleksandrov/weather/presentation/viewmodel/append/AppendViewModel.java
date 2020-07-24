package com.aleksandrov.weather.presentation.viewmodel.append;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AppendViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AppendViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is append fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}