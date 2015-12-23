package com.linhphan.androidboilerplate.ui.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.linhphan.androidboilerplate.R;
import com.linhphan.androidboilerplate.ui.activity.BaseActivity;

/**
 * Created by linhphan on 11/13/15.
 */
public class DumpFragment extends BaseFragment implements View.OnClickListener {

    TextView mTxtContent;
    Button mBtnNewFragment;


    //================ overridden methods ==========================================================

    @Override
    public void onStart() {
        super.onStart();
    }


    //================ implemented methods =========================================================
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_new_Fragment:
                try {
                    Message message = mBaseHandler.obtainMessage();
                    message.what = BaseActivity.REPLACING_FRAGMENT;
                    Bundle bundle = getArguments();
                    if (bundle == null){
                        bundle = new Bundle();
                        bundle.putInt(BaseFragment.ARGUMENT_KEY, 1);
                    }else{
                        int number = bundle.getInt(BaseFragment.ARGUMENT_KEY, 0);
                        bundle.putInt(BaseFragment.ARGUMENT_KEY, number + 1);
                    }
                    message.obj = BaseFragment.newInstance(DumpFragment.class, bundle);
                    mBaseHandler.sendMessage(message);
                }catch (ClassCastException e){
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_blank;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void getWidgets(View root) {
        mTxtContent = (TextView) root.findViewById(R.id.txt_content);
        mBtnNewFragment = (Button) root.findViewById(R.id.btn_new_Fragment);
        mBtnNewFragment.setOnClickListener(this);


        Bundle bundle = getArguments();
        if (bundle != null){
            int number = bundle.getInt(ARGUMENT_KEY, 0);
            String content = "the content of fragment " + number;
            mTxtContent.setText(content);
        }
    }

    @Override
    protected void registerEventHandler() {

    }

    //================ others ======================================================================
}
