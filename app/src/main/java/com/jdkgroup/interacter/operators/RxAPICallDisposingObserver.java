package com.jdkgroup.interacter.operators;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.support.annotation.CallSuper;

import com.jdkgroup.interacter.InterActorCallback;
import com.jdkgroup.interacter.disposablemanager.DisposableManager;
import com.jdkgroup.model.Response;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import com.jdkgroup.interviewdemo.R;

/**
 * Created by Lakhani on 12/1/2017.
 * https://medium.com/@CodyEngel/managing-disposables-in-rxjava-2-for-android-388722ae1e8a
 */

public class RxAPICallDisposingObserver<T> implements Observer<T> {
    private Context context;
    private InterActorCallback<T> callback;

    public RxAPICallDisposingObserver(Context context, InterActorCallback<T> callback) {
        this.context = context;
        this.callback = callback;
    }

    @Override
    @CallSuper
    public void onSubscribe(Disposable d) {
        callback.onStart();
        DisposableManager.add(d);
    }

    @Override
    public void onNext(T t) {
        Response response = (Response) t;
        switch (response.getStatus()) {
            case 200:
                callback.onResponse(t);
                break;

            case 404:
                //TODO LOGOUT
                break;

            default:
                callback.onError(response.getMessage());
                break;
        }
    }

    @Override
    public void onError(Throwable e) {
        callback.onError(exceptionHandle(e));
        callback.onFinish();
    }

    @Override
    public void onComplete() {
        callback.onFinish();
    }

    private String exceptionHandle(Throwable throwable) {
        if (throwable instanceof SocketTimeoutException) {
            return context.getString(R.string.msg_connection_time_out);
        } else if (throwable instanceof ActivityNotFoundException) {
            return context.getString(R.string.msg_activity_not_found);
        } else if (throwable instanceof UnknownHostException || throwable instanceof ConnectException) {
            return context.getString(R.string.msg_server_not_responding);
        } else {
            return context.getString(R.string.msg_something_wrong);
        }
    }
}