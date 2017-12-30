package com.jdkgroup.interviewdemo.adapter.viewpager;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.jdkgroup.interviewdemo.R;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    private Activity activity;
    private List<String> listDealDetailImages;

    public ViewPagerAdapter(Activity activity, List<String> listDealDetailImages) {
        this.activity = activity;
        this.listDealDetailImages = listDealDetailImages;
    }

    @Override
    public int getCount() {
        return listDealDetailImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(((View) object));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        AppCompatImageView appIvImage;

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.itemview_viewpager_slider, container, false);

        appIvImage = view.findViewById(R.id.appIvImage);

        Glide.with(activity)
                .load(listDealDetailImages.get(position))
                .into(appIvImage);

        container.addView(view);
        return view;
    }
}
