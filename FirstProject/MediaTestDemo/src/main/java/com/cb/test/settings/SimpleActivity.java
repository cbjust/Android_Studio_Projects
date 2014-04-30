package com.cb.test.settings;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.cb.test.R;

public class SimpleActivity extends Activity
{

    private final static String TAG = "SimpleActivity";

    private VideoView mVideoView;

    private MediaController mMediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_layout);

        initViews();

        play();
    }

    private void initViews()
    {
        mVideoView = (VideoView) findViewById(R.id.video_view);

        mMediaController = new MediaController(this);
        mMediaController.setMediaPlayer(mVideoView);

        mVideoView.setMediaController(mMediaController);
    }

    private void play()
    {
        File file = new File("/sdcard/s.3gp");
        if (!file.exists())
        {
            Toast.makeText(this, "File is not existed!", Toast.LENGTH_LONG).show();
            return;
        }

        mVideoView.setVideoPath(file.getAbsolutePath());
        mVideoView.start();
    }

}
