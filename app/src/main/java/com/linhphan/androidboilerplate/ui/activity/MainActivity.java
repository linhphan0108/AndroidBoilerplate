package com.linhphan.androidboilerplate.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.linhphan.androidboilerplate.R;
import com.linhphan.androidboilerplate.listener.ConfirmDialogCallback;
import com.linhphan.androidboilerplate.ui.dialog.ConfirmDialog;
import com.linhphan.androidboilerplate.util.Constant;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConfirmDialog dialog = new ConfirmDialog();
        dialog.registerCallback(new ConfirmDialogCallback() {
            @Override
            public void onOk() {
                Toast.makeText(MainActivity.this, "ok is clicked", Toast.LENGTH_SHORT).show();
            }
        });
        Bundle bundle = new Bundle();
        bundle.putString(Constant.DIALOG_TITLE, "the title here");
        bundle.putString(Constant.DIALOG_MESSAGE, "the message here");
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), ConfirmDialog.class.getSimpleName());
    }
}
