package com.jdkgroup.interviewdemo.adapter;

import android.app.Activity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import com.jdkgroup.interviewdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<?> listExamData;
    private Activity activity;

    private ItemListener listener;

    public Adapter(Activity activity, List<?> listExamData) {
        this.activity = activity;
        this.listExamData = listExamData;
    }

    public void setListener(ItemListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_design_a, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listExamData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.appTvTitle)
        AppCompatTextView appTvTitle;
        @BindView(R.id.appTvDate)
        AppCompatTextView appTvDate;
        @BindView(R.id.appTvCount)
        AppCompatTextView appTvCount;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            //itemView.setOnClickListener(view -> listener.onClickCategory(listExamData.get(getAdapterPosition())));
        }
    }

    public interface ItemListener {
        //void onClickCategory(final ExamData examData);
    }

}