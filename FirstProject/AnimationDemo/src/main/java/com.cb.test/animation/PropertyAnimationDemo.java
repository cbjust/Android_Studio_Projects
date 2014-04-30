package com.cb.test.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * @author Justin Chen
 * @date 2014.4.30
 */

public class PropertyAnimationDemo extends Activity
{

    private Button mScaleXBtn, mScaleBtn, mTranslateBtn, mRotateBtn, mAlphaBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);

        initViews();
    }

    /**
     * init the views
     */
    private void initViews()
    {

        mScaleXBtn = (Button) findViewById(R.id.scaleX);
        mScaleBtn = (Button) findViewById(R.id.scale);
        mTranslateBtn = (Button) findViewById(R.id.translate);
        mRotateBtn = (Button) findViewById(R.id.rotate);
        mAlphaBtn = (Button) findViewById(R.id.alpha);

        mScaleXBtn.setOnClickListener(onClickListener);
        mScaleBtn.setOnClickListener(onClickListener);
        mTranslateBtn.setOnClickListener(onClickListener);
        mRotateBtn.setOnClickListener(onClickListener);
        mAlphaBtn.setOnClickListener(onClickListener);

        // test button
        Button mTestBtn = (Button) findViewById(R.id.test_btn);
        mTestBtn.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {
            // test to add animator in xml
                case R.id.scaleX:
                    setAnimator(view, R.animator.scalex);
                    break;
                case R.id.scale:
                    setAnimator(view, R.animator.scale);
                    break;
                case R.id.translate:
                    setAnimator(view, R.animator.translatex);
                    break;
                case R.id.rotate:
                    setAnimator(view, R.animator.rotation);
                    break;
                case R.id.alpha:
                    setAnimator(view, R.animator.alpha);
                    break;

                // test to add animator in java code
                case R.id.test_btn:
                    dynamicAnimator(view);
                    break;
            }
        }
    };

    /**
     * set animator from xml files
     * 
     * @param view the view user clicked
     * @param resId the animator used
     */
    public void setAnimator(View view, int resId)
    {
        Animator animator = AnimatorInflater.loadAnimator(PropertyAnimationDemo.this, resId);
        if (animator != null)
        {
            animator.setTarget(view);
            animator.start();
        }
    }

    /**
     * test to add animator in java code
     * 
     * @param view the view user clicked
     */
    public void dynamicAnimator(View view)
    {
        ObjectAnimator animator_one = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 2.0f, 1.0f);
        animator_one.setDuration(2000);
        ObjectAnimator animator_two = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 2.0f, 1.0f);
        animator_two.setDuration(2000);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.addListener(animatorListener);
        // animatorSet.playTogether(animator_one);
        animatorSet.playSequentially(animator_one, animator_two);
        animatorSet.start();

        // other method to make animation

        // PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("x", 50f);
        // PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("y", 50f);
        // ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY).start();
    }

    /**
     * a listener to respond the state
     */
    private Animator.AnimatorListener animatorListener = new Animator.AnimatorListener()
    {
        @Override
        public void onAnimationStart(Animator animator)
        {
            Toast.makeText(PropertyAnimationDemo.this, "onAnimationStart", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAnimationEnd(Animator animator)
        {
            Toast.makeText(PropertyAnimationDemo.this, "onAnimationEnd", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAnimationCancel(Animator animator)
        {
            Toast.makeText(PropertyAnimationDemo.this, "onAnimationCancel", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAnimationRepeat(Animator animator)
        {
            Toast.makeText(PropertyAnimationDemo.this, "onAnimationRepeat", Toast.LENGTH_SHORT).show();
        }
    };
}
