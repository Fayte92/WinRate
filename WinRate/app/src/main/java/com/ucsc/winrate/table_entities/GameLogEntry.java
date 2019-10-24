package com.ucsc.winrate.table_entities;

import androidx.annotation.NonNull;
import androidx.room.*;

import java.util.Date;

//The GameLogEntry class represents the Master Table of the app
//Each GameLogEntry instance is a row of the Master Table
@Entity(tableName = "word_table")
public class GameLogEntry {

    //Column Data:
    @PrimaryKey /*A Date object, being a combination of calendar date and time, will be the
    //primary key for the master table. */
    @NonNull
    @ColumnInfo(name = "Date")
    private Date date;

    @NonNull
    @ColumnInfo(name = "Win")
    private boolean victoryCondition;

    @ColumnInfo(name = "Opponent")
    private String opponentName;

    @ColumnInfo(name = "Opponent Deck")
    private String opponentDeck;

    @ColumnInfo(name = "Deck Used")
    private String userDeck;



    public GameLogEntry (Date date, boolean victoryCondition, String opponentName,
                        String opponentDeck, String userDeck){
        this.date = date;
        this.victoryCondition = victoryCondition;
        this.opponentName = opponentName;
        this.opponentDeck = opponentDeck;
        this.userDeck = userDeck;

    }


    //Getter Functions:

    public Date getDate(){
        return this.date;
    }

    public boolean getVictoryCondition(){
        return this.victoryCondition;
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

}
