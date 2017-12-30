package com.jdkgroup.interviewdemo.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;

import com.jdkgroup.baseclass.BaseActivity;
import com.jdkgroup.customviews.appcompatedittext.AppEditTextChangedListener;
import com.jdkgroup.interviewdemo.R;

import butterknife.BindView;

public class FilterActivity extends BaseActivity implements AppEditTextChangedListener.OnEditTextChangedListener {

    @BindView(R.id.appEditFilter)
    AppCompatEditText appEditFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        bindViews();

        appEditFilter.addTextChangedListener(new AppEditTextChangedListener(this, appEditFilter));

    }

    @Override
    public void onTextChanged(String str) {
        System.out.println("Tag" + str);
    }
}
