package com.jdkgroup.baseclass;

import android.app.Activity;
import android.view.View;

import java.util.Map;

public interface BaseView<T> {
    boolean hasInternet();
    void showProgressDialog(boolean show);
    //void onSuccess(T response);
    //void onSuccess(List<T> response);
    void onFailure(String message);
    Map<String, String> getDefaultParameter();
    Activity getActivity();
}