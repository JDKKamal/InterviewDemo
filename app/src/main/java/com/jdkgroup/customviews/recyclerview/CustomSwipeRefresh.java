package com.jdkgroup.customviews.recyclerview;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

import com.jdkgroup.interviewdemo.R;

public class CustomSwipeRefresh extends SwipeRefreshLayout {
    public CustomSwipeRefresh(Context context) {
        super(context);
        init();
    }

    public CustomSwipeRefresh(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setColorSchemeResources(R.color.colorAccent);

    }

    @Override
    public boolean canChildScrollUp() {
        return false;
    }
}
