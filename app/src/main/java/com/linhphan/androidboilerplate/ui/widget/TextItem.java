package com.linhphan.androidboilerplate.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by linhphan on 7/22/16.
 */
public class TextItem extends StickItem {
    public TextItem(Context context) {
        super(context);
    }

    public TextItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TextItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected View getMainView() {
        return null;
    }
}
