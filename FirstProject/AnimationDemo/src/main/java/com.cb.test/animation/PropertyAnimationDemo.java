package com.cb.test.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
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
                    setAnimatorFromXml(view, R.animator.scalex);
                    break;
                case R.id.scale:
                    setAnimatorFromXml(view, R.animator.scale);
                    break;
                case R.id.translate:
                    setAnimatorFromXml(view, R.animator.translatex);
                    break;
                case R.id.rotate:
                    setAnimatorFromXml(view, R.animator.rotation);
                    break;
                case R.id.alpha:
                    setAnimatorFromXml(view, R.animator.alpha);
                    break;

                // test to add animator in java code from three method
                case R.id.test_btn:
                    setAnimatorFromJavaCode1(view);
                    // setAnimatorFromJavaCode2(view);
                    // setAnimatorFromJavaCode3(view);
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
    public void setAnimatorFromXml(View view, int resId)
    {
        Animator animator = AnimatorInflater.loadAnimator(PropertyAnimationDemo.this, resId);
        if (animator != null)
        {
            animator.setTarget(view);
            animator.start();
        }
    }

    /**
     * first method: add animator in java code using AnimatorSet
     * 
     * @param view the view user clicked
     */
    public void setAnimatorFromJavaCode1(View view)
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
    }

    /**
     * first method: add animator in java code using PropertyValuesHolder
     * 
     * @param view the view user clicked
     */
    public void setAnimatorFromJavaCode2(View view)
    {
        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 2.0f, 1.0f);
        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 2.0f, 1.0f);

        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, pvhScaleX, pvhScaleY);
        animator.setDuration(2000);
        animator.start();
    }

    /**
     * first method: add animator in java code using ViewPropertyAnimator
     * 
     * @param view the view user clicked
     */
    public void setAnimatorFromJavaCode3(View view)
    {
        final View finalView = view;
        // view.animate() returns ViewPropertyAnimator object
        finalView.animate().scaleX(2.0f).scaleY(2.0f).setDuration(1000).setListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation)
            {
                finalView.animate().scaleX(1.0f).scaleY(1.0f).setDuration(1000).start();
            }
        }).start();
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
