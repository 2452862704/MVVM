package com.example.cargroup.view;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.cargroup.BR;
import com.example.cargroup.R;
import com.example.cargroup.databinding.FragmentCarBinding;
import com.example.cargroup.viewmodel.CarViewModel;
import com.example.mvvmcommon.mvvm.view.BaseFragment;

@Route(path = "/cargroup/carfragment")
public class CarFragment extends BaseFragment<FragmentCarBinding, CarViewModel> {

    @Override
    protected int initVariable() {
        return BR.vm;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_car;
    }
}
