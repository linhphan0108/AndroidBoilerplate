package com.linhphan.androidboilerplate.api;

import android.content.Context;
import android.os.AsyncTask;

import com.linhphan.androidboilerplate.api.Parser.JsonParser;
import com.linhphan.androidboilerplate.callback.DownloadCallback;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by linhphan on 11/12/15.
 */
public class JsonDownloader extends AsyncTask<String, Integer, Object> {

    private Context mContext;
    private DownloadCallback mCallback;
    private JsonParser mParser;
    private Exception mException;

    public JsonDownloader(Context mContext, DownloadCallback mCallback, JsonParser parser) {
        this.mContext = mContext;
        this.mCallback = mCallback;
        this.mParser = parser;
    }

    @Override
    protected Object doInBackground(String... params) {
        String url = params[0];
        try {
            String data = retrieveDataFromServer(url);
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
    }

    /**
     * try to retrieve data from remote server
     *
     * @param path
     * @return data which is presented by a string
     * @throws IOException
     */
    private String retrieveDataFromServer(String path) throws IOException {
        URL url = new URL(path);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());
        InputStreamReader reader = new InputStreamReader(in, Charset.forName("UTF-8"));
        BufferedReader bufferedReader = new BufferedReader(reader);
        String result = readBuffer(bufferedReader);

        bufferedReader.close();
        reader.close();
        in.close();
        httpURLConnection.disconnect();
        return result;
    }

    /**
     * read data from buffer
     *
     * @param reader
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
}
