package com.themkrworld.collage.ui.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

import com.themkrworld.collage.utils.AppConfig;
import com.themkrworld.collage.utils.Tracer;

/**
 * Created by delhivery on 23/8/16.
 */
public class ClickLinearLayout extends LinearLayout implements View.OnClickListener, Animation.AnimationListener {
    private static final String TAG = AppConfig.BASE_TAG + ".ClickButtonView";
    private OnClickListener mOnClickListener;
    private Animation mAnimationClicked;

    public ClickLinearLayout(Context context) {
        super(context);
        init();
    }

    public ClickLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClickLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ClickLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    /**
     * Method to init the Button Animation
     */
    private void init() {
        Tracer.debug(TAG, "init()");
        mAnimationClicked = new ScaleAnimation(1F, 0.8F, 1F, 0.8F, Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);
        mAnimationClicked.setDuration(100);
        mAnimationClicked.setRepeatCount(1);
        mAnimationClicked.setRepeatMode(Animation.REVERSE);
        mAnimationClicked.setAnimationListener(this);
    }

    @Override
    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
        super.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startAnimation(mAnimationClicked);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Tracer.debug(TAG, "onAnimationEnd()" + animation);
        if (animation.equals(mAnimationClicked)) {
            if (mOnClickListener != null) {
                mOnClickListener.onClick(this);
            }
            clearAnimation();
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
