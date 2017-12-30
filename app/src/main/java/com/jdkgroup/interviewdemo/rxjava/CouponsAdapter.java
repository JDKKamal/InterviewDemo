package com.jdkgroup.interviewdemo.rxjava;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.jdkgroup.interviewdemo.R;
import java.util.List;

class CouponsAdapter extends RecyclerView.Adapter<CouponsAdapter.ViewHolder> {

    private List<Coupon> couponList;
    private Context context;

    public CouponsAdapter(List<Coupon> cpnList, Context ctx) {
        couponList = cpnList;
        context = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_pagination, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Coupon coupon = couponList.get(position);
        holder.store.setText(coupon.getStore());
        holder.coupon.setText(coupon.getCoupon());
        holder.expiry.setText(coupon.getExpiryDate());
        holder.code.setText(coupon.getCouponCode());
    }

    @Override
    public int getItemCount() {
        return couponList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView store;
        public TextView coupon;
        public TextView expiry;
        public TextView code;

        public ViewHolder(View view) {
            super(view);

            store = view.findViewById(R.id.store);
            coupon = view.findViewById(R.id.coupon);
            expiry = view.findViewById(R.id.expiry);
            code = view.findViewById(R.id.coupon_code);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "You chose coupon " + getAdapterPosition(), Toast.LENGTH_LONG).show();
        }
    }
}