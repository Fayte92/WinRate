package com.ucsc.winrate.ui.lifeCounter;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ucsc.winrate.WinRateRepository;
import com.ucsc.winrate.table_entities.GameLogEntry;

import java.util.List;

public class NoticeDialogViewModel extends AndroidViewModel {
    private WinRateRepository repository;
    private LiveData<List<GameLogEntry>> allGameLogEntries; //cached copy of database

    public NoticeDialogViewModel(@NonNull Application application) {
        super(application);
        this.repository = new WinRateRepository(application);
        this.allGameLogEntries = repository.getAllGameLogEntries();
    }

    public void insert(GameLogEntry entry){
        repository.insert(entry);
    }

    public LiveData<List<GameLogEntry>> getAllGameLogEntries(){
        return this.allGameLogEntries;
    }

    public void deleteAllGameLogEntries(){
        repository.deleteAllGameLogEntries();
    }
}
