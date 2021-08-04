package com.example.homegroup.home.view;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.homegroup.BR;
import com.example.homegroup.R;
import com.example.homegroup.databinding.FragmentHomeBinding;
import com.example.homegroup.home.viewmodel.HomeViewModel;
import com.example.mvvmcommon.mvvm.view.BaseFragment;


@Route(path = "/homegroup/homefragment")
public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {
    @Override
    protected int initVariable() {
        return BR.vm;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_home;
    }
}
