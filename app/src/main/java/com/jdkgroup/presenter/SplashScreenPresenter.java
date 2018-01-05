package com.jdkgroup.presenter;

import android.app.Activity;
import android.os.Handler;

import com.jdkgroup.baseclass.BasePresenter;
import com.jdkgroup.interviewdemo.DrawerActivity;
import com.jdkgroup.interviewdemo.activity.CallAPIActivity;
import com.jdkgroup.interviewdemo.rxjava.MainActivity;
import com.jdkgroup.utils.AppUtils;
import com.jdkgroup.view.SplashScreenView;

public class SplashScreenPresenter extends BasePresenter<SplashScreenView> {
    public void getSplashScreenWait(int timeOut, final Activity activity) {
        new Handler().postDelayed(() -> {
            AppUtils.startActivity(activity, DrawerActivity.class);
            activity.finish();
        }, timeOut);
    }
}