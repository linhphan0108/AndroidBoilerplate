package com.linhphan.androidboilerplate.ui.fragment;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.linhphan.androidboilerplate.R;

/**
 * Created by linhphan on 7/4/16.
 */
public class VideoPlayerRotationFragment extends BaseFragment implements SurfaceHolder.Callback, MediaPlayer.OnPreparedListener {

    private static MediaPlayer mediaPlayer;
    private SurfaceHolder vidHolder;
    private SurfaceView vidSurface;
    String vidAddress = "https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";

    //=============== inherited methods ============================================================
    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_video_player_rotation;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void getWidgets(View root, Bundle savedInstanceState) {
        vidSurface = (SurfaceView) root.findViewById(R.id.surfView);
        vidHolder = vidSurface.getHolder();

    }

    @Override
    protected void registerEventHandler() {
        vidHolder.addCallback(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    //========== implemented methods ===============================================================
    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        if (mediaPlayer == null) {
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDisplay(vidHolder);
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnPreparedListener(this);
                mediaPlayer.setDataSource(vidAddress);
                mediaPlayer.prepareAsync();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            mediaPlayer.start();
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mediaPlayer.start();
    }
}
