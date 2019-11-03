/*
WinRateRepository.java
Created by Andrew Leamy
Oct 24 2019
Repository for WinRate Database
*/

package com.ucsc.winrate;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ucsc.winrate.table_entities.GameLogEntry;
import com.ucsc.winrate.table_entities.GameLogEntryDao;

import java.util.List;

public class WinRateRepository {

    private GameLogEntryDao gameLogEntryDao;
    private LiveData<List<GameLogEntry>> allGameLogEntries;

    //constructor:
    WinRateRepository(Application application) {
        WinRateRoomDatabase db = WinRateRoomDatabase.getDatabase(application);
        this.gameLogEntryDao = db.gameLogEntryDao();
        this.allGameLogEntries = gameLogEntryDao.getAll();
    }

    //getter functions:
    LiveData<List<GameLogEntry>> getAllGameLogEntries() {
        return allGameLogEntries;
    }

    public void insert (GameLogEntry gameLogEntry) {
        new insertAsyncTask(this.gameLogEntryDao).execute(gameLogEntry);
    }

    //Private Sub-class insertAsyncTask
    private static class insertAsyncTask extends AsyncTask<GameLogEntry, Void, Void> {

        private GameLogEntryDao mAsyncTaskDao;

        insertAsyncTask(GameLogEntryDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final GameLogEntry... params) {
            mAsyncTaskDao.insertGameLogEntry(params[0]);
            return null;
        }
    }
}
