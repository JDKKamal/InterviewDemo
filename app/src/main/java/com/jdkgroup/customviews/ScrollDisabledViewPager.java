package com.jdkgroup.customviews;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ScrollDisabledViewPager extends ViewPager
{
    private boolean isPagingEnabled = true;

    public ScrollDisabledViewPager(Context context)
    {
        super(context);
    }

    public ScrollDisabledViewPager(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        return this.isPagingEnabled && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event)
    {
        return this.isPagingEnabled && super.onInterceptTouchEvent(event);
    }

    public void setPagingEnabled(boolean b)
    {
        this.isPagingEnabled = b;
    }

}
