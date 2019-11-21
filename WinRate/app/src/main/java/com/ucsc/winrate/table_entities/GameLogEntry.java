/*
GameLogEntry.java
Created by Andrew Leamy
Oct 24 2019
The GameLogEntry class represents the Master Table of the app's Game Log
Each GameLogEntry instance is a row of that Master Table
*/
package com.ucsc.winrate.table_entities;

import androidx.annotation.NonNull;
import androidx.room.*;

@Entity(tableName = "master_table")
public class GameLogEntry {

    //Column Fields:

    @PrimaryKey /*A Date object, being a combination of calendar date and system time, will be the
    primary key for the master table. */
    @NonNull
    @ColumnInfo(name = "Date")
    private String date;

    @NonNull
    @ColumnInfo(name = "Win")
    private boolean winStatus;

    @ColumnInfo(name = "Opponent")
    private String opponentName;

    @ColumnInfo(name = "Opponent Deck")
    private String opponentDeck;

    @ColumnInfo(name = "Deck Used")
    private String userDeck;


    //Constructor
    public GameLogEntry (String date, boolean winStatus, String opponentName,
                        String opponentDeck, String userDeck){
        this.date = date;
        this.winStatus = winStatus;
        this.opponentName = opponentName;
        this.opponentDeck = opponentDeck;
        this.userDeck = userDeck;

    }


    //Getter Functions:

    public String getDate(){
        return this.date;
    }

    public boolean getWinStatus(){
        return this.winStatus;
    }

    public String getOpponentName(){
        return this.opponentName;
    }

    public String getOpponentDeck(){
        return this.opponentDeck;
    }

    public String getUserDeck(){
        return this.userDeck;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
    }

    public void setWinStatus(boolean winStatus) {
        this.winStatus = winStatus;
    }

    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }

    public void setOpponentDeck(String opponentDeck) {
        this.opponentDeck = opponentDeck;
    }

    public void setUserDeck(String userDeck) {
        this.userDeck = userDeck;
    }

}
