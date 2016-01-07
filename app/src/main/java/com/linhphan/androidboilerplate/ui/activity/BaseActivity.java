package com.linhphan.androidboilerplate.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.linhphan.androidboilerplate.R;
import com.linhphan.androidboilerplate.ui.fragment.BaseFragment;
import com.linhphan.androidboilerplate.util.Logger;

/**
 * Created by linhphan on 11/13/15.
 */
public abstract class BaseActivity extends AppCompatActivity{


//    protected Handler mBaseHandler = new Handler(this);
//    protected int mContainerResource;//the id if the fragment container

    //==================== setters and getters =====================================================


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
        overridePendingTransition(R.anim.no_sliding, R.anim.sliding_exit_down);
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

    //===================== fragment management ====================================================

    public void replaceFragment(int containerLayoutId, BaseFragment fragment, boolean isAddBackStack, FragmentTransaction transaction){
        if (containerLayoutId == 0 || fragment == null){
            Logger.e(getClass().getName(), "container was null or fragment was null");
            return;
        }
        transaction.replace(containerLayoutId, fragment, fragment.getClassTagName());
        if (isAddBackStack) {
            transaction.addToBackStack(fragment.getClassTagName());
        }
        transaction.commit();
    }

    /**
     * pop the latest fragment in manager's fragment back stack.
     */
    public void popFragment(){
        FragmentManager manager = getSupportFragmentManager();
        manager.popBackStack();
    }

    /**
     * pop the fragment by id
     * @param id which was returned from commit call
     */
    public void popFragment(int id){
        FragmentManager manager = getSupportFragmentManager();
        manager.popBackStack(id, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    /**
     *  get an instant of FragmentTransaction
     * @param enter the animation resource for entered screen
     * @param exit the animation resource for exited screen
     * @param popEnter the animation resource for popped enter screen
     * @param popExit the animation resource fro popped exit screen
     * @return FragmentTransaction object
     */
    public FragmentTransaction getFragmentTransaction(int enter, int exit, int popEnter, int popExit){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(enter, exit, popEnter, popExit);
        return fragmentTransaction;
    }
}
