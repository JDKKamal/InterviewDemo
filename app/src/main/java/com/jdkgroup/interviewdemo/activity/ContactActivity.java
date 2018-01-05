package com.jdkgroup.interviewdemo.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.widget.RecyclerView;

import com.jdkgroup.baseclass.BaseActivity;
import com.jdkgroup.customviews.contact.Contact;
import com.jdkgroup.customviews.contact.RxContacts;
import com.jdkgroup.customviews.permission.PermissionResultCallback;
import com.jdkgroup.customviews.permission.PermissionUtils;
import com.jdkgroup.interviewdemo.R;
import com.jdkgroup.interviewdemo.adapter.ContactAdapter;
import com.jdkgroup.utils.AppUtils;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;

import java.util.ArrayList;

public class ContactActivity extends BaseActivity implements OnRequestPermissionsResultCallback, PermissionResultCallback {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private ContactAdapter contactAdapter;

    ArrayList<String> permissions = new ArrayList<>();
    PermissionUtils permissionUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        bindViews();
        permissionUtils = new PermissionUtils(getActivity());
        permissions.add(Manifest.permission.READ_CONTACTS);

        setRecyclerView(recyclerView, 0, recyclerViewLinearLayout);

        permissionUtils.check_permission(permissions,"Explain here why the app needs permissions",1);
    }

    private ContactAdapter getContactAdapter() {
        if (contactAdapter != null) {
            return contactAdapter;
        }
        contactAdapter = new ContactAdapter();
        return contactAdapter;
    }

    private void requestContacts() {
        RxContacts
                .fetch(this)
                .filter(m -> m.getInVisibleGroup() == 1)
                .toSortedList(Contact::compareTo)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(contacts -> {
                    ContactAdapter adapter = getContactAdapter();
                    adapter.setContacts(contacts);
                }, it -> {

                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionUtils.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    @Override
    public void PermissionGranted(int request_code) {
        AppUtils.logD("Permission granted");
        //TODO PERMISSION IS GRANTED
        requestContacts();
    }

    @Override
    public void PartialPermissionGranted(int request_code, ArrayList<String> granted_permissions) {
        AppUtils.logD("Permission partially granted");
    }

    @Override
    public void PermissionDenied(int request_code) {
        AppUtils.logD("Permission denied");
    }

    @Override
    public void NeverAskAgain(int request_code) {
        AppUtils.logD("Permission never ask again");

        Intent appSetting = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + this.getPackageName()));
        appSetting.addCategory(Intent.CATEGORY_DEFAULT);
        appSetting.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(appSetting);
    }
}
