package com.jdkgroup.interviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jdkgroup.interviewdemo.R;
import com.jdkgroup.model.ModelMultipleSelect;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MultipleSelectAdapter extends RecyclerView.Adapter<MultipleSelectAdapter.ViewHolder> {
    private Context context;
    private List<ModelMultipleSelect> listMultipleSelect;
    private OnCheckedChange onCheckedChange;


    public MultipleSelectAdapter(Context context, List<ModelMultipleSelect> listMultipleSelect, OnCheckedChange onCheckedChange) {
        this.context = context;
        this.listMultipleSelect = listMultipleSelect;
        this.onCheckedChange = onCheckedChange;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_multiple_select, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.cbImage.setText(listMultipleSelect.get(position).getCategoryName());
        holder.cbImage.setId(position);

        holder.cbImage.setChecked(listMultipleSelect.get(position).isChecked());

        /*if(list.get(position).isChecked()) {
            holder.cbImage.setTextColor(ContextCompat.getColor(context, R.color.black));
            holder.cbImage.setChecked(true);
        }
        else {
            holder.cbImage.setTextColor(ContextCompat.getColor(context, R.color.black));
            holder.cbImage.setChecked(false);
        }*/
    }

    @Override
    public int getItemCount() {
        return listMultipleSelect.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cbImage)
        CheckBox cbImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            cbImage.setOnCheckedChangeListener((buttonView, isChecked) -> {
                        //holder.cbImage.getId();
                        listMultipleSelect.get(cbImage.getId()).setChecked(isChecked);
                        if (isChecked)
                           cbImage.setChecked(true);
                        else
                            cbImage.setChecked(false);
                        onCheckedChange.onLoadedList(listMultipleSelect);
                    }
            );
        }
    }

    public void resetData() {
        for (ModelMultipleSelect c : listMultipleSelect) {
            c.setChecked(false);
        }
        notifyDataSetChanged();
    }

    public interface OnCheckedChange {
         void onLoadedList(List<ModelMultipleSelect> categories);
    }

}