package com.jdkgroup.baseclass;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public abstract class SimpleMVPActivity<P extends BasePresenter<V>, V extends BaseView> extends BaseActivity {

    private P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = createPresenter();
        presenter.attachView(attachView());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public abstract
    @NonNull
    P createPresenter();

    public abstract
    @NonNull
    V attachView();

    public P getPresenter() {
        return presenter;
    }

    public boolean hasInternet() {
        return hasInternet();
    }

    public Activity getActivity() {
        return this;
    }
}
