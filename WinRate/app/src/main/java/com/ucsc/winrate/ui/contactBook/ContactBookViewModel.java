package com.ucsc.winrate.ui.contactBook;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ContactBookViewModel extends ViewModel{

    private MutableLiveData<String> mText;

    public ContactBookViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Contact Book");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
