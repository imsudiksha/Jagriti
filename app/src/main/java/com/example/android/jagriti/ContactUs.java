package com.example.android.jagriti;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class ContactUs extends AppCompatActivity {

    ImageButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_contact_us );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        button=(ImageButton)findViewById(R.id.message);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                sendEmail();
            }
        });
    }

    protected void sendEmail(){
        Log.i("Send Email","");
        String[]to={"support@gmail.com"};
        Intent emailIntent=new Intent( Intent.ACTION_SEND );
        emailIntent.putExtra( Intent.EXTRA_EMAIL, to );
        emailIntent.putExtra( Intent.EXTRA_SUBJECT,"Here goes Subject" );

        try{startActivity( Intent.createChooser( emailIntent,"Send Mail")
        );
            finish();
    }
    catch (android.content.ActivityNotFoundException ex){
        Toast.makeText( ContactUs.this,"There is no email client installed", Toast.LENGTH_SHORT ).show();
    }
}}
