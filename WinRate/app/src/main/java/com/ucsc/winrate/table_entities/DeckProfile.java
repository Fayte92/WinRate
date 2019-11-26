/*
package com.ucsc.winrate.table_entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "deck_profiles")
public class DeckProfile {
    //Table Columns:
    @ColumnInfo(name = "ID")
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "Name")
    @NonNull //Deck Profiles must have a name for identification purposes
    private String name;

    @ColumnInfo(name = "Owner")
    private String owner;

    @ColumnInfo(name = "Owner ID")
    private int ownerID;

    public DeckProfile(int id, @NonNull String name, String owner, int ownerID) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.ownerID = ownerID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }
}*/
