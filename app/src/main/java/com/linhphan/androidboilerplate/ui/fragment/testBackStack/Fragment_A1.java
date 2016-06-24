package com.linhphan.androidboilerplate.ui.fragment.testBackStack;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.linhphan.androidboilerplate.R;
import com.linhphan.androidboilerplate.ui.fragment.BaseFragment;

/**
 * Created by linhphan on 5/30/16.
 */
public class Fragment_A1 extends BaseFragment implements View.OnClickListener {

    private Button btnA2;

    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_a1;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void getWidgets(View root, Bundle savedInstanceState) {
        btnA2 = (Button) root.findViewById(R.id.btn_a2);
    }

    @Override
    protected void registerEventHandler() {
        btnA2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_a2:
                getOwnerActivity().replaceFragment(R.id.fl_content, Fragment_A2.class, true, null, null);
                break;
        }

    }
}
