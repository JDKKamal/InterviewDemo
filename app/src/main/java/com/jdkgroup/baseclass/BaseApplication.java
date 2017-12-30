package com.jdkgroup.baseclass;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.jdkgroup.interviewdemo.R;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class BaseApplication extends MultiDexApplication {

    private static BaseApplication baseApplication = null;

    public static BaseApplication getBaseApplication() {
        return baseApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        baseApplication = this;

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().name("app.realm").build();
        Realm.getInstance(realmConfiguration);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(this.getString(R.string.regular_font))
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
}