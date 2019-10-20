package com.ucsc.winrate;

import java.util.Date;

//This is the Class file which store the Player Deck, Opponent Name and  Deck, Date and  win or lose.
public class WinRateData {
    String plaD;
    String oppN;
    String oppD;
    boolean WL;
    Date curDate;

    public WinRateData(String plaD, String oppN, String oppD, boolean WL, Date curDate){
       this.plaD = plaD;
       this.oppN = oppN;
       this.oppD = oppD;
       this.WL = WL;
       this.curDate = curDate;
    }


    public String getPlaD() {
        return plaD;
    }

    public String getOppN() {
        return oppN;
    }

    public String getOppD() {
        return oppD;
    }

    public boolean isWL() {
        return WL;
    }

    public Date getCurDate() {
        return curDate;
    }
}
