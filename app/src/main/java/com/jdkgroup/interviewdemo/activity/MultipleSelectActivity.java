package com.jdkgroup.interviewdemo.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jdkgroup.baseclass.BaseActivity;
import com.jdkgroup.interviewdemo.R;
import com.jdkgroup.interviewdemo.adapter.MultipleSelectAdapter;
import com.jdkgroup.model.ModelMultipleSelect;
import com.jdkgroup.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MultipleSelectActivity extends BaseActivity {

    @BindView(R.id.rvFilter)
    RecyclerView rvFilter;

    private MultipleSelectAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_select);

        bindViews();

        rvFilter.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MultipleSelectAdapter(this, getList(), new MultipleSelectAdapter.OnCheckedChange(){
            @Override
            public void onLoadedList(List<ModelMultipleSelect> listMultipleSelect) {
                AppUtils.logD(getToJsonClass(listMultipleSelect));
            }
        });
        rvFilter.setAdapter(adapter);
    }

    public List<ModelMultipleSelect> getList() {
        List<ModelMultipleSelect> listMultipleSelect = new ArrayList<>();

        for (int i = 0; i < 37; i++) {
            listMultipleSelect.add(new ModelMultipleSelect((i + 1) + "", "Select " + i));
        }
        return listMultipleSelect;

    }

}
