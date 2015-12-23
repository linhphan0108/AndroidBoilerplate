package com.linhphan.androidboilerplate.ui.activity;

import android.os.Message;
import android.os.Bundle;

import com.linhphan.androidboilerplate.R;
import com.linhphan.androidboilerplate.ui.fragment.BaseFragment;
import com.linhphan.androidboilerplate.ui.fragment.DumpFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContainerResource = R.id.fr_container;

        Message message = mBaseHandler.obtainMessage();
        message.what = BaseActivity.REPLACING_FRAGMENT;
        Bundle bundle = new Bundle();
        bundle.putInt(BaseFragment.ARGUMENT_KEY, 1);
        message.obj = BaseFragment.newInstance(DumpFragment.class, bundle);
        mBaseHandler.sendMessage(message);
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

    }

    @Override
    protected void registerEventHandler() {

    }
}
