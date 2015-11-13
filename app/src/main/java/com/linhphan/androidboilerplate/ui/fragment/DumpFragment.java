package com.linhphan.androidboilerplate.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = View.inflate(container.getContext(), R.layout.fragment_blank, null);
        mTxtContent = (TextView) view.findViewById(R.id.txt_content);
        mBtnNewFragment = (Button) view.findViewById(R.id.btn_new_Fragment);
        mBtnNewFragment.setOnClickListener(this);


        Bundle bundle = getArguments();
        if (bundle != null){
            int number = bundle.getInt(ARGUMENT_KEY, 0);
            String content = "the content of fragment " + number;
            mTxtContent.setText(content);
        }

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

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
}
