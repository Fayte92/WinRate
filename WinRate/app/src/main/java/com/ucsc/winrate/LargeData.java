package com.ucsc.winrate;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
//This is just a Class file which is not used by any activity recently, leave it alone or delete willnot
//impact the program running.
public class LargeData {
    int winSum;
    int loseSum;
    WinRateData curData;
    String dataPath;
    FirebaseUser user;
    DatabaseReference myRef;

    public LargeData(int winSum, int loseSum, WinRateData curData, String dataPath, FirebaseUser user, DatabaseReference myRef){
        this.winSum = winSum;
        this.loseSum = loseSum;
        this.curData = curData;
        myRef.child(user.getUid()).child(dataPath).setValue(curData);
    }

    public int getWinSum() {
        return winSum;
    }

    public int getLoseSum() {
        return loseSum;
    }

    public WinRateData getCurData() {
        return curData;
    }
}
