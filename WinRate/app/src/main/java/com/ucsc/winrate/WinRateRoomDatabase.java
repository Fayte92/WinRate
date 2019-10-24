/*
WinRateRoomDatabase.java
Created by Andrew Leamy
Oct 24 2019
This abstract class represents the entire app database.
Each entity class represents a table which is part of this database
Dao's act as the interface between tables and the database
*/

package com.ucsc.winrate;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ucsc.winrate.table_entities.*;

@Database(entities = {GameLogEntry.class}, version = 1)
public abstract class WinRateRoomDatabase extends RoomDatabase {
    //Define Dao's for each table in the database
    public abstract GameLogEntryDao gameLogEntryDao();

    //Database must be singleton to prevent errors
    //begin singleton code
    private static volatile WinRateRoomDatabase INSTANCE;

    static WinRateRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WinRateRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WinRateRoomDatabase.class, "winrate_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
