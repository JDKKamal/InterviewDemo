package com.jdkgroup.interacter.operators;

import android.content.ActivityNotFoundException;
import android.content.Context;

import com.jdkgroup.interacter.InterActorCallback;
import com.jdkgroup.interviewdemo.R;
import com.jdkgroup.model.Response;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RxAPICallObservable {
    public RxAPICallObservable() {
    }

    public static <T> Disposable call(Context context, Observable<T> observable, final InterActorCallback<T> callback) {
        callback.onStart();

        if (observable == null) {
            throw new IllegalArgumentException(context.getString(R.string.msg_observable_not_null));
        }

        if (callback == null) {
            throw new IllegalArgumentException(context.getString(R.string.msg_callback_not_null));
        }

        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((T t) -> {
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
                }, throwable -> {
                    if (throwable instanceof SocketTimeoutException) {
                        callback.onError(context.getString(R.string.msg_connection_time_out));
                    } else if (throwable instanceof ActivityNotFoundException) {
                        callback.onError(context.getString(R.string.msg_activity_not_found));
                    } else if (throwable instanceof UnknownHostException || throwable instanceof ConnectException) {
                        callback.onError(context.getString(R.string.msg_server_not_responding));
                    } else {
                        callback.onError(context.getString(R.string.msg_something_wrong));
                    }
                    callback.onFinish();
                });
    }
}
