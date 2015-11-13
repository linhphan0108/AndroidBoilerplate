package com.linhphan.androidboilerplate.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;;

import com.linhphan.androidboilerplate.api.Parser.JsonParser;
import com.linhphan.androidboilerplate.callback.DownloadCallback;
import com.linhphan.androidboilerplate.util.Logger;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * Created by linhphan on 11/12/15.
 */
public class JsonDownloader extends AsyncTask<String, Integer, Object> {

    private Context mContext;
    private Method mType = Method.GET;//the method of request whether POST or GET, default value is GET
    private Map<String, String> mParams;
    private DownloadCallback mCallback;
    private JsonParser mParser;
    private ProgressDialog mProgressbar;
    private boolean mIsProgressbarHorizontal;
    private boolean mIsShowProgressbar;
    private Exception mException;

    public JsonDownloader(Context mContext, DownloadCallback mCallback) {
        this.mContext = mContext;
        this.mCallback = mCallback;
    }

    public JsonDownloader setType(Method type) {
        this.mType = type;
        return this;
    }

    public JsonDownloader setParams(Map<String, String> params) {
        this.mParams = params;
        return this;
    }

    public JsonDownloader setParser(JsonParser jsonParser){
        mParser = jsonParser;
        return this;
    }

    /**
     * setup the progressbar which will be showed on screen
     *
     * @param isShow     the progressbar will be showed if this parameter is true, otherwise nothing will be showed
     * @param horizontal if this parameter is true then the progressbar will showed in horizontal style, otherwise the progressbar will be showed in spinner style
     * @return JsonDownloader object
     */
    public JsonDownloader showProgressbar(boolean isShow, boolean horizontal) {
        this.mIsShowProgressbar = isShow;
        this.mIsProgressbarHorizontal = horizontal;
        return this;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mIsShowProgressbar) {
            mProgressbar = new ProgressDialog(mContext);

            //====
            if (mIsProgressbarHorizontal) {
                mProgressbar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                mProgressbar.setMax(100);
                mProgressbar.setProgress(0);

            } else {
                mProgressbar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            }

            mProgressbar.setMessage("Please! wait a minute");
            mProgressbar.setCancelable(false);
            mProgressbar.show();
        }
    }

    @Override
    protected Object doInBackground(String... params) {

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);

                if (mProgressbar != null)
                    publishProgress(mProgressbar.getProgress() + 10);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String url = params[0];
        String data;
        try {
            if (mType == Method.POST) {
                data = sendPost(url, mParams);
            } else {
                data = sendGet(url);
            }
            Logger.i(getClass().getName(), "got data from server: " + data);
            if (mParser != null)
                return mParser.parse(data);
            else
                return data;
        } catch (IOException e) {
            e.printStackTrace();
            mException = e;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if (mException == null)
            mCallback.onDownloadSuccessfully(o);
        else {
            mCallback.onDownloadFailed(mException);
        }
        if (mProgressbar != null && mProgressbar.isShowing())
            mProgressbar.dismiss();

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (mProgressbar != null && mProgressbar.isShowing())
            mProgressbar.setProgress(values[0]);
    }

    /**
     * try to retrieve data from remote server
     *
     * @return data from server which is presented by a string
     * @throws IOException
     */
    private String sendGet(String path) throws IOException {
        String query = null;
        if (mParams != null)
            query = encodeQueryString(mParams);
        if (query != null && !query.isEmpty())
            query = "?" + query;
        URL url = new URL(path + query);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        int responseCode = httpURLConnection.getResponseCode();
        Logger.i(getClass().getName(), "sending POST  request to URL: " + path);
        Logger.i(getClass().getName(), "post parameters: " + query);
        Logger.i(getClass().getName(), "response code: " + responseCode);

        if (responseCode != HttpURLConnection.HTTP_OK) {
            Logger.e(getClass().getName(), "connection is failed");
            return null;
        }

        InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());
        InputStreamReader inputStreamReader = new InputStreamReader(in, Charset.forName("UTF-8"));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String result = readBuffer(bufferedReader);

        bufferedReader.close();
        inputStreamReader.close();
        in.close();
        httpURLConnection.disconnect();
        return result;
    }

    /**
     * try to retrieve data from remote server
     * dump server: http://www.posttestserver.com/
     *
     * @return data from server which is presented by a string
     * @throws IOException
     */
    private String sendPost(String path, Map<String, String> params) throws IOException {
        URL url = new URL(path);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        String query = encodeQueryString(params);


        //== add header
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Length", "" + Integer.toString(query.getBytes().length));

        //== set post request
        httpURLConnection.setDoOutput(true);
        DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
        dataOutputStream.writeBytes(query);
        dataOutputStream.flush();
        dataOutputStream.close();

        int responseCode = httpURLConnection.getResponseCode();
        Logger.i(getClass().getName(), "sending POST  request to URL: " + path);
        Logger.i(getClass().getName(), "post parameters: " + query);
        Logger.i(getClass().getName(), "response code: " + responseCode);

        if (responseCode != HttpURLConnection.HTTP_OK) {
            Logger.e(getClass().getName(), "connection is failed");
            return null;
        }

        InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());
        InputStreamReader inputStreamReader = new InputStreamReader(in, Charset.forName("UTF-8"));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String result = readBuffer(bufferedReader);

        //== close streams
        bufferedReader.close();
        inputStreamReader.close();
        in.close();
        httpURLConnection.disconnect();
        return result;
    }

    /**
     * read data from buffer
     *
     * @return data which is presented by a string
     * @throws IOException
     */
    private String readBuffer(BufferedReader reader) throws IOException {
        if (reader == null) return null;
        StringBuilder builder = new StringBuilder();
        int tamp;
        while ((tamp = reader.read()) != -1) {
            builder.append((char) tamp);
        }
        return builder.toString();
    }

    private String encodeQueryString(Map<String, String> params) throws UnsupportedEncodingException {
        final char PARAMETER_DELIMITER = '&';
        final char PARAMETER_EQUALS_CHAR = '=';

        StringBuilder builder = new StringBuilder();
        if (params != null) {
            boolean firstParameter = true;
            for (String key : params.keySet()) {
                if (!firstParameter) {
                    builder.append(PARAMETER_DELIMITER);
                }
                builder.append(key)
                        .append(PARAMETER_EQUALS_CHAR)
                        .append(URLEncoder.encode(params.get(key), "UTF-8"));

                if (firstParameter)
                    firstParameter = false;
            }
        }

        return builder.toString();
    }
}
