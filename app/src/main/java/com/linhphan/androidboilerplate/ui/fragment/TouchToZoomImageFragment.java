package com.linhphan.androidboilerplate.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.linhphan.androidboilerplate.R;
import com.linhphan.androidboilerplate.util.Logger;
import com.linhphan.androidboilerplate.util.ViewUtil;

/**
 * Created by linhphan on 3/16/16.
 */
public class TouchToZoomImageFragment extends BaseFragment {

    private ImageView mImg;
    private Matrix mMatrix;
    private Matrix mOrigin;
    private int mRealBitmapWidth;
    private int mRealBitmapHeight;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();



    }

    @Override
    public void onResume() {
        super.onResume();

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);

        mRealBitmapHeight = bitmap.getWidth();
        mRealBitmapHeight = bitmap.getHeight();
        int imageViewWidth = (int)ViewUtil.convertDp2Px(200);
        int imageViewHeight = (int)ViewUtil.convertDp2Px(360);

        float ratioScale = Math.min((float)imageViewWidth / mRealBitmapHeight, (float)imageViewHeight / mRealBitmapHeight);

        mMatrix.setScale(ratioScale, ratioScale);
        mOrigin.setScale(ratioScale, ratioScale);

        mImg.setImageDrawable(bitmapDrawable);
        mImg.setScaleType(ImageView.ScaleType.MATRIX);
        mImg.setImageMatrix(mMatrix);
    }

    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_touch_to_zoom_image;
    }

    @Override
    protected void init() {
        mMatrix = new Matrix();
        mOrigin = new Matrix();
    }

    @Override
    protected void getWidgets(View root, Bundle savedInstanceState) {
        if (root != null){
            mImg = (ImageView) root.findViewById(R.id.img);
        }
    }

    @Override
    protected void registerEventHandler() {
        mImg.setOnTouchListener(new View.OnTouchListener() {

            private ViewGroup.LayoutParams layoutParams;

            private PointF onDownPoint = new PointF();
            private PointF onMovePoint = new PointF();
            private int originWidth;
            private int originHeight;

            @Override
            public boolean onTouch(View v, MotionEvent event) {


                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mMatrix = new Matrix();
                        onDownPoint.set(event.getX(), event.getY());
                        onMovePoint.set(event.getX(), event.getY());
                        layoutParams  = mImg.getLayoutParams();
                        originWidth = mImg.getWidth();
                        originHeight = mImg.getHeight();
                        Logger.d(getClassTagName(), "ACTION_DOWN x: "+ onDownPoint.x +", y: "+ onDownPoint.y);
                        break;

                    case MotionEvent.ACTION_MOVE:
                        onMovePoint.set(event.getX(), event.getY());
                        Logger.d(getClassTagName(), "ACTION_MOVE x: " + onMovePoint.x + ", y: " + onMovePoint.y);
                        layoutParams.width = (int) (mImg.getWidth() * 1.01f);
                        layoutParams.height = (int) (mImg.getHeight() * 1.01f);

                        float ratioScale = Math.min((float)layoutParams.width / mRealBitmapHeight, (float)layoutParams.height / mRealBitmapHeight);
                        Logger.d(getClassTagName(), "ratio: " + ratioScale);
                        mMatrix.setScale(ratioScale, ratioScale);
                        mImg.setImageMatrix(mMatrix);
                        mImg.setLayoutParams(layoutParams);
                        mImg.invalidate();
                        break;

                    case MotionEvent.ACTION_UP:
                        onMovePoint.set(event.getX(), event.getY());
                        Logger.d(getClassTagName(), "ACTION_UP x: " + onMovePoint.x + ", y: " + onMovePoint.y);
                        mImg.setImageMatrix(mOrigin);
                        layoutParams.width = originWidth;
                        layoutParams.height = originHeight;
                        mImg.setLayoutParams(layoutParams);
                        mImg.invalidate();
                        break;
                }

                return true;
            }
        });
    }
}
