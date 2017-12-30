package com.jdkgroup.interviewdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.jdkgroup.baseclass.SimpleMVPActivity;
import com.jdkgroup.interacter.AppInteractor;
import com.jdkgroup.interviewdemo.activity.CallAPIActivity;
import com.jdkgroup.presenter.SplashScreenPresenter;
import com.jdkgroup.utils.AppUtils;
import com.jdkgroup.utils.PreferenceUtils;
import com.jdkgroup.view.SplashScreenView;

public class SplashScreen extends SimpleMVPActivity<SplashScreenPresenter, SplashScreenView> implements SplashScreenView {

    private final int SPLASH_TIME_OUT = 3000;
    private AppInteractor appInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        //TODO GET DEVICE INFORMATION
        appInteractor = new AppInteractor();
        appInteractor.getDeviceInfo(getActivity());

        if (PreferenceUtils.getInstance(this).isLogin() == false) {
            //TODO SPLASH SCREEN TIME OUT
            getPresenter().getSplashScreenWait(SPLASH_TIME_OUT, this);
        } else {
            AppUtils.startActivity(getActivity(), CallAPIActivity.class);
            finish();
        }
    }

    @NonNull
    @Override
    public SplashScreenPresenter createPresenter() {
        return new SplashScreenPresenter();
    }

    @NonNull
    @Override
    public SplashScreenView attachView() {
        return this;
    }

    @Override
    public void setSplashScreenWait() {

    }

    @Override
    public void onFailure(String message) {

    }
}