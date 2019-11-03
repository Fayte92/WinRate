package com.ucsc.winrate.table_entities;

import androidx.annotation.NonNull;
import androidx.room.*;

@Entity(tableName = "opponent_profiles")
public class OpponentProfile {
    //table columns:
    @ColumnInfo(name = "ID")
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "First Name")
    @NonNull //Opponent profiles must have at least one name field for identification purposes
    private String firstName;

    @ColumnInfo(name = "Last Name")
    private String lastName;

    @ColumnInfo(name = "Nickname")
    private String nickname; //User may set nicknames to distinct between opponents with the same
                            //first name

    @ColumnInfo(name = "Times Faced")
    private int timesFaced;

    @ColumnInfo(name = "Times Won Against")
    private int timesWonAgainst;

    @ColumnInfo(name = "Times Lost Against")
    private int timesLostAgainst;

    //TODO: add a private variable to store the profile image in

    //Constructor
    public OpponentProfile(String firstName, String lastName, String nickname){
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.timesFaced = 0;
        this.timesWonAgainst = 0;
        this.timesLostAgainst = 0;
    }

    //getter methods:
    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public int getTimesFaced() {
        return timesFaced;
    }

    public int getTimesWonAgainst() {
        return timesWonAgainst;
    }

    public int getTimesLostAgainst() {
        return timesLostAgainst;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    //Setter methods:

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setTimesFaced(int timesFaced) {
        this.timesFaced = timesFaced;
    }

    public void setTimesWonAgainst(int timesWonAgainst) {
        this.timesWonAgainst = timesWonAgainst;
    }

    public void setTimesLostAgainst(int timesLostAgainst) {
        this.timesLostAgainst = timesLostAgainst;
    }
}
