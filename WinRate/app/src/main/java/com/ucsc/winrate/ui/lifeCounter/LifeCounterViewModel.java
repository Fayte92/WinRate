package com.ucsc.winrate.ui.lifeCounter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LifeCounterViewModel extends ViewModel{

    private MutableLiveData<String> mText;

    public LifeCounterViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Life Counter");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
