package com.jdkgroup.interviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jdkgroup.customviews.recyclerview.BaseRecyclerView;
import com.jdkgroup.customviews.recyclerview.BaseViewHolder;
import com.jdkgroup.interviewdemo.R;
import com.jdkgroup.model.ModelProduct;
import java.util.List;

import butterknife.BindView;

public class OrderAdapter extends BaseRecyclerView<ModelProduct> {

    private LayoutInflater inflater;

    private Context context;
    private List<ModelProduct> profiles;

    private OnCartItemChange onCartItemChange;

    public OrderAdapter(Context context, List<ModelProduct> profiles, OnCartItemChange onCartItemChange) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.profiles = profiles;
        this.onCartItemChange = onCartItemChange;
    }

    @Override
    protected ModelProduct getItem(int position) {
        return profiles.get(position);
    }

    @Override
    public BaseViewHolder<ModelProduct> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.itemview_order, parent, false));
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class ViewHolder extends BaseViewHolder<ModelProduct> {
        @BindView(R.id.appTvProductName)
        AppCompatTextView appTvProductName;
        @BindView(R.id.ivDelete)
        AppCompatImageView removeProduct;
        @BindView(R.id.ivMinusQty)
        public AppCompatImageView ivMinus;
        @BindView(R.id.ivPlusQty)
        AppCompatImageView ivPlus;
        @BindView(R.id.tvPrice)
        public AppCompatTextView price;
        @BindView(R.id.tvQty)
        public AppCompatTextView quantity;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void populateItem(ModelProduct profile) {
            appTvProductName.setText(profile.getName() + "");
            price.setText(profile.getPrice() + "");
            quantity.setText(profile.getQuantity() + "");

            ivMinus.setOnClickListener(v -> {
                String strQty = quantity.getText().toString();
                int qty = 1;
                try {
                    qty = Integer.parseInt(strQty);
                    if (qty > 1)
                        qty--;
                } catch (Exception e) {
                }
                quantity.setText(qty + "");
                profiles.get(getAdapterPosition()).setQuantity(qty);
                updateData();
            });

            ivPlus.setOnClickListener(v -> {
                String strQty = quantity.getText().toString();
                int qty = 0;
                try {
                    qty = Integer.parseInt(strQty);
                } catch (Exception e) {
                }
                qty++;
                quantity.setText(qty + "");
                profiles.get(getAdapterPosition()).setQuantity(qty);
                updateData();
            });

            removeProduct.setOnClickListener(view -> {
                profiles.get(getAdapterPosition()).getQuantity();
                profiles.remove(profiles.get(getAdapterPosition()));
                notifyDataSetChanged();
                updateData();
            });
        }
    }

    public void updateData() {
        double grandTotal = 0.00;
        for (ModelProduct product : profiles) {
            try {
                grandTotal += (product.getQuantity() * product.getPrice());
            } catch (Exception e) {
            }
        }
        if (onCartItemChange != null)
            onCartItemChange.onCartChange(grandTotal, profiles);
    }

    public interface OnCartItemChange{
        void onCartChange(double grandTotal, List<ModelProduct> cartItems);
    }
}
