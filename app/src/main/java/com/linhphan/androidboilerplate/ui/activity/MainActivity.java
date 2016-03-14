package com.linhphan.androidboilerplate.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.linhphan.androidboilerplate.R;
import com.linhphan.androidboilerplate.api.BaseDownloadWorker;
import com.linhphan.androidboilerplate.api.JsonDownloadWorker;
import com.linhphan.androidboilerplate.api.Method;
import com.linhphan.androidboilerplate.api.Parser.IParser;
import com.linhphan.androidboilerplate.ui.fragment.AnimationFragment;
import com.linhphan.androidboilerplate.ui.fragment.DumpFragment;
import com.linhphan.androidboilerplate.util.AppUtil;
import com.linhphan.androidboilerplate.util.Logger;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button mBtnNewFragment;

    private int mAutoIncreaseNumber = 0;

    //=============== overridden methods ===========================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = new Bundle();
        bundle.putInt(DumpFragment.ARGUMENT_KEY, mAutoIncreaseNumber);
        FragmentTransaction transaction = getFragmentTransaction(R.anim.sliding_enter_right_left, R.anim.no_sliding, 0, 0);
        addFragment(R.id.fr_container, DumpFragment.class, false, bundle, transaction);

        //// Server
        JsonDownloadWorker worker = new JsonDownloadWorker(this, true, new BaseDownloadWorker.DownloadCallback() {
            @Override
            public void onSuccessfully(Object data, int requestCode, int responseCode) {
                switch (requestCode){
                    case 999 :
                        break;

                    case 1000:

                        break;
                }
            }

            @Override
            public void onFailed(Exception e, int requestCode, int responseCode) {

            }
        });

        String url = "http://dev.bravesoft.vn/FoodCoach/API/user_login.json";
        Map<String, String> params = new HashMap<>();
        params.put("device_token", "abc");
        params.put("username", "user_1");
        params.put("password", "123456");

        worker.setType(Method.POST)
                .setRequestCode(999)
                .setParser(new JsonParser())
                .setParams(params)
                .execute(url);


        AppUtil appUtil = AppUtil.getInstance();

        Logger.i(getClassTagName(), "uuid " +appUtil.getRandomUuid());
        Logger.i(getClassTagName(), "uuid " +appUtil.getRandomUuid());
        Logger.i(getClassTagName(), "uuid " +appUtil.getRandomUuid());
        Logger.i(getClassTagName(), "uuid " +appUtil.getRandomUuid());
        Logger.i(getClassTagName(), "uuid " +appUtil.getRandomUuid());
        Logger.i(getClassTagName(), "uuid " +appUtil.getRandomUuid());
        Logger.i(getClassTagName(), "uuid " +appUtil.getRandomUuid());
        Logger.i(getClassTagName(), "uuid " +appUtil.getRandomUuid());

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
        mBtnNewFragment = (Button) findViewById(R.id.btn_new_Fragment);
    }

    @Override
    protected void registerEventHandler() {
        mBtnNewFragment.setOnClickListener(this);
    }

    //=============== implemented methods ==========================================================
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_new_Fragment:

                mAutoIncreaseNumber++;
                if (mAutoIncreaseNumber % 2 == 0) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(DumpFragment.ARGUMENT_KEY, mAutoIncreaseNumber);
                    FragmentTransaction transaction = getFragmentTransaction(R.anim.sliding_enter_right_left, R.anim.no_sliding, 0, 0);
                    addFragment(R.id.fr_container, DumpFragment.class, true, bundle, transaction);

                }else if (mAutoIncreaseNumber % 2 == 1){
                    Bundle bundle = new Bundle();
                    bundle.putInt(DumpFragment.ARGUMENT_KEY, mAutoIncreaseNumber);
                    FragmentTransaction transaction = getFragmentTransaction(R.anim.sliding_enter_right_left, R.anim.no_sliding, 0, 0);
                    addFragment(R.id.fr_container, AnimationFragment.class, true, bundle, transaction);
                }
                break;

            default:
                break;
        }
    }

    //=============== inner classes ================================================================
    class JsonParser implements IParser{
        @Override
        public Object parse(Object data, BaseDownloadWorker.ResponseCodeHolder responseCode) {
            if (data instanceof String) {
                Logger.i(getClass().getName(), String.valueOf(data));
            }
            return null;
        }
    }
}
