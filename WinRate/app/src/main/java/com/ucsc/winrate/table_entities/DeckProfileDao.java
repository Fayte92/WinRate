/*
package com.ucsc.winrate.table_entities;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DeckProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDeckProfile(DeckProfile profile);

    @Delete
    void deleteDeckProfile(DeckProfile profile);

    @Query("DELETE FROM deck_profiles")
    void deleteAll();

    @Query("SELECT * FROM deck_profiles WHERE ID=:id")
    LiveData<List<DeckProfile>> getDeckProfile(int id);
}
*/
