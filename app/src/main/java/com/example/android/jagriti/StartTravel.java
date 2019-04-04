package com.example.android.jagriti;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartTravel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_start_travel );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
    }

    public void Transport(View v){
        Intent i=new Intent( this ,ShareTransport.class);
        startActivity( i );
    }

    public void sharelocation(View v){
        Intent i=new Intent( this ,Sharelocation.class);
        startActivity( i );
    }
}
