package com.linhphan.androidboilerplate.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;

import com.linhphan.androidboilerplate.R;
import com.linhphan.androidboilerplate.ui.fragment.testBackStack.Fragment_A;
import com.linhphan.androidboilerplate.ui.fragment.testBackStack.Fragment_B;
import com.linhphan.androidboilerplate.ui.fragment.testBackStack.Fragment_C;
import com.linhphan.androidboilerplate.ui.fragment.testBackStack.Fragment_D;

public class TestBackStack extends BaseActivity implements View.OnClickListener {

    private Button mBtnTabA;
    private Button mBtnTabB;
    private Button mBtnTabC;
    private Button mBtnTabD;

    //============ inherited methods ===============================================================
    @Override
    protected int getActivityLayoutResource() {
        return R.layout.activity_test_back_stack;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void getWidgets(Bundle savedInstanceState) {
        mBtnTabA = (Button) findViewById(R.id.btn_tabA);
        mBtnTabB = (Button) findViewById(R.id.btn_tabB);
        mBtnTabC = (Button) findViewById(R.id.btn_tabC);
        mBtnTabD = (Button) findViewById(R.id.btn_tabD);
    }

    @Override
    protected void registerEventHandler() {
        mBtnTabA.setOnClickListener(this);
        mBtnTabB.setOnClickListener(this);
        mBtnTabC.setOnClickListener(this);
        mBtnTabD.setOnClickListener(this);
    }

    //============ implemented methods =============================================================
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_tabA:
                replaceFragment(R.id.fl_content, Fragment_A.class, false, null, null);
                break;

            case R.id.btn_tabB:
                replaceFragment(R.id.fl_content, Fragment_B.class, false, null, null);
                break;

            case R.id.btn_tabC:
                replaceFragment(R.id.fl_content, Fragment_C.class, false, null, null);
                break;

            case R.id.btn_tabD:
                replaceFragment(R.id.fl_content, Fragment_D.class, false, null, null);
                break;
        }
    }
}
