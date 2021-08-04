package com.example.cargroup.view;


import com.example.cargroup.BR;
import com.example.cargroup.R;
import com.example.cargroup.databinding.FragmentOrderpayBinding;
import com.example.cargroup.viewmodel.OrderPayViewModel;
import com.example.mvvmcommon.mvvm.view.BaseFragment;

public class OrderPayFragment extends BaseFragment<FragmentOrderpayBinding, OrderPayViewModel> {
    @Override
    protected int initVariable() {
        return BR.vm;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_orderpay;
    }

}
