package com.linhphan.androidboilerplate.ui.fragment.testBackStack;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.linhphan.androidboilerplate.R;
import com.linhphan.androidboilerplate.ui.activity.BaseActivity;
import com.linhphan.androidboilerplate.ui.fragment.BaseFragment;

/**
 * Created by linhphan on 5/30/16.
 */
public class Fragment_A2 extends BaseFragment implements View.OnClickListener {
    private Button btnB;

    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_a2;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void getWidgets(View root, Bundle savedInstanceState) {
        btnB = (Button) root.findViewById(R.id.btn_goto_b);
    }

    @Override
    protected void registerEventHandler() {
        btnB.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_goto_b:
                BaseActivity activity= getOwnerActivity();
                activity.clearBackStack();
                activity.replaceFragment(R.id.fl_content, Fragment_B.class, false, null, null);
                break;
        }

    }
}
