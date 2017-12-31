package com.jdkgroup.interviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.jdkgroup.customviews.recyclerview.BaseRecyclerView;
import com.jdkgroup.customviews.recyclerview.BaseViewHolder;
import com.jdkgroup.interviewdemo.R;
import com.jdkgroup.model.ModelMultipleSelect;

import butterknife.BindView;

public class BaseAdapter extends BaseRecyclerView<ModelMultipleSelect> {

    private LayoutInflater inflater;
    private List<ModelMultipleSelect> profiles;

    public BaseAdapter(Context context, ArrayList<ModelMultipleSelect> profiles) {
        this.inflater = LayoutInflater.from(context);
        this.profiles = profiles;
    }

    @Override
    protected ModelMultipleSelect getItem(int position) {
        return profiles.get(position);
    }

    @Override
    public BaseViewHolder<ModelMultipleSelect> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProfileViewHolder(inflater.inflate(R.layout.itemview_design_a, parent, false));
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class ProfileViewHolder extends BaseViewHolder<ModelMultipleSelect> {
        @BindView(R.id.appTvTitle)
        AppCompatTextView appTvTitle;

        public ProfileViewHolder(View itemView) {
            super(itemView);

        }

        @Override
        public void populateItem(ModelMultipleSelect profile) {
            appTvTitle.setText(profile.getCategoryName() + "");
        }
    }
}
