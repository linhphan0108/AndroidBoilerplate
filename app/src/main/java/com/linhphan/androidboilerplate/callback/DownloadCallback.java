package com.linhphan.androidboilerplate.callback;

/**
 * Created by linhphan on 11/12/15.
 */
public interface DownloadCallback {
    int UNKNOWN_REQUEST_CODE = 1000;

    void onSuccessfully(Object data, int requestCode);
    void onFailed(Exception e, int requestCode);
}
