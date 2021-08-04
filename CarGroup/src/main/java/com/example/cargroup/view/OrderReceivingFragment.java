package com.example.cargroup.view;


import com.example.cargroup.BR;
import com.example.cargroup.R;
import com.example.cargroup.databinding.FragmentOrderreceivingBinding;
import com.example.cargroup.viewmodel.OrderReceivingViewModel;
import com.example.mvvmcommon.mvvm.view.BaseFragment;

public class OrderReceivingFragment extends BaseFragment<FragmentOrderreceivingBinding, OrderReceivingViewModel> {
    @Override
    protected int initVariable() {
        return BR.vm;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_orderreceiving;
    }

}
