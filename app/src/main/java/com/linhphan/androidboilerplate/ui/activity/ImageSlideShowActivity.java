package com.linhphan.androidboilerplate.ui.activity;

import android.os.Bundle;
import android.os.Handler;
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
    private Animation mFadeIn;
    private Animation mFadeOut;

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
        mFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        mFadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
    }

    @Override
    protected void getWidgets(Bundle savedInstanceState) {
        mImgSlider1 = (ImageView) findViewById(R.id.img_slider1);
        mImgSlider2 = (ImageView) findViewById(R.id.img_slider2);
    }

    @Override
    protected void registerEventHandler() {
    }
}
