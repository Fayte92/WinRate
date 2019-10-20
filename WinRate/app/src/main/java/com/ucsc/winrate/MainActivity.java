package com.ucsc.winrate;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    //declare the  necessary variables.
    static int winC,loseC,curWinSum,curLoseSum;//winC,loseC are the counter of win or lose.
    static int DataCounter = 0;
    static boolean result;//result iss used for storing the value of switch.
    EditText playerDeck,opponentName, opponentDeck;// EditText for user input.
    Switch WinLose;
    Button submit;

    FirebaseDatabase mFireBase = FirebaseDatabase.getInstance();//declare Firebase database
    DatabaseReference myRef = mFireBase.getReference();//reference of database, mainly used by us as a path for storing files
    FirebaseUser curUser = FirebaseAuth.getInstance().getCurrentUser();//Current User, it is used to get Displayname, email or Uid.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //declare each element in the input layout
        playerDeck = findViewById(R.id.playerDeckText);
        opponentName = findViewById(R.id.opponentNameText);
        opponentDeck = findViewById(R.id.opponentDeckText);

        WinLose = findViewById(R.id.winSwitch);

        submit = findViewById(R.id.submitButton);
        //OnCheckChangeListener is a listener which can detect the switch is on or off
        WinLose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                result = isChecked;
                if(isChecked){
                    winC = 1;
                }else{
                    loseC = 1;
                }
            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });



    }
    //addData() will add the data to the firebase
    private void addData(){
        //String UserID = curUser.getDisplayName();
        //String id = myRef.push().getKey();
        //get date data by Calendar.
        //And get text from elements in layout and convert them to string
        Date curD = Calendar.getInstance().getTime();
        String pD = playerDeck.getText().toString();
        String oN = opponentName.getText().toString();
        String oD = opponentDeck.getText().toString();
        //String DataName = "data" + DataCounter;
        //Toast.makeText(this,UserID,Toast.LENGTH_LONG).show();

        WinRateData winData = new WinRateData(pD,oN,oD,result,curD);
        DatabaseReference newRef = myRef.child(curUser.getUid());
        newRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                curWinSum = Integer.parseInt(dataSnapshot.child("winSum").getValue().toString());
                curLoseSum = Integer.parseInt(dataSnapshot.child("loseSum").getValue().toString());
                curWinSum = curWinSum + winC;
                curLoseSum = curLoseSum + loseC;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myRef.child(curUser.getUid()).child("winSum").setValue(curWinSum);
        myRef.child(curUser.getUid()).child("loseSum").setValue(curLoseSum);
        //myRef.child(curUser.getUid()).child("winSum").setValue(winC);
        //myRef.child(curUser.getUid()).child("loseSum").setValue(loseC);
        myRef.child(curUser.getUid()).child(myRef.push().getKey()).setValue(winData);

        //LargeData newdata = new LargeData(winC, loseC,  winData, DataName, curUser, myRef);
        DataCounter++;

    }
}
