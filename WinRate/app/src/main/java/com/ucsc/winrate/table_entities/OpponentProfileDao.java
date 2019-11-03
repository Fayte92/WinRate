package com.ucsc.winrate.table_entities;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface OpponentProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOpponentProfile(OpponentProfile newEntry);

    @Query("DELETE FROM opponent_profiles")
    void deleteAll();

    @Query("SELECT * FROM opponent_profiles ORDER BY ID DESC")
    LiveData<List<OpponentProfile>> getAll();

    @Query("Select * FROM opponent_profiles WHERE ID=:id")
    LiveData<List<OpponentProfile>> getOpponentProfile(int id);
}
