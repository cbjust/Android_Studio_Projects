package com.cb.test.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

/**
 * @author Justin Chen
 * @date 2014.4.29
 */

public class ViewAnimationDemo extends Activity
{

    private Button mScaleBtn, mTranslateBtn, mRotateBtn, mAlphaBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);

        initViews();
    }

    /**
     * init the views
     */
    private void initViews()
    {
        mScaleBtn = (Button) findViewById(R.id.scale);
        mTranslateBtn = (Button) findViewById(R.id.translate);
        mRotateBtn = (Button) findViewById(R.id.rotate);
        mAlphaBtn = (Button) findViewById(R.id.alpha);

        mScaleBtn.setOnClickListener(onClickListener);
        mTranslateBtn.setOnClickListener(onClickListener);
        mRotateBtn.setOnClickListener(onClickListener);
        mAlphaBtn.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.scale:
                    setAnimation(view, R.anim.scale);
                    break;
                case R.id.translate:
                    setAnimation(view, R.anim.translate);
                    break;
                case R.id.rotate:
                    setAnimation(view, R.anim.rotate);
                    break;
                case R.id.alpha:
                    setAnimation(view, R.anim.alpha);
                    break;
            }
        }
    };

    /**
     * start animation according to the animation xml
     * 
     * @param view the view user clicked
     * @param resId the animation defined in xml
     */
    public void setAnimation(View view, int resId)
    {
        Animation animation = AnimationUtils.loadAnimation(ViewAnimationDemo.this, resId);
        if (animation != null)
        {
            animation.setStartOffset(0);
            view.setAnimation(animation);
        }
    }

}
