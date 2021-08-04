package com.example.cargroup.view;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.cargroup.BR;
import com.example.cargroup.R;
import com.example.cargroup.databinding.ActivityOrderDetailBinding;
import com.example.cargroup.viewmodel.OrderDetailVieweModel;
import com.example.mvvmcommon.mvvm.view.BaseActivity;

@Route(path = "/cargroup/orderdetailactivity")
public class OrderDetailActivity extends BaseActivity<ActivityOrderDetailBinding, OrderDetailVieweModel> {
    @Override
    protected boolean isFullScreen() {
        return false;
    }

    @Override
    protected int initVariable() {
        return BR.vm;
    }

    @Override
    public int bindLayout() {
        vm.ordernum = getIntent().getExtras().getInt("ordernum");
        return R.layout.activity_order_detail;
    }
}
