package com.jdkgroup.baseclass;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import org.greenrobot.eventbus.EventBus;

import javax.annotation.Nullable;

public abstract class BaseService extends Service {

    public BaseService() {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (useEventBus()) {
            EventBus.getDefault().register(this);
        }

        this.init();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (useEventBus()) {
            EventBus.getDefault().unregister(this);
        }
    }

    /**
     * 子类实现初始化
     */
    abstract public void init();

    /**
     * 使用eventBus
     *
     * @return
     */
    protected boolean useEventBus() {
        return true;
    }
}