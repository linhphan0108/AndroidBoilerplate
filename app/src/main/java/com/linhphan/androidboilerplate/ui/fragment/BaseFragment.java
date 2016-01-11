package com.linhphan.androidboilerplate.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linhphan.androidboilerplate.ui.activity.BaseActivity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by linhphan on 11/13/15.
 */
public abstract class BaseFragment extends Fragment {

    public static final String ARGUMENT_KEY =  "ARGUMENT_KEY";

    public static BaseFragment newInstance(Class<?> c, Bundle bundle){
        BaseFragment baseFragment = null;
        try {
            Constructor<?> constructor = c.getConstructors()[0];
            baseFragment = (BaseFragment) constructor.newInstance();
            if (bundle != null){
                baseFragment.setArguments(bundle);
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return baseFragment;
    }

    //================= overridden methods =========================================================
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getFragmentLayoutResource(), container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        getWidgets(getView());
        registerEventHandler();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    //================== abstract methods ==========================================================
    /**
     * @return the layout resource which will be inflated to layout
     */
    protected abstract int getFragmentLayoutResource();
    /**
     * initialize default value for valuables here
     * this method will be called in onCreate method of Fragment.
     */
    protected abstract void init();

    /**
     * views should retrieve here
     */
    protected abstract void getWidgets(View root);

    /**
     * register event listeners fro views
     */
    protected abstract void registerEventHandler();

    public String getClassTagName(){
        return this.getClass().getName();
    }

    @SuppressWarnings("unchecked")
    protected  <T extends BaseActivity>T getOwnerActivity(){
        return (T) getActivity();
    }

    protected BaseActivity getBaseActivity(){
        return (BaseActivity) getActivity();
    }

    //================== others ====================================================================
    public static boolean isFragmentVisisble(int id, FragmentManager manager){
        Fragment f = manager.findFragmentById(id);
        return f != null && f.isVisible();
    }

    public static boolean isFragmentVisisble(String tag, FragmentManager manager){
        Fragment f = manager.findFragmentByTag(tag);
        return f != null && f.isVisible();
    }

    public static boolean isFragmentInStack(int id, FragmentManager manager){
        Fragment f = manager.findFragmentById(id);
        return f != null;
    }

    public static boolean isFragmentInStack(String tag, FragmentManager manager){
        Fragment f = manager.findFragmentByTag(tag);
        return f != null;
    }
}
