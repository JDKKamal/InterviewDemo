package com.jdkgroup.interviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;

import com.jdkgroup.interviewdemo.R;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.Holder> implements Filterable {

    private Context context;
    private ArrayList<String> listDeveloper, filterList;
    private CustomFilter filter;

    public FilterAdapter(Context context, ArrayList<String> listDeveloper) {
        this.context = context;
        this.listDeveloper = listDeveloper;
        this.filterList = listDeveloper;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_design_a, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return listDeveloper.size();
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CustomFilter(filterList);
        }
        return filter;
    }

    public class Holder extends RecyclerView.ViewHolder {

        public Holder(View itemView) {
            super(itemView);
        }
    }

    public class CustomFilter extends Filter {
        ArrayList<String> filterList;

        public CustomFilter(ArrayList<String> filterList) {
            this.filterList = filterList;
        }

        //TODO FILTER / SEARCH
        @Override
        protected FilterResults performFiltering(CharSequence str) {
            FilterResults results = new FilterResults();

            if (str != null && str.length() > 0) {
                str = str.toString().toUpperCase();

                ArrayList<String> filteredPlayers = new ArrayList<>();

                for (int i = 0; i < filterList.size(); i++) {
                    if (filterList.get(i).toUpperCase().contains(str)) {
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
            listDeveloper = (ArrayList<String>) results.values;
            notifyDataSetChanged();
        }
    }
}
