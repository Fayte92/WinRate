package com.ucsc.winrate.ui.inputForm;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ucsc.winrate.WinRateRepository;
import com.ucsc.winrate.table_entities.GameLogEntry;

import java.util.List;

public class InputFormViewModel extends ViewModel{

    private WinRateRepository repository;
    private LiveData<List<GameLogEntry>> allGameLogEntries; //cached copy of database

    private MutableLiveData<String> mText;

    public InputFormViewModel(Application application) {
        mText = new MutableLiveData<>();
        mText.setValue("Input Form");
        this.repository = new WinRateRepository(application);
        this.allGameLogEntries = repository.getAllGameLogEntries();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<GameLogEntry>> getAllGameLogEntries(){
        return this.allGameLogEntries;
    }

    public void insert(GameLogEntry gameLogEntry){
        repository.insert(gameLogEntry);
    }
}
