package com.example.android.jagriti;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {

    private static final int CHOOSE_IMAGE =101 ;
    ImageView imageView;
    Uri uriProfileImage;
    ProgressBar progressBar;
    private StorageReference mStorageRef;
    String profileImageUrl;
    FirebaseAuth firebaseAuth;
    EditText name;
    EditText address;
    EditText phone;
    EditText phone1;
    EditText phone2;
    EditText phone3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_profile );

        firebaseAuth=FirebaseAuth.getInstance();

        name=(EditText)findViewById( R.id.name );

        address=(EditText)findViewById( R.id.address );

        phone=(EditText)findViewById( R.id.phone );

        phone1=(EditText)findViewById( R.id.phone1 );

        phone2=(EditText)findViewById( R.id.phone2 );

        phone3=(EditText)findViewById( R.id.phone3 );

        progressBar=(ProgressBar)findViewById( R.id.progressBar );

      getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        imageView=(ImageView)findViewById( R.id.imageView );
        
        imageView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImageChooser();
            }
        } );
        
        
        findViewById( R.id.save ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveUserInformation();

            }
        } );
    }



    private void saveUserInformation() {

        String displayName=name.getText().toString();
        String displayAddress=address.getText().toString();
        String displayPhone=phone.getText().toString();
        String displayPhone1=phone1.getText().toString();
        String displayPhone2=phone2.getText().toString();
        String displayPhone3=phone3.getText().toString();


        if(displayName.isEmpty() ){
            name.setError( "Name Required" );
            name.requestFocus();
            return;
        }
        if(displayAddress.isEmpty() ){
            address.setError( "Required" );
            address.requestFocus();
            return;}
            if(displayPhone.isEmpty() ) {
                phone.setError( " Required" );
                phone.requestFocus();
                return;
            }
            if(displayPhone1.isEmpty() ) {
                phone1.setError( "Required" );
                phone1.requestFocus();
                return;
            }




        FirebaseUser user=firebaseAuth.getCurrentUser();

        if(user!=null){
      /*   UserProfileChangeRequest profileChangeRequest= new UserProfileChangeRequest.Builder()
                  .setDisplayName(displayName).
                  setPhotoUri(Uri.parse(profileImageUrl))
                  .build();


            user.updateProfile( profileChangeRequest )
            .addOnCompleteListener( new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText( ProfileActivity.this,"Profile Updated",Toast.LENGTH_SHORT ).show();
                    }
                }
            } );*/



            //database
            userInfo user1=new userInfo( displayName,displayAddress,displayPhone,displayPhone1,displayPhone2,displayPhone3 );

            FirebaseDatabase.getInstance().getReference().child("users")
                    .child( user.getUid() )
                    .setValue( user1 )
            .addOnCompleteListener( new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText( ProfileActivity.this,"Profile Updated",Toast.LENGTH_SHORT ).show();
                    }

                    else{
                        Toast.makeText( ProfileActivity.this, "Error Occured", Toast.LENGTH_SHORT ).show();                   }
                }
            } );

        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult( requestCode,resultCode,data );



        if(requestCode==CHOOSE_IMAGE && resultCode==RESULT_OK  && data!=null && data.getData()!=null){
            uriProfileImage= data.getData();

            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap( getContentResolver() ,uriProfileImage);
                imageView.setImageBitmap( bitmap );

                uploadImageToFirebaseSTorage();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImageToFirebaseSTorage() {

         mStorageRef=FirebaseStorage.getInstance().getReference("profilepics/"+System.currentTimeMillis()+".jpg");

        if(uriProfileImage!=null){
            progressBar.setVisibility( View.VISIBLE );

           mStorageRef.putFile( uriProfileImage )
                    .addOnSuccessListener( new OnSuccessListener<UploadTask.TaskSnapshot>() {

                        @Override

                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    progressBar.setVisibility( View.GONE );

                   profileImageUrl=mStorageRef.getDownloadUrl().toString();

                }
            } )

            .addOnFailureListener( new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressBar.setVisibility( View.GONE );
                    Toast.makeText( ProfileActivity.this,e.getMessage(),Toast.LENGTH_SHORT ).show();
                }
            } );
        }

    }


    private void showImageChooser(){

        Intent intent=new Intent(  );
        
        intent.setType( "image/*" );
        intent.setAction( Intent.ACTION_GET_CONTENT );
        startActivityForResult( Intent.createChooser( intent,"Select Profile Image" ),CHOOSE_IMAGE );
    }
}
