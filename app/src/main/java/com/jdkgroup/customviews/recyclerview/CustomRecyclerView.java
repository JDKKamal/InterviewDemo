package com.jdkgroup.customviews.recyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.View;

public class  CustomRecyclerView extends RecyclerView {
    private OnLoadMoreListener mOnLoadMoreListener;
    private boolean isLoading;
    private View mLoadMoreView;

    public CustomRecyclerView(Context context) {
        super(context);
        init();
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

    private void init() {
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
                if (staggeredGridLayoutManager.getSpanCount() == getAdapter().getItemCount() - 1 && isRecyclerScrollable() && mOnLoadMoreListener != null) {
                    isLoading = true;

                    if (mLoadMoreView != null) {
                        mLoadMoreView.setVisibility(View.VISIBLE);
                        recyclerView.scrollToPosition(getAdapter().getItemCount() - 1);
                    }
                    mOnLoadMoreListener.onLoadMore();
                }
            }
        });

}

    public boolean isRecyclerScrollable() {
        return computeVerticalScrollRange() > getHeight();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener, View mLoadMoreView) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
        this.mLoadMoreView = mLoadMoreView;
    }

    public void loadMoreComplete() {
        isLoading = false;
        if(mLoadMoreView!=null){
        if (mLoadMoreView.getVisibility() == View.VISIBLE) {
            mLoadMoreView.setVisibility(View.GONE);
        }}
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }
}
