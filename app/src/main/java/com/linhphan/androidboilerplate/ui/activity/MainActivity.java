package com.linhphan.androidboilerplate.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.linhphan.androidboilerplate.R;
import com.linhphan.androidboilerplate.api.JsonDownloadWorker;
import com.linhphan.androidboilerplate.api.Method;
import com.linhphan.androidboilerplate.api.Parser.IParser;
import com.linhphan.androidboilerplate.callback.DownloadCallback;
import com.linhphan.androidboilerplate.ui.fragment.BaseFragment;
import com.linhphan.androidboilerplate.ui.fragment.DumpFragment;
import com.linhphan.androidboilerplate.util.Logger;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = new Bundle();
        bundle.putInt(BaseFragment.ARGUMENT_KEY, 1);
        BaseFragment fragment = BaseFragment.newInstance(DumpFragment.class, bundle);
        FragmentTransaction transaction = getFragmentTransaction(R.anim.sliding_enter_right_left, R.anim.no_sliding, 0, 0);
        replaceFragment(R.id.fr_container, fragment, false, transaction);

        //// Server
        JsonDownloadWorker worker = new JsonDownloadWorker(this, true, new DownloadCallback() {
            @Override
            public void onDownloadSuccessfully(Object data, int requestCode) {
                switch (requestCode){
                    case 999 :
                        break;

                    case 1000:

                        break;
                }
            }

            @Override
            public void onDownloadFailed(Exception e, int requestCode) {

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

    class JsonParser implements IParser{

        @Override
        public Object parse(Object data) {
            if (data instanceof String){
                Logger.i(getClass().getName(), String.valueOf(data));
            }
            return null;
        }
    }
}
