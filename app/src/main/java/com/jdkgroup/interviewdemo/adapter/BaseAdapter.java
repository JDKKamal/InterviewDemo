package com.jdkgroup.interviewdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.login.Login;

import java.util.ArrayList;

import com.jdkgroup.customviews.recyclerview.BaseRecyclerView;
import com.jdkgroup.customviews.recyclerview.BaseViewHolder;
import com.jdkgroup.interviewdemo.R;


public class BaseAdapter extends BaseRecyclerView<Login> {

    private LayoutInflater inflater;
    private ArrayList<Login> profiles;

    public BaseAdapter(Context context, ArrayList<Login> profiles) {
        this.inflater = LayoutInflater.from(context);
        this.profiles = profiles;
    }

    @Override
    protected Login getItem(int position) {
        return profiles.get(position);
    }

    @Override
    public BaseViewHolder<Login> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProfileViewHolder(inflater.inflate(R.layout.itemview_design_a, parent, false));
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }
    static class ProfileViewHolder extends BaseViewHolder<Login> {

        public ProfileViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void populateItem(Login profile) {

        }
    }
}
