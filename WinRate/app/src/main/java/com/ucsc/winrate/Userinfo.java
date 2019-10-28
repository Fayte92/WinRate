package com.ucsc.winrate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class Userinfo extends AppCompatActivity {

    private TextView WLStatic;
    private TextView Username;
    private int wrate = 3;
    private FirebaseAuth firebaseAuth;
    private Button logout;
    private FirebaseDatabase firebaseDatabase;
    private String email;
    private Button input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);

        WLStatic = findViewById(R.id.winratestatic);
        Username = findViewById(R.id.UserName);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

        WLStatic.setText("static: " + wrate);
        /*
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userProfile userProfile = dataSnapshot.getValue(userProfile.class);
                //WLStatic.setText("static: " + wrate);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Userinfo.this, "get data failed", Toast.LENGTH_SHORT).show();
            }

        });

         */




        if(user!= null){
            email = user.getEmail();
        }
        Username.setText("" + email);


        logout = (Button)findViewById(R.id.Logout);
        input = (Button)findViewById(R.id.fightinput);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(Userinfo.this, SignIn.class) );
            }
        });

        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Userinfo.this, MainActivity.class) );
            }
        });

    }
}
