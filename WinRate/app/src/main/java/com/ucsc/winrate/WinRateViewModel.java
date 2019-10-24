/*
WinRateViewModel.java
Created by Andrew Leamy
Oct 24 2019
View Model for WinRate Database
*/

package com.ucsc.winrate;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ucsc.winrate.table_entities.GameLogEntry;

import java.util.List;

public class WinRateViewModel extends AndroidViewModel {
    private WinRateRepository repository;
    private LiveData<List<GameLogEntry>> allGameLogEntries;

    //Constructor:
    public WinRateViewModel (Application application) {
        super(application);
        this.repository = new WinRateRepository(application);
        this.allGameLogEntries = repository.getAllGameLogEntries();
    }

    //Getter Methods:
    LiveData<List<GameLogEntry>> getAllGameLogEntries(){
        return this.allGameLogEntries;
    }

    public void insert(GameLogEntry gameLogEntry){
        repository.insert(gameLogEntry);
    }
}
