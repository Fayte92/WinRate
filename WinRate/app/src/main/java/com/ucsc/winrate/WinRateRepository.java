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

import com.ucsc.winrate.table_entities.*;

import java.util.List;

public class WinRateRepository {

    private GameLogEntryDao gameLogEntryDao;
    private OpponentProfileDao opponentProfileDao;
    private LiveData<List<GameLogEntry>> allGameLogEntries;

    //constructor:
    public WinRateRepository(Application application) {
        WinRateRoomDatabase db = WinRateRoomDatabase.getDatabase(application);
        this.gameLogEntryDao = db.gameLogEntryDao();
        this.opponentProfileDao = db.opponentProfileDao();
        allGameLogEntries = gameLogEntryDao.getAll();
    }

    //SQL functions:

    public void insert (GameLogEntry gameLogEntry) {
        new insertGameLogEntryAsyncTask(this.gameLogEntryDao).execute(gameLogEntry);
    }

    public void delete (GameLogEntry entry){
        new deleteGameLogEntryAsyncTask(this.gameLogEntryDao).execute(entry);
    }

    public void insert (OpponentProfile opponentProfile){
        new insertOpponentProfileAsyncTask(this.opponentProfileDao).execute(opponentProfile);
    }

    public LiveData<List<GameLogEntry>> getAllGameLogEntries(){
        return this.allGameLogEntries;
    }

    public void deleteAllGameLogEntries(){
        new deleteAllGameLogEntriesAsyncTask(this.gameLogEntryDao).execute();
    }

    public LiveData<List<OpponentProfile>> getAllOpponentProfiles(){
        return opponentProfileDao.getAll();
    }

//    public GameLogEntry getGameLogEntryByDate(String date){
//        return gameLogEntryDao.getGameLogEntry(date).getValue().get(0);
//    }
//
//    public OpponentProfile getOpponentProfileById(int id){
//        return opponentProfileDao.getOpponentProfile(id).getValue().get(0);
//    }


    //Private Sub-classes insertAsyncTask
    private static class insertGameLogEntryAsyncTask extends AsyncTask<GameLogEntry, Void, Void> {

        private GameLogEntryDao mAsyncTaskDao;

        private insertGameLogEntryAsyncTask(GameLogEntryDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final GameLogEntry... params) {
            mAsyncTaskDao.insertGameLogEntry(params[0]);
            return null;
        }
    }

    private static class deleteGameLogEntryAsyncTask extends AsyncTask<GameLogEntry, Void, Void> {

        private GameLogEntryDao mAsyncTaskDao;

        private deleteGameLogEntryAsyncTask(GameLogEntryDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final GameLogEntry... params) {
            mAsyncTaskDao.deleteGameLogEntry(params[0]);
            return null;
        }
    }

    private static class deleteAllGameLogEntriesAsyncTask extends AsyncTask<Void, Void, Void> {

        private GameLogEntryDao mAsyncTaskDao;

        private deleteAllGameLogEntriesAsyncTask(GameLogEntryDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    private static class insertOpponentProfileAsyncTask extends AsyncTask<OpponentProfile, Void, Void> {

        private OpponentProfileDao mAsyncTaskDao;

        insertOpponentProfileAsyncTask(OpponentProfileDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final OpponentProfile... params) {
            mAsyncTaskDao.insertOpponentProfile(params[0]);
            return null;
        }
    }
}
