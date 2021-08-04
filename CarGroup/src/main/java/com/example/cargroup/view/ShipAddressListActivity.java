package com.example.cargroup.view;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.cargroup.BR;
import com.example.cargroup.R;
import com.example.cargroup.databinding.ActivityAddressBinding;
import com.example.cargroup.viewmodel.ShipAddressListViewModel;
import com.example.mvvmcommon.mvvm.view.BaseActivity;

@Route(path = "/cargroup/shipaddressactivity")
public class ShipAddressListActivity extends BaseActivity<ActivityAddressBinding, ShipAddressListViewModel> {
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
        return R.layout.activity_address;
    }
}
