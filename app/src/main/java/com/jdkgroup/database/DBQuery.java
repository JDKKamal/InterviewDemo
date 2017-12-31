package com.jdkgroup.database;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;

import java.util.Collection;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by lakhani on 9/26/2017.
 */

public class  DBQuery {

    private static DBQuery instance;
    private final Realm realm;

    public DBQuery(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static DBQuery with(Fragment fragment) {
        if (instance == null) {
            instance = new DBQuery(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static DBQuery with(Activity activity) {
        if (instance == null) {
            instance = new DBQuery(activity.getApplication());
        }
        return instance;
    }

    public static DBQuery with(Application application) {
        if (instance == null) {
            instance = new DBQuery(application);
        }
        return instance;
    }

    public static DBQuery getInstance() {
        return instance;
    }

    public Realm getRealm() {
        return realm;
    }

    void closeRealm() {
        if (realm != null && !realm.isClosed()) {
            realm.close();
        }
    }

    public long realmVersion() {
        return realm.getVersion();
    }

    public void realmInsert(RealmModel realmModel) {
        realm.beginTransaction();
        realm.insert(realmModel);
        realm.commitTransaction();
    }

    public void realmInsertList(Collection<? extends RealmModel> list) {
        realm.beginTransaction();
        realm.insert(list);
        realm.commitTransaction();
    }

    public RealmResults realmList(Class classObject) {
        return realm.where(classObject).findAll();
    }
}