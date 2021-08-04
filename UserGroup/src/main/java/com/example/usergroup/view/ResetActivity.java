package com.example.usergroup.view;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.mvvmcommon.mvvm.view.BaseActivity;
import com.example.usergroup.BR;
import com.example.usergroup.R;
import com.example.usergroup.databinding.ActivityResetpwdBinding;
import com.example.usergroup.viewmodel.ResetPwdViewModel;

@Route(path = "/usergroup/resetctivity")
public class ResetActivity extends BaseActivity<ActivityResetpwdBinding, ResetPwdViewModel> {
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
        return R.layout.activity_resetpwd;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle bundle = getIntent().getExtras();
        String phone = bundle.getString("phone");
        String verify = bundle.getString("verify");
        vm.phone = phone;
        vm.verify = verify;
    }
}
