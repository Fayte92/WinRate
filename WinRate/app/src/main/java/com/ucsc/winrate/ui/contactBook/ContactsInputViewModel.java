package com.ucsc.winrate.ui.contactBook;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ucsc.winrate.WinRateRepository;
import com.ucsc.winrate.table_entities.GameLogEntry;
import com.ucsc.winrate.table_entities.OpponentProfile;

import java.util.List;

public class ContactsInputViewModel extends AndroidViewModel {

    private WinRateRepository repository;
    private LiveData<List<OpponentProfile>> allOpponentProfiles; //cached copy of database

    public ContactsInputViewModel(@NonNull Application application) {
        super(application);
        this.repository = new WinRateRepository(application);
        this.allOpponentProfiles = repository.getAllOpponentProfiles();
    }


    public LiveData<java.util.List<OpponentProfile>> getAllOpponentProfiles(){
        return this.allOpponentProfiles;
    }
}
