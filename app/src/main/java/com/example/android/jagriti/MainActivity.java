package com.example.android.jagriti;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText password;

    Button signup;
    TextView signin1;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );


        firebaseAuth=FirebaseAuth.getInstance();
        email=(EditText)findViewById( R.id.email );
        password=(EditText)findViewById( R.id.password );
        signup=(Button)findViewById( R.id.signup );
        signin1=(TextView)findViewById( R.id.signin1 );
       progressBar=(ProgressBar)findViewById( R.id.signupProgressbar );
        FirebaseUser user=firebaseAuth.getCurrentUser();

        if(user!=null){
            Intent i=new Intent( this,UserActivity.class );
            startActivity( i );
        }



        signup.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailid=email.getText().toString();
                String Password=password.getText().toString();

                if(emailid.isEmpty()){
                    email.setError( "Please Provide your Email" );
                    email.requestFocus();
                }

                else if(Password.isEmpty()){
                    password.setError( "Set your password" );
                    password.requestFocus();
                }

                else if (emailid.isEmpty() && Password.isEmpty()){
                    Toast.makeText(MainActivity.this, "Fields Empty!", Toast.LENGTH_SHORT).show();
                }

                else if(!(emailid.isEmpty() && Password.isEmpty())){

                    firebaseAuth.createUserWithEmailAndPassword( emailid,Password ).addOnCompleteListener( MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {


                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this.getApplicationContext(),
                                        "SignUp unsuccessful: " + task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            } else {

                                startActivity(new Intent(MainActivity.this, UserActivity.class));
                            }

                        }
                    } );
                }

                else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }
        } );

       // progressBar.setVisibility( View.VISIBLE );

        signin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(MainActivity.this, ActivityLogin.class);
                startActivity(I);
            }
        });
    }

}

