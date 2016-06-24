package com.linhphan.androidboilerplate.ui.fragment.testBackStack;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.linhphan.androidboilerplate.R;
import com.linhphan.androidboilerplate.ui.fragment.BaseFragment;

/**
 * Created by linhphan on 5/30/16.
 */
public class Fragment_A extends BaseFragment implements View.OnClickListener{

    private Button btnA1;

    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_a;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void getWidgets(View root, Bundle savedInstanceState) {
        btnA1 = (Button) root.findViewById(R.id.btn_a1);
    }

    @Override
    protected void registerEventHandler() {
        btnA1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_a1:
                getOwnerActivity().replaceFragment(R.id.fl_content, Fragment_A1.class, true, null, null);
                break;
        }

    }
}
