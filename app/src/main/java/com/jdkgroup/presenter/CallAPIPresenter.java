package com.jdkgroup.presenter;

import android.app.Activity;

import com.jdkgroup.baseclass.BasePresenter;
import com.jdkgroup.interacter.InterActorCallback;
import com.jdkgroup.model.callapi.ModelCallApi;
import com.jdkgroup.view.CallAPIView;

public class CallAPIPresenter extends BasePresenter<CallAPIView> {
    public void callAPI(final Activity activity) {
        getAppInteractor().callAPINew(activity, new InterActorCallback<ModelCallApi>() {
            @Override
            public void onStart() {
                getView().showProgressDialog(true);
            }

            @Override
            public void onResponse(ModelCallApi response) {
                getView().callCategory(response);
                getView().showProgressDialog(false);
            }

            @Override
            public void onFinish() {
                getView().showProgressDialog(false);
            }

            @Override
            public void onError(String message) {
                getView().onFailure(message);
            }

        });
    }
}