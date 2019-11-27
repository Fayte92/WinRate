package com.ucsc.winrate.ui.settings;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ucsc.winrate.WinRateRepository;
import com.ucsc.winrate.table_entities.DeckProfile;
import com.ucsc.winrate.table_entities.GameLogEntry;
import com.ucsc.winrate.table_entities.OpponentProfile;

import java.util.List;

public class SettingsViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;
    private WinRateRepository repository;
    private LiveData<List<GameLogEntry>> allGameLogEntries; //cached copy of table
    private LiveData<List<OpponentProfile>> allOpponentProfiles; // cached copy of table
    private LiveData<List<DeckProfile>> allDeckProfiles; //cached copy of table

    public SettingsViewModel(@NonNull Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue("Settings");
        this.repository = new WinRateRepository(application);
        this.allGameLogEntries = repository.getAllGameLogEntries();
        this.allOpponentProfiles = repository.getAllOpponentProfiles();
        this.allDeckProfiles = repository.getAllDeckProfiles();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void insert(GameLogEntry entry){
        repository.insert(entry);
    }

    public void insert(OpponentProfile profile) { repository.insert(profile); }

    public void insert(DeckProfile profile) { repository.insert(profile); }

    public LiveData<java.util.List<GameLogEntry>> getAllGameLogEntries(){
        return this.allGameLogEntries;
    }

    public LiveData<java.util.List<OpponentProfile>> getAllOpponentProfiles(){
        return this.allOpponentProfiles;
    }

    public LiveData<List<DeckProfile>> getAllDeckProfiles(){
        return this.allDeckProfiles;
    }

    public void deleteAllGameLogEntries(){
        repository.deleteAllGameLogEntries();
    }

    public void deleteAllOpponentProfiles() { repository.deleteAllOpponentProfils(); }

    public void deleteAllDeckProfiles() { repository.deleteAllDeckProfiles(); }
}
