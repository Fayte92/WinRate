package com.ucsc.winrate.ui.inputForm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InputFormViewModel extends ViewModel{

    private MutableLiveData<String> mText;

    public InputFormViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is input form fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
