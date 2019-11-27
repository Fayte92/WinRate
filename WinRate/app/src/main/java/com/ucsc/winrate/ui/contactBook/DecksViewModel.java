package com.ucsc.winrate.ui.contactBook;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ucsc.winrate.WinRateRepository;
import com.ucsc.winrate.table_entities.DeckProfile;

import java.util.List;

public class DecksViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;
    private WinRateRepository repository;
    private LiveData<List<DeckProfile>> allDeckProfiles; //Cached copy of deck profiles table

    public DecksViewModel(@NonNull Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue("Decks");
        this.repository = new WinRateRepository(application);
        this.allDeckProfiles = repository.getAllDeckProfiles();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void insert(DeckProfile profile){
        repository.insert(profile);
    }

    public LiveData<List<DeckProfile>> getAllDeckProfiles(){
        return this.allDeckProfiles;
    }
}
