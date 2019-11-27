package com.ucsc.winrate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    View parentView;//ID of parent view for reference

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        parentView = findViewById(R.id.signInLayout);
        //MyUtilities.setupUI(parentView);
        firebaseAuth = FirebaseAuth.getInstance();

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final Button registerButton = findViewById(R.id.register);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name = usernameEditText.getText().toString().trim();
                String user_password = passwordEditText.getText().toString().trim();
                firebaseAuth.signInWithEmailAndPassword(user_name, user_password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    //FirebaseUser user = firebaseAuth.getCurrentUser();
                                    Toast.makeText(SignIn.this, "sign in success", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(SignIn.this, MainActivity.class));
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(SignIn.this, "sign in failed", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(SignIn.this, SignIn.class));
                                }

                            }
                        });

            }
        });


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name = usernameEditText.getText().toString().trim();
                String user_password = passwordEditText.getText().toString().trim();
                firebaseAuth.createUserWithEmailAndPassword(user_name, user_password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    //FirebaseUser user = firebaseAuth.getCurrentUser();
                                    Toast.makeText(SignIn.this, "register success", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(SignIn.this, MainActivity.class));
                                } else {
                                    Toast.makeText(SignIn.this, "register failed", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(SignIn.this, SignIn.class));
                                }
                            }
                        });
            }
        });
    }

}
