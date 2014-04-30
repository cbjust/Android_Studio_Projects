package com.cb.test.settings;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.rtp.AudioStream;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.cb.test.R;

import java.io.File;
import java.io.IOException;

public class MusicActivity extends Activity
{

    private final static String TAG = "CustomerActivity";

    private Button mPlayBtn, mPauseBtn, mStopBtn, mReplayBtn;

    private SeekBar mSeekBar;

    private boolean isPlaying;

    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_layout);

        initViews();
    }

    private void initViews()
    {
        mSeekBar = (SeekBar) findViewById(R.id.seekbar);

        mPlayBtn = (Button) findViewById(R.id.play);
        mPauseBtn = (Button) findViewById(R.id.pause);
        mStopBtn = (Button) findViewById(R.id.stop);
        mReplayBtn = (Button) findViewById(R.id.replay);

        mPlayBtn.setOnClickListener(listener);
        mPauseBtn.setOnClickListener(listener);
        mStopBtn.setOnClickListener(listener);
        mReplayBtn.setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.play:
                    play();
                    break;
                case R.id.pause:
                    pause();
                    break;
                case R.id.stop:
                    stop();
                    break;
                case R.id.replay:
                    replay();
                    break;
            }
        }
    };

    private void play()
    {
        File file = new File("/sdcard/s.3gp");
        if (!file.exists())
        {
            Toast.makeText(this, "File is not existed!", Toast.LENGTH_LONG).show();
            return;
        }

        mPlayBtn.setEnabled(false);
        mPauseBtn.setEnabled(true);
        mStopBtn.setEnabled(true);
        mReplayBtn.setEnabled(true);

        try
        {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource(file.getAbsolutePath());
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.prepareAsync();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
        {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer)
            {
                mMediaPlayer.start();
                Toast.makeText(MusicActivity.this, "start playing", Toast.LENGTH_SHORT).show();
                // getDuration returns right while calling VideoView.start()
                // firstly, or it will return -1
                asyncSeekBar(mMediaPlayer.getDuration());
            }
        });

        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer)
            {
                mSeekBar.setProgress(0);
                mPlayBtn.setEnabled(true);
                mPauseBtn.setEnabled(false);
                mStopBtn.setEnabled(false);
                mReplayBtn.setEnabled(false);
            }
        });

        mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener()
        {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2)
            {
                mSeekBar.setProgress(0);
                replay();
                return false;
            }
        });
    }

    public void asyncSeekBar(final int duration)
    {
        new Thread()
        {
            @Override
            public void run()
            {
                isPlaying = true;

                while (isPlaying)
                {
                    if (mMediaPlayer != null && mMediaPlayer.isPlaying())
                    {
                        mSeekBar.setProgress(mMediaPlayer.getCurrentPosition() * 100 / duration);
                    }
                    try
                    {
                        sleep(500);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    private void pause()
    {
        if (mPauseBtn.getText().equals("pause"))
        {
            mMediaPlayer.pause();
            mPauseBtn.setText(getResources().getString(R.string._continue));
            Toast.makeText(this, "paused", Toast.LENGTH_SHORT).show();
        }
        else
        {
            mMediaPlayer.start();
            mPauseBtn.setText(getResources().getString(R.string.pause));
            Toast.makeText(this, "continue", Toast.LENGTH_SHORT).show();
        }
    }

    private void stop()
    {
        if (mMediaPlayer != null)
        {
            if (mMediaPlayer.isPlaying())
            {
                mMediaPlayer.seekTo(0); // doesn't work, any method that can
                                        // make
                // VideoView return to beginning?
                mMediaPlayer.stop();

                mMediaPlayer.release();
                mMediaPlayer = null;

                mPlayBtn.setEnabled(true);
                mPauseBtn.setEnabled(false);
                mPauseBtn.setText(getResources().getString(R.string.pause));
                mStopBtn.setEnabled(false);
                mReplayBtn.setEnabled(false);

                isPlaying = false;

                Toast.makeText(this, "stopped", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Video has been paused", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void replay()
    {
        if (mPauseBtn.getText().equals("continue"))
        {
            mPauseBtn.setText(getResources().getString(R.string.pause));
        }

        Toast.makeText(this, "replay", Toast.LENGTH_SHORT).show();
        play();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        // need to release the MediaPlayer when quit
        if (mMediaPlayer != null)
        {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
        Toast.makeText(this, "quit the playUI", Toast.LENGTH_SHORT).show();
    }
}
