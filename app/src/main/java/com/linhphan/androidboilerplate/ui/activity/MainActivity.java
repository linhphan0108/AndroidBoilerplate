package com.linhphan.androidboilerplate.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.linhphan.androidboilerplate.R;
import com.linhphan.androidboilerplate.api.BaseDownloadWorker;
import com.linhphan.androidboilerplate.api.JsonDownloadWorker;
import com.linhphan.androidboilerplate.api.Method;
import com.linhphan.androidboilerplate.api.Parser.IParser;
import com.linhphan.androidboilerplate.ui.fragment.AnimationFragment;
import com.linhphan.androidboilerplate.ui.fragment.DumpFragment;
import com.linhphan.androidboilerplate.ui.fragment.TouchToZoomImageFragment;
import com.linhphan.androidboilerplate.util.AppUtil;
import com.linhphan.androidboilerplate.util.Logger;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button mBtnNewFragment;
    private Button mBtnClearBackStack;
    private Button mBtnPop;

    private int mAutoIncreaseNumber = 0;
    private final int mTotalPage = 2;

    //=============== overridden methods ===========================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = new Bundle();
        bundle.putInt(DumpFragment.ARGUMENT_KEY, mAutoIncreaseNumber);
        replaceFragment(R.id.fr_container, TouchToZoomImageFragment.class, false, bundle, null);
    }



    @Override
    protected int getActivityLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void getWidgets() {
        mBtnNewFragment = (Button) findViewById(R.id.btn_new_Fragment);
        mBtnClearBackStack = (Button) findViewById(R.id.btn_clear_back_stack);
        mBtnPop = (Button) findViewById(R.id.btn_pop);
    }

    @Override
    protected void registerEventHandler() {
        mBtnNewFragment.setOnClickListener(this);
        mBtnClearBackStack.setOnClickListener(this);mBtnPop.getParent();
        mBtnPop.setOnClickListener(this);
    }

    //=============== implemented methods ==========================================================
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_new_Fragment:

                mAutoIncreaseNumber++;
                if (mAutoIncreaseNumber % mTotalPage == 0) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(DumpFragment.ARGUMENT_KEY, mAutoIncreaseNumber);
                    FragmentTransaction transaction = getFragmentTransaction(R.anim.sliding_enter_right_left, 0, 0, R.anim.sliding_exit_left_right);
                    replaceFragment(R.id.fr_container, DumpFragment.class, true, bundle, transaction);

                }else if (mAutoIncreaseNumber % mTotalPage == 1){
                    Bundle bundle = new Bundle();
                    bundle.putInt(DumpFragment.ARGUMENT_KEY, mAutoIncreaseNumber);
                    FragmentTransaction transaction = getFragmentTransaction(R.anim.sliding_enter_right_left, 0, 0, R.anim.sliding_exit_left_right);
                    replaceFragment(R.id.fr_container, DumpFragment.class, true, bundle, transaction);
                }else if(mAutoIncreaseNumber % mTotalPage == 2){
                    Bundle bundle = new Bundle();
                    bundle.putInt(DumpFragment.ARGUMENT_KEY, mAutoIncreaseNumber);
                    FragmentTransaction transaction = getFragmentTransaction(R.anim.sliding_enter_right_left, 0, 0, R.anim.sliding_exit_left_right);
                    replaceFragment(R.id.fr_container, TouchToZoomImageFragment.class, true, bundle, transaction);
                }
                break;

            case R.id.btn_clear_back_stack:
                clearBackStacks();
                break;

            case R.id.btn_pop:
                clearBackStack();
                printBackStackSize();
                break;

            default:
                break;
        }
    }

    protected void clearBackStacks(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        int count = manager.getBackStackEntryCount();
        printBackStackSize();
        for (int i = 0; i< count; i++){
            FragmentManager.BackStackEntry bs = manager.getBackStackEntryAt(i);
            String tag = bs.getName();
            Fragment fragment = manager.findFragmentByTag(tag);
            ft.remove(fragment);
        }
        ft.commit();
        printBackStackSize();
    }

    private void printBackStackSize(){
        FragmentManager manager = getSupportFragmentManager();
        int count = manager.getBackStackEntryCount();
        Logger.d("back stack size: ", count +"");
    }
}
