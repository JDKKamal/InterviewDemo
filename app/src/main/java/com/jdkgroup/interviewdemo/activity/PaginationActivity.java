package com.jdkgroup.interviewdemo.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;

import com.jdkgroup.baseclass.BaseActivity;
import com.jdkgroup.interviewdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PaginationActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    public RecyclerView recyclerView;

    private boolean isLoading = false;
    private int offset = 1;
    private int recordsPerPage = 10;
    private int maxListItems = 0;
    private boolean isSearch = false;

    private List<?> alFaqData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagination);
        alFaqData = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = setRecyclerView(recyclerView, 0, recyclerViewLinearLayout);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                int totalVisibleItemCount = linearLayoutManager.getChildCount();
                int totalItemInPage = linearLayoutManager.getItemCount();

                if (!isLoading) {
                    if ((totalVisibleItemCount + firstVisibleItem) >= totalItemInPage && firstVisibleItem >= 0 && totalItemInPage < maxListItems) {
                        //TODO API CALL param is offset, recordsPerPage
                    }
                    isLoading = true;
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*public void onSuccessfullyFaq(FaqResponse faqResponse) {
        isLoading = false;
        isSearch = false;
        offset++;
        maxListItems = faqResponse.getPager().getTotalRecords();
    }*/
}
