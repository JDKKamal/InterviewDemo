package com.jdkgroup.interviewdemo.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.jdkgroup.baseclass.BaseActivity;
import com.jdkgroup.interviewdemo.R;
import com.jdkgroup.interviewdemo.adapter.OrderAdapter;
import com.jdkgroup.model.ModelProduct;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class OrderActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        bindViews();

        setRecyclerView(recyclerView, 0, recyclerViewLinearLayout);

        OrderAdapter adapter = new OrderAdapter(this, getListProduct(), new OrderAdapter.OnCartItemChange() {
            @Override
            public void onCartChange(double grandTotal, List<ModelProduct> cartItems) {
                System.out.println("Tag" + grandTotal);
            }
        });
        recyclerView.setAdapter(adapter);

    }

    private List<ModelProduct> getListProduct() {
        List<ModelProduct> listProduct = new ArrayList<>();
        listProduct.add(new ModelProduct(1, "Name 1", 1, 10));
        listProduct.add(new ModelProduct(2, "Name 2", 1, 20));
        listProduct.add(new ModelProduct(3, "Name 3", 1, 10));
        listProduct.add(new ModelProduct(4, "Name 4", 1, 10));
        listProduct.add(new ModelProduct(5, "Name 5", 1, 30));
        listProduct.add(new ModelProduct(6, "Name 6", 1, 10));
        listProduct.add(new ModelProduct(7, "Name 7", 1, 10));
        listProduct.add(new ModelProduct(8, "Name 8", 1, 40));
        listProduct.add(new ModelProduct(8, "Name 9", 1, 10));
        listProduct.add(new ModelProduct(10, "Name 10", 1, 10));

        return listProduct;
    }

}
