package com.ucsc.winrate.ui.stats;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ucsc.winrate.WinRateRepository;
import com.ucsc.winrate.table_entities.GameLogEntry;

import java.util.List;

public class StatsViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;
    private WinRateRepository repository;
    private LiveData<List<GameLogEntry>> allGameLogEntries; //cached copy of game log table

    public StatsViewModel(@NonNull Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue("Statistics");
        this.repository = new WinRateRepository(application);
        this.allGameLogEntries = repository.getAllGameLogEntries();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void insert(GameLogEntry entry){
        repository.insert(entry);
    }

    public LiveData<List<GameLogEntry>> getAllGameLogEntries(){
        return this.allGameLogEntries;
    }
}
