package com.example.dcaballero.socialapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WelcomeActivity extends AppCompatActivity {

    TextView tvUsername;
    ImageView ivUserAvatar;
    ImageView preview;

    static final int RC_IMAGE_PICK = 9000;
    static final int RC_VIDEO_PICK = 9001;

    Uri mediaUri;
    String mediaType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        tvUsername = findViewById(R.id.username);
        ivUserAvatar = findViewById(R.id.useravatar);
        preview = findViewById(R.id.preview);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            tvUsername.setText(firebaseUser.getDisplayName());
            Glide.with(this)
                    .load(firebaseUser.getPhotoUrl().toString())
                    .into(ivUserAvatar);


            findViewById(R.id.sign_out).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            findViewById(R.id.image).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    File photoFile = null;
                    try {
                        photoFile = File.createTempFile("IMG", ".jpg", getCacheDir());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//
//
//                    Intent pickIntent = new Intent();
//                    pickIntent.setType("image/*");
//                    pickIntent.setAction(Intent.ACTION_GET_CONTENT);
//                    Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//                    takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
//
//                    String pickTitle = "Select or take a new Picture"; // Or get from strings.xml
//                    Intent chooserIntent = Intent.createChooser(pickIntent, pickTitle);
//                    chooserIntent.putExtra
//                            (Intent.EXTRA_INITIAL_INTENTS, new Intent[]{takePhotoIntent});


                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    takePicture.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(WelcomeActivity.this, "fileprovider", photoFile));
                    startActivityForResult(takePicture, RC_IMAGE_PICK);

//                    startActivityForResult(intent, RC_IMAGE_PICK);
                }
            });

            findViewById(R.id.video).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, RC_VIDEO_PICK);
                }
            });


        }
    }
}
