package com.example.android.jagriti;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ShareTransport extends AppCompatActivity {

    EditText vehicle;
    EditText number;
    EditText phone1;
    EditText phone2;
    EditText phone3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_share_transport );

        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        vehicle=(EditText)findViewById( R.id.vehicle );
        number=(EditText)findViewById( R.id.vehiclenumber );


    }

    public void share(View v){
        phone1=(EditText)findViewById( R.id.phone1 );
        String DisplayPhone1=phone1.getText().toString();

        phone2=(EditText)findViewById( R.id.phone2 );
        String DisplayPhone2=phone2.getText().toString();

        phone3=(EditText)findViewById( R.id.phone3 );
        String DisplayPhone3=phone2.getText().toString();
        String shareBody="Vehicle :"+ vehicle +"\n Vehicle number: "+number;

      Intent i=new Intent( Intent.ACTION_SENDTO, Uri.parse("smsto:"+DisplayPhone1+","+DisplayPhone2+","+DisplayPhone3) );
      i.putExtra( "sms_body",shareBody );
      startActivity( i );

    }
}
