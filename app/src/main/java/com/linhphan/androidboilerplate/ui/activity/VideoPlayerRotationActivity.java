package com.linhphan.androidboilerplate.ui.activity;

import android.os.Bundle;

import com.linhphan.androidboilerplate.R;
import com.linhphan.androidboilerplate.ui.fragment.VideoPlayerRotationFragment;

/**
 * Created by linhphan on 7/4/16.
 */
public class VideoPlayerRotationActivity extends BaseActivity {
    @Override
    protected int getActivityLayoutResource() {
        return R.layout.activity_video_player_rotation;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void getWidgets(Bundle savedInstanceState) {
        replaceFragment(R.id.frl_content, VideoPlayerRotationFragment.class, false, null, null);
    }

    @Override
    protected void registerEventHandler() {

    }
}
