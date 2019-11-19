package com.ucsc.winrate.ui.lifeCounter;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ucsc.winrate.WinRateRepository;
import com.ucsc.winrate.table_entities.OpponentProfile;

import java.util.List;

public class LifeCounterViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;
    private WinRateRepository repository;
    private LiveData<List<OpponentProfile>> allOpponentProfiles; //cached copy of database

    public LifeCounterViewModel(@NonNull Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue("Life Counter");
        this.repository = new WinRateRepository(application);
        this.allOpponentProfiles = repository.getAllOpponentProfiles();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<java.util.List<OpponentProfile>> getAllOpponentProfiles(){
        return this.allOpponentProfiles;
    }

    //This method shouldn't be necessary:
    public void insert(OpponentProfile newProfile){
        repository.insert(newProfile);
    }
}
