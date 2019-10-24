/*
GameLogEntryDao.java
Created by Andrew Leamy
Oct 24 2019
The GameLogEntryDao class acts as the Dao between GameLogEntry and the Room Database
A Dao defines methods which executes specific SQL commands on the room database,
effectively acting as an API for accessing the database.
*/

package com.ucsc.winrate.table_entities;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GameLogEntryDao {

    //addRow adds a GameLogEntry instance to the database as a new Table
    //If newEntry has the same primary key as a row already in the database,
    //newEntry will overwrite the entry already in the database.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addRow(GameLogEntry newEntry);

    //Deletes all rows in the Master Table
    @Query("DELETE FROM master_table")
    void deleteAll();

    //Returns a list of all GameLogEntry instances in the database, ordered descending by date
    @Query("SELECT * FROM master_table ORDER BY Date DESC")
    LiveData<List<GameLogEntry>> getAll();
}
