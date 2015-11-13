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
        setContentView(R.layout.activity_main);

        mContainerResource = R.id.fr_container;

        Message message = mBaseHandler.obtainMessage();
        message.what = BaseActivity.REPLACING_FRAGMENT;
        Bundle bundle = new Bundle();
        bundle.putInt(BaseFragment.ARGUMENT_KEY, 1);
        message.obj = BaseFragment.newInstance(DumpFragment.class, bundle);
        mBaseHandler.sendMessage(message);




//        ConfirmDialog dialog = new ConfirmDialog();
//        dialog.registerCallback(new ConfirmDialogCallback() {
//            @Override
//            public void onOk() {
//                Toast.makeText(MainActivity.this, "ok is clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
//        Bundle bundle = new Bundle();
//        bundle.putString(Constant.DIALOG_TITLE, "the title here");
//        bundle.putString(Constant.DIALOG_MESSAGE, "the message here");
//        dialog.setArguments(bundle);
//        dialog.show(getSupportFragmentManager(), ConfirmDialog.class.getSimpleName());



//        Map<String, String> query = new HashMap<>();
//        query.put("name", "linh");
//        query.put("title", "developer");
//        query.put("age", "26");
//
//        JsonDownloader jsonDownloader = new JsonDownloader(this, new DownloadCallback() {
//            @Override
//            public void onDownloadSuccessfully(Object data) {
//                if (data instanceof ArrayList){
//                    @SuppressWarnings("unchecked")
//                    ArrayList<DumpModel> list = (ArrayList<DumpModel>) data;
//                    Toast.makeText(getBaseContext(), "data length "+ list.size(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onDownloadFailed(Exception e) {
//
//            }
//        });
//
//        jsonDownloader.showProgressbar(true, true)
////                .setType(Method.POST)
//                .setParams(query)
//                .setParser(new DumpParser())
//                .execute("https://ota.dev.vc/merries/banner/banner.json");
    }
}
