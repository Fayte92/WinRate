package com.ucsc.winrate.ui.contactBook;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DecksViewModel extends ViewModel{

    private MutableLiveData<String> mText;

    public DecksViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Decks");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
