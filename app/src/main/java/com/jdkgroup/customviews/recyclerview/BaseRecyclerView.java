package com.jdkgroup.customviews.recyclerview;

import android.support.v7.widget.RecyclerView;

public abstract class BaseRecyclerView<T> extends RecyclerView.Adapter<BaseViewHolder<T>> {

  protected abstract T getItem(int position);

  @Override
  public void onBindViewHolder(BaseViewHolder<T> holder, int position) {
    holder.populateItem(getItem(position));
  }
}
