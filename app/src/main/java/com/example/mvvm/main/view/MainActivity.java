package com.example.mvvm.main.view;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.example.mvvm.BR;
import com.example.mvvm.R;
import com.example.mvvm.databinding.ActivityMainBinding;
import com.example.mvvm.main.viewmodel.MainViewModel;
import com.example.mvvmcommon.mvvm.view.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {
    private List<Fragment>fragments=new ArrayList<>();
    private List<String>pathes=new ArrayList<>();
    @Override
    protected boolean isFullScreen() {
        return false;
    }

    @Override
    protected int setColor() {
        return R.color.common_blue;
    }

    @Override
    protected int initVariable() {
        return BR.vm;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    //s 代表fragment路由地址
    @Override
    protected void selFragment(String s) {
        super.selFragment(s);
        LogUtils.e("---------------------------------"+s);
        Fragment fragment = (Fragment) ARouter.getInstance().build(s).navigation();
        LogUtils.e("---------------------------------"+fragment);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fg_linear,fragment).commit();
    }
}
