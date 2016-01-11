package com.linhphan.androidboilerplate.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.linhphan.androidboilerplate.R;
import com.linhphan.androidboilerplate.ui.activity.BaseActivity;

/**
 * Created by linhphan on 11/13/15.
 */
public class DumpFragment extends BaseFragment implements View.OnClickListener {

    public static final String ARGUMENT_KEY =  "ARGUMENT_KEY";

    private TextView mTxtContent;
    private ImageView mImg;
    private Button mBtnRotation;
    private Button mBtnMove;
    private Button mBtnZoom;
    private Button mBtnFadeIn;


    //================ overridden methods ==========================================================

    @Override
    public void onStart() {
        super.onStart();
    }


    //================ implemented methods =========================================================
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_rotation:
                animateRotation();
                break;

            case R.id.btn_move:
                animateMovement();
                break;

            case R.id.btn_zoom:
                animateZoom();
                break;

            case R.id.btn_fade_in:
                animateFadeIn();
                break;

            default:
                break;
        }
    }

    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_blank;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void getWidgets(View root) {
        mTxtContent = (TextView) root.findViewById(R.id.txt_content);
        mImg = (ImageView) root.findViewById(R.id.img);
        mBtnRotation = (Button) root.findViewById(R.id.btn_rotation);
        mBtnMove = (Button) root.findViewById(R.id.btn_move);
        mBtnZoom = (Button) root.findViewById(R.id.btn_zoom);
        mBtnFadeIn = (Button) root.findViewById(R.id.btn_fade_in);


        Bundle bundle = getArguments();
        if (bundle != null){
            int number = bundle.getInt(ARGUMENT_KEY, 0);
            String content = "the content of fragment " + number;
            mTxtContent.setText(content);
        }
    }

    @Override
    protected void registerEventHandler() {
        mBtnRotation.setOnClickListener(this);
        mBtnMove.setOnClickListener(this);
        mBtnZoom.setOnClickListener(this);
        mBtnFadeIn.setOnClickListener(this);
    }

    //================ other methods ================================================================

    private void animateRotation(){
        Animation rotation = AnimationUtils.loadAnimation(getContext(), R.anim.rotation);
        mImg.startAnimation(rotation);
    }

    private void animateMovement(){
        Animation rotation = AnimationUtils.loadAnimation(getContext(), R.anim.move);
        mImg.startAnimation(rotation);
    }

    private void animateZoom(){
        Animation rotation = AnimationUtils.loadAnimation(getContext(), R.anim.zoom);
        mImg.startAnimation(rotation);
    }

    private void animateFadeIn(){
        Animation rotation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        mImg.startAnimation(rotation);
    }
}
