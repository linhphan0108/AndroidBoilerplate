package com.linhphan.androidboilerplate.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.linhphan.androidboilerplate.R;
import com.linhphan.androidboilerplate.ui.widget.ImageItem;

/**
 * Created by linhphan on 3/17/16.
 */
public class HandleImageFragment extends BaseFragment implements View.OnClickListener {
    private Button mBtnNewItem;

    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_handle_image;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void getWidgets(View root, Bundle savedInstanceState) {
        ImageItem stickItem = new ImageItem(getContext());
        stickItem.setImageDrawable(getResources().getDrawable(R.drawable.img));
        ViewGroup frameLayout = (ViewGroup) root;
        frameLayout.addView(stickItem);

        mBtnNewItem = (Button) root.findViewById(R.id.btn_add_new_item);
    }

    @Override
    protected void registerEventHandler() {
        mBtnNewItem.setOnClickListener(this);
    }

    //============= implemented methods ============================================================
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_new_item:
                View view = getView();
                if (view != null && view instanceof FrameLayout){
                    FrameLayout frameLayout = (FrameLayout) view;
                    ImageItem stickItem = new ImageItem(getContext());
                    stickItem.setImageDrawable(getResources().getDrawable(R.drawable.img));
                    frameLayout.addView(stickItem);
                }
                break;

            default:
                break;
        }
    }
}
