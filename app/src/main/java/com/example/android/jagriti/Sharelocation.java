package com.example.android.jagriti;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Sharelocation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sharelocation );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
    }
}
