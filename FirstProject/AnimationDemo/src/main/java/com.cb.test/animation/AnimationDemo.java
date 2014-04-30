package com.cb.test.animation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * @author Justin Chen
 * @date 2014.4.29
 */

public class AnimationDemo extends Activity
{

    private Button mViewAnimBtn, mPropertyAnimBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        initViews();
    }

    /**
     * init the views
     */
    private void initViews()
    {
        mViewAnimBtn = (Button) findViewById(R.id.view_anim_btn);
        mPropertyAnimBtn = (Button) findViewById(R.id.property_anim_btn);

        mViewAnimBtn.setOnClickListener(onClickListener);
        mPropertyAnimBtn.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.view_anim_btn:
                    startActivity(new Intent(AnimationDemo.this, com.cb.test.animation.ViewAnimationDemo.class));
                    break;
                case R.id.property_anim_btn:
                    startActivity(new Intent(AnimationDemo.this, com.cb.test.animation.PropertyAnimationDemo.class));
                    break;
            }
        }
    };
}
