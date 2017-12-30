package com.jdkgroup.interviewdemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jdkgroup.baseclass.BaseFragment;
import com.jdkgroup.interviewdemo.R;

/*
    API Call in fragment MVPFragment<Presenter, View> implements View
*/

public class FragmentTab extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);

        return view;
    }
}