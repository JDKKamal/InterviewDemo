package com.jdkgroup.interviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

import com.jdkgroup.customviews.recyclerview.BaseRecyclerView;
import com.jdkgroup.customviews.recyclerview.BaseViewHolder;
import com.jdkgroup.interviewdemo.R;
import com.jdkgroup.model.ModelMultipleSelect;

import butterknife.BindView;

public class FilterAdapter  extends BaseRecyclerView<ModelMultipleSelect> implements Filterable {

    private LayoutInflater inflater;
    private List<ModelMultipleSelect> profiles, filterList;
    private CustomFilter filter;

    public FilterAdapter(Context context, ArrayList<ModelMultipleSelect> profiles) {
        this.inflater = LayoutInflater.from(context);
        this.profiles = profiles;
        this.filterList = profiles;
    }

    @Override
    protected ModelMultipleSelect getItem(int position) {
        return profiles.get(position);
    }

    @Override
    public BaseViewHolder<ModelMultipleSelect> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProfileViewHolder(inflater.inflate(R.layout.itemview_design_a, parent, false));
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CustomFilter(filterList);
        }
        return filter;
    }

    class ProfileViewHolder extends BaseViewHolder<ModelMultipleSelect> {
        @BindView(R.id.appTvTitle)
        AppCompatTextView appTvTitle;

        public ProfileViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void populateItem(ModelMultipleSelect profile) {
            appTvTitle.setText(profile.getCategoryName() + "");
        }
    }

    public class CustomFilter extends Filter {
        List<ModelMultipleSelect> filterList;

        public CustomFilter(List<ModelMultipleSelect> filterList) {
            this.filterList = filterList;
        }

        //TODO FILTER / SEARCH
        @Override
        protected FilterResults performFiltering(CharSequence str) {
            FilterResults results = new FilterResults();

            if (str != null && str.length() > 0) {
                str = str.toString().toUpperCase();

                ArrayList<ModelMultipleSelect> filteredPlayers = new ArrayList<>();

                for (int i = 0; i < filterList.size(); i++) {
                    if (filterList.get(i).getCategoryName().toUpperCase().contains(str)) {
                        filteredPlayers.add(filterList.get(i));
                    }
                }
                results.count = filteredPlayers.size();
                results.values = filteredPlayers;
            } else {
                results.count = filterList.size();
                results.values = filterList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            profiles = (ArrayList<ModelMultipleSelect>) results.values;
            notifyDataSetChanged();
        }
    }
}
