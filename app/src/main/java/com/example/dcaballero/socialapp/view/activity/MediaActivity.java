package com.example.dcaballero.socialapp.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.dcaballero.socialapp.GlideApp;
import com.example.dcaballero.socialapp.R;

public class MediaActivity extends AppCompatActivity {

    ImageView mImageView;
    VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        mImageView = findViewById(R.id.imageView);
        mVideoView = findViewById(R.id.videoView);

        Intent intent = getIntent();
        String mediaUrl = intent.getStringExtra("mediaUrl");
        String mediaType = intent.getStringExtra("mediaType");

        if ("video".equals(mediaType) || "audio".equals(mediaType)) {
            MediaController mc = new MediaController(this);
            mc.setAnchorView(mVideoView);
            mVideoView.setMediaController(mc);
            mVideoView.setVideoPath(mediaUrl);
            mVideoView.start();
        } else if ("image".equals(mediaType)) {
            GlideApp.with(this).load(mediaUrl).into(mImageView);
        }
    }
}
