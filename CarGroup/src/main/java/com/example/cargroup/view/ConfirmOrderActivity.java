package com.example.cargroup.view;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.cargroup.BR;
import com.example.cargroup.R;
import com.example.cargroup.databinding.ActivityConfirmOrderBinding;
import com.example.cargroup.viewmodel.ConfirmOrderViewModel;
import com.example.mvvmcommon.mvvm.view.BaseActivity;

@Route(path = "/cargroup/confirmorderactivity")
public class ConfirmOrderActivity extends BaseActivity<ActivityConfirmOrderBinding, ConfirmOrderViewModel> {

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
        Bundle bundle = getIntent().getExtras();
        int orderId = bundle.getInt("orderId");
        vm.ordernum = orderId;
        return R.layout.activity_confirm_order;
    }
}
