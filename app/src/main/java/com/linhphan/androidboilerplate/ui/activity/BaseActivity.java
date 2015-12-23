package com.linhphan.androidboilerplate.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.linhphan.androidboilerplate.R;
import com.linhphan.androidboilerplate.util.Logger;

/**
 * Created by linhphan on 11/13/15.
 */
public abstract class BaseActivity extends AppCompatActivity implements Handler.Callback{

    public final static int REPLACING_FRAGMENT = 11;

    protected Handler mBaseHandler = new Handler(this);
    protected int mContainerResource;//the id if the fragment container

    //==================== setters and getters =====================================================
    public Handler getBaseHandler() {
        return mBaseHandler;
    }

    //==================== overridden methods ======================================================


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getActivityLayoutResource());

        init();
        getWidgets();
        registerEventHandler();
    }

    @Deprecated
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.no_sliding, R.anim.animation_sliding_down);
    }

    //==================== implemented methods =====================================================


    //===================== abstract methods =======================================================

    /**
     * @return the layout resource id
     */
    protected abstract int getActivityLayoutResource();

    /**
     * initialize default value for valuables here
     */
    protected abstract void init();

    /**
     * views should retrieve here
     */
    protected abstract void getWidgets();

    /**
     * register event listeners fro views
     */
    protected abstract void registerEventHandler();

    //===================== others =================================================================s

    //handle message from handler
    @Override
    public boolean handleMessage(Message msg) {

        switch (msg.what){
            case REPLACING_FRAGMENT:
                replaceFragment(mContainerResource, (Fragment) msg.obj);
                break;
        }

        return false;
    }

    private void replaceFragment(int container, Fragment fragment){
        if (container == 0 || fragment == null){
            Logger.e(getClass().getName(), "container was null or fragment was null");
            return;
        }
        FragmentTransaction fragmentTransaction = getFragmentTransaction();
        fragmentTransaction.replace(container, fragment);
        fragmentTransaction.commit();
    }

    /**
     * pop the latest fragment in manager's fragment back stack.
     */
    private void popFragment(){
        FragmentManager manager = getSupportFragmentManager();
        manager.popBackStack();
    }

    /**
     * pop the fragment by id
     * @param id which was returned from commit call
     */
    private void popFragment(int id){
        FragmentManager manager = getSupportFragmentManager();
        manager.popBackStack(id, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    /**
     * get an instant of FragmentTransaction which was setup an custom sliding animation
     * @return FragmentTransaction object
     */
    protected FragmentTransaction getFragmentTransaction(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.animation_sliding_in_right_left, R.anim.no_sliding);
        return fragmentTransaction;
    }

    /**
     *  get an instant of FragmentTransaction
     * @param enter the animation resource for entered screen
     * @param exit the animation resource for exited screen
     * @param popEnter the animation resource for popped enter screen
     * @param popExit the animation resource fro popped exit screen
     * @return FragmentTransaction object
     */
    protected FragmentTransaction getFragmentTransaction(int enter, int exit, int popEnter, int popExit){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(enter, exit, popEnter, popExit);
        return fragmentTransaction;
    }
}
