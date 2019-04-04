package com.example.android.jagriti;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class UserActivity extends AppCompatActivity {


    //for logout
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle SavedInstanceState){

        super.onCreate( SavedInstanceState );
        setContentView( R.layout.user_activity);
}


    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater=getMenuInflater();
        inflater.inflate( R.menu.main_menu,menu );


        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){

            case R.id.logout :


                firebaseAuth= FirebaseAuth.getInstance();



                FirebaseAuth.getInstance().signOut();
                Intent I = new Intent(UserActivity.this, ActivityLogin.class);
                startActivity(I);


                return true;

            default: return super.onOptionsItemSelected( item );
        }
    }


    public void ProfileStart(View view){

        Intent i=new Intent( this,ProfileActivity.class );
        startActivity( i );

    }

    public void StartTravelling(View view){


        Intent i=new Intent( this,StartTravel.class );
        startActivity( i );

    }

    public void contact(View view){
        Intent i=new Intent( this,ContactUs.class );
        startActivity( i );

    }

    public void Defence(View v){
        Intent i=new Intent( this,DefenceTricks.class );
        startActivity( i );
    }
}
