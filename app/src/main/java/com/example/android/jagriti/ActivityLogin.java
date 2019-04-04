package com.example.android.jagriti;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ActivityLogin extends AppCompatActivity {



    EditText loginEmail;
    EditText loginPassword;
    Button signin2;
    TextView signup2;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate( savedInstanceState );
        setContentView( R.layout.login_activity );


        firebaseAuth=FirebaseAuth.getInstance();
        loginEmail=(EditText)findViewById( R.id.loginemail );
        loginPassword=(EditText)findViewById( R.id.loginpassword );
        signup2=(TextView)findViewById( R.id.signup2 );
        signin2=(Button)findViewById( R.id.signin2 );

        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user=firebaseAuth.getCurrentUser();

                if(user!=null){

                    Toast.makeText(ActivityLogin.this, "User logged in ", Toast.LENGTH_SHORT).show();
                    Intent I = new Intent(ActivityLogin.this, UserActivity.class);
                    startActivity(I);
                }

                else {
                    Toast.makeText(ActivityLogin.this, "Login to continue", Toast.LENGTH_SHORT).show();
                }

            }
        };


        signin2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String userEmail = loginEmail.getText().toString();
                String userPaswd = loginPassword.getText().toString();

                if (userEmail.isEmpty()) {
                    loginEmail.setError("Provide your Email first!");
                    loginEmail.requestFocus();
                }

                else if (userPaswd.isEmpty()) {
                    loginPassword.setError("Enter Password!");
                    loginPassword.requestFocus();
                }

                else if (userEmail.isEmpty() && userPaswd.isEmpty()) {
                    Toast.makeText(ActivityLogin.this, "Fields Empty!", Toast.LENGTH_SHORT).show();
                }

                else if (!(userEmail.isEmpty() && userPaswd.isEmpty())) {
                    firebaseAuth.signInWithEmailAndPassword(userEmail, userPaswd).addOnCompleteListener( ActivityLogin.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {
                                Toast.makeText(ActivityLogin.this, "Not sucessfull", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(ActivityLogin.this, UserActivity.class));
                            }

                        }
                    } );

                           /* addOnCompleteListener(ActivityLogin.this, new OnCompleteListener() {
                                @Override
                                public void onComplete(@NonNull Task task) {
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(ActivityLogin.this, "Not sucessfull", Toast.LENGTH_SHORT).show();
                                    } else {
                                        startActivity(new Intent(ActivityLogin.this, UserActivity.class));
                                    }
                                }
                            });*/
                } else {
                    Toast.makeText(ActivityLogin.this, "Error", Toast.LENGTH_SHORT).show();
                }


            }
        } );


        signup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(ActivityLogin.this, MainActivity.class);
                startActivity(I);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener( authStateListener );
    }
}
