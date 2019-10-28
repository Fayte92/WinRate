package com.ucsc.winrate;

public class userProfile {
    int winSum;
    int loseSum;
    WinRateData winRateData;

    public userProfile(int winSum, int loseSum, WinRateData winRateData){
        this.winSum = winSum;
        this.loseSum = loseSum;
        this.winRateData = winRateData;
    }

    public int getWinSum(){
        return winSum;
    }

    public int getLoseSum() {
        return loseSum;
    }
    public WinRateData getWinRateData()

    {
        return winRateData;
    }

}


