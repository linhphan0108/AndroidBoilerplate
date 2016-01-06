package com.linhphan.androidboilerplate.ui.activity;

import android.os.Message;
import android.os.Bundle;

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

        mContainerResource = R.id.fr_container;

        Message message = mBaseHandler.obtainMessage();
        message.what = BaseActivity.REPLACING_FRAGMENT;
        Bundle bundle = new Bundle();
        bundle.putInt(BaseFragment.ARGUMENT_KEY, 1);
        message.obj = BaseFragment.newInstance(DumpFragment.class, bundle);
        mBaseHandler.sendMessage(message);




    }



    //// Server

    JsonDownloadWorker worker = new JsonDownloadWorker(this, true, new DownloadCallback() {
        @Override
        public void onDownloadSuccessfully(Object data, int requestCode) {
            switch (requestCode){
                case 999 :
                    worker.cancel(true);
                    request_02();
                    break;

                case 1000:

                    break;
            }
        }

        @Override
        public void onDownloadFailed(Exception e, int requestCode) {

        }
    });



    void request_01(){
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


    void request_02(){
        String url = "http://dev.bravesoft.vn/FoodCoach/API/user_login2.json";
        Map<String, String> params = new HashMap<>();
        params.put("device_token", "abc");
        params.put("username", "user_1");
        params.put("password", "123456");

        worker.setType(Method.POST)
                .setRequestCode(1999)
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
