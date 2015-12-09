package com.linhphan.androidboilerplate.callback;

/**
 * Created by linhphan on 11/12/15.
 */
public interface DownloadCallback {
    int UNKNOWN_REQUEST_CODE = 1000;

    void onDownloadSuccessfully(Object data, int requestCode);
    void onDownloadFailed(Exception e, int requestCode);
}
