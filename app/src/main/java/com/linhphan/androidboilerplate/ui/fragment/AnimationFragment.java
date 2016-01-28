package com.linhphan.androidboilerplate.ui.fragment;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.linhphan.androidboilerplate.R;
import com.linhphan.androidboilerplate.util.Logger;


/**
 * Created by linhphan on 1/11/16.
 */
public class AnimationFragment extends BaseFragment implements View.OnClickListener {

    private ImageView mImg;
    private Button mBtnTranslate;
    private Button mBtnNewView;
    private Button mBtnRemoveView;
    private LinearLayout mLl;

    //============= overridden methods =============================================================
    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_animation;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void getWidgets(View root) {
        mImg = (ImageView) root.findViewById(R.id.img);
        mBtnTranslate = (Button) root.findViewById(R.id.btn_translate);
        mBtnNewView = (Button) root.findViewById(R.id.btn_new_view);
        mBtnRemoveView = (Button) root.findViewById(R.id.btn_remove_view);
        mLl = (LinearLayout) root.findViewById(R.id.ll_view_group);
    }

    @Override
    protected void registerEventHandler() {
        mBtnTranslate.setOnClickListener(this);
        mBtnNewView.setOnClickListener(this);
        mBtnRemoveView.setOnClickListener(this);

        LayoutTransition lt = new LayoutTransition();
        lt.enableTransitionType(LayoutTransition.CHANGING);
        mLl.setLayoutTransition(lt);
    }

    //============= implemented methods ============================================================
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_translate:
                translate();
                break;

            case R.id.btn_new_view:
                newView();
                break;

            case R.id.btn_remove_view:
                removeView();
                break;

            default:
                break;
        }
    }

    //============ other methods ===================================================================

    private void translate() {
        mImg.animate().cancel();
        mImg.animate()
                .translationX(200)
                .setDuration(1000)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        Logger.d(getClassTagName(), "translate started");
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Logger.d(getClassTagName(), "translate ended");
                        mImg.animate().cancel();
                        mImg.animate()
                                .translationX(0)
                                .setDuration(1000);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
    }

    private void newView(){
        mLl.addView(new Button(getContext()), 0);
    }

    private void removeView(){
        if (mLl.getChildCount() > 0) {
            mLl.removeViewAt(0);
        }
    }
}
