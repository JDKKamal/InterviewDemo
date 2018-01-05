package com.jdkgroup.interviewdemo.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;

import com.jdkgroup.baseclass.BaseActivity;
import com.jdkgroup.customviews.appcompatedittext.AppEditTextChangedListener;
import com.jdkgroup.interviewdemo.R;
import com.jdkgroup.interviewdemo.adapter.BaseAdapter;
import com.jdkgroup.interviewdemo.adapter.FilterAdapter;
import com.jdkgroup.model.ModelMultipleSelect;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FilterActivity extends BaseActivity implements AppEditTextChangedListener.OnEditTextChangedListener {

    @BindView(R.id.appEditFilter)
    AppCompatEditText appEditFilter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private FilterAdapter filterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        bindViews();

        setRecyclerView(recyclerView, 0, recyclerViewLinearLayout);

        filterAdapter = new FilterAdapter(getActivity(), getList());
        recyclerView.setAdapter(filterAdapter);

        appEditFilter.addTextChangedListener(new AppEditTextChangedListener(this, appEditFilter));

    }

    @Override
    public void onTextChanged(String str) {
        filterAdapter.getFilter().filter(str);
    }


    public ArrayList<ModelMultipleSelect> getList() {
        ArrayList<ModelMultipleSelect> listMultipleSelect = new ArrayList<>();

        for (int i = 0; i < 37; i++) {
            listMultipleSelect.add(new ModelMultipleSelect((i + 1) + "", "Select " + i));
        }
        return listMultipleSelect;

    }
}
