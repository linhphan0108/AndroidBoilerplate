package com.linhphan.androidboilerplate.ui.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.linhphan.androidboilerplate.R;



/**
 * Created by linhphan on 6/21/16.
 */
public class ImageSlideShowActivity extends BaseActivity {

    private ImageView mImgSlider1;
    private ImageView mImgSlider2;
    private Animation mFadeInAnimation;
    private Animation mFadeOutAnimation;
    private Handler mSliderHandler = new Handler();
    private int[] mImageResources;
    private int mImageSliderIndex;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected int getActivityLayoutResource() {
        return R.layout.activity_image_slide_show;
    }

    @Override
    protected void init() {
        mFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        mFadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out);

        mImageResources = new int[]{
                R.drawable.img_slider1,
                R.drawable.img_slider2,
                R.drawable.img_slider3,
                R.drawable.img_slider4
        };
    }

    @Override
    protected void getWidgets(Bundle savedInstanceState) {
        mImgSlider1 = (ImageView) findViewById(R.id.img_slider1);
        mImgSlider2 = (ImageView) findViewById(R.id.img_slider2);

        mImgSlider1.setVisibility(View.VISIBLE);
        mImgSlider2.setVisibility(View.GONE);
    }

    @Override
    protected void registerEventHandler() {
        mSliderHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                slideAutomatically(false);
            }
        }, 3000);

    }

    private void slideAutomatically(boolean isFadeIn){
        final ImageView fadeInView;
        final ImageView fadeOutView;

        //== get the views which will be faded in and faded out
        if (mImgSlider1.getVisibility() == View.VISIBLE){
            fadeInView = mImgSlider2;
            fadeOutView = mImgSlider1;
        }else{
            fadeInView = mImgSlider1;
            fadeOutView = mImgSlider2;
        }

        mFadeInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mSliderHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        slideAutomatically(false);
                    }
                }, 3000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mFadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                fadeOutView.setVisibility(View.GONE);
                mSliderHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        slideAutomatically(true);
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        //== set animation to view
        if (isFadeIn){
            mImageSliderIndex = mImageSliderIndex % mImageResources.length;
            int resource = mImageResources[mImageSliderIndex];
            fadeInView.setImageResource(resource);
            mImageSliderIndex++;

            fadeInView.clearAnimation();
            fadeInView.startAnimation(mFadeInAnimation);
            fadeInView.setVisibility(View.VISIBLE);
        }else{
            fadeOutView.clearAnimation();
            fadeOutView.startAnimation(mFadeOutAnimation);
        }

    }
}
