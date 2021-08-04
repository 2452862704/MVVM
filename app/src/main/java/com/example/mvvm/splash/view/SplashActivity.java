package com.example.mvvm.splash.view;

import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.mvvm.BR;
import com.example.mvvm.R;
import com.example.mvvm.databinding.ActivitySplashBinding;
import com.example.mvvm.splash.viewmodel.SplashViewModel;
import com.example.mvvmcommon.mvvm.view.BaseActivity;

public class SplashActivity extends BaseActivity<ActivitySplashBinding,
        SplashViewModel> {
    @Override
    protected int initVariable() {
        return BR.vm;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_splash;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected boolean isFullScreen() {
        return true;
    }
}
