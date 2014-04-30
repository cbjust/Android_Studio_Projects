package com.cb.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cb.test.animation.R;
import com.cb.test.animation.AnimationDemo;

public class MainActivity extends Activity
{

    private Button mAnimBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    public void initViews()
    {
        mAnimBtn = (Button) findViewById(R.id.anim_main_btn);
        mAnimBtn.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.anim_main_btn:
                    startActivity(new Intent(MainActivity.this, AnimationDemo.class));
                    break;
            }
        }
    };
}
