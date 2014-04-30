package com.cb.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cb.test.settings.CustomerActivity;
import com.cb.test.settings.SimpleActivity;
import com.cb.test.settings.MusicActivity;

public class MainActivity extends Activity
{

    private final static String TAG = "MainActivity";

    private Button mSimpleMediaBtn, mCustomerMediaBtn, mMusicBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews()
    {
        mSimpleMediaBtn = (Button) findViewById(R.id.simple_btn);
        mCustomerMediaBtn = (Button) findViewById(R.id.customer_btn);
        mMusicBtn = (Button) findViewById(R.id.music_btn);

        mSimpleMediaBtn.setOnClickListener(listener);
        mCustomerMediaBtn.setOnClickListener(listener);
        mMusicBtn.setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.simple_btn:
                    startActivity(new Intent(MainActivity.this, SimpleActivity.class));
                    break;
                case R.id.customer_btn:
                    startActivity(new Intent(MainActivity.this, CustomerActivity.class));
                    break;
                case R.id.music_btn:
                    startActivity(new Intent(MainActivity.this, MusicActivity.class));
                    break;
            }
        }
    };
}
