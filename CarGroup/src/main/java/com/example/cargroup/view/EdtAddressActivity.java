package com.example.cargroup.view;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.cargroup.BR;
import com.example.cargroup.R;
import com.example.cargroup.data.entity.ShipAddress;
import com.example.cargroup.databinding.ActivityEditAddressBinding;
import com.example.cargroup.viewmodel.EdtShipAddressViewModel;
import com.example.mvvmcommon.mvvm.view.BaseActivity;

@Route(path = "/cargroup/edtshipaddressactivity")
public class EdtAddressActivity extends BaseActivity<ActivityEditAddressBinding, EdtShipAddressViewModel> {
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
        vm.addFlag = bundle.getBoolean("addFlag");
        vm.shipAddress = (ShipAddress) bundle.getSerializable("shipAddress");
        return R.layout.activity_edit_address;
    }
}
