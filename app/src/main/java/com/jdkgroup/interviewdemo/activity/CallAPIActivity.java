package com.jdkgroup.interviewdemo.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.jdkgroup.baseclass.SimpleMVPActivity;
import com.jdkgroup.database.AppDatabase;
import com.jdkgroup.interviewdemo.R;
import com.jdkgroup.interviewdemo.adapter.viewpager.ViewPagerAdapter;
import com.jdkgroup.model.callapi.ModelCallApi;
import com.jdkgroup.presenter.CallAPIPresenter;
import com.jdkgroup.utils.AppUtils;
import com.jdkgroup.view.CallAPIView;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;

public class CallAPIActivity extends SimpleMVPActivity<CallAPIPresenter, CallAPIView> implements CallAPIView {

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_api);

        bindViews();

        getPresenter().callAPI(this);
    }

    @NonNull
    @Override
    public CallAPIPresenter createPresenter() {
        return new CallAPIPresenter();
    }

    @NonNull
    @Override
    public CallAPIView attachView() {
        return this;
    }

    @Override
    public void onFailure(String message) {
        AppUtils.showToast(this, message);
    }

    @Override
    public void callCategory(ModelCallApi response) {
        viewPagerAdapter = new ViewPagerAdapter(getActivity(), response.getData().getDealDetailImages());
        viewPager.setAdapter(viewPagerAdapter);
    }

    @Override
    public void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }
}
