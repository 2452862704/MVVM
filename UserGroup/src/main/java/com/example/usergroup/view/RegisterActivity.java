package com.example.usergroup.view;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.mvvmcommon.mvvm.view.BaseActivity;
import com.example.usergroup.BR;
import com.example.usergroup.R;
import com.example.usergroup.databinding.ActivityRegisterBinding;
import com.example.usergroup.viewmodel.RegisterViewModel;

@Route(path = "/usergroup/registeractivity")
public class RegisterActivity extends BaseActivity<ActivityRegisterBinding, RegisterViewModel> {
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
        return R.layout.activity_register;
    }
}
