package com.example.cargroup.view;


import com.example.cargroup.BR;
import com.example.cargroup.R;
import com.example.cargroup.databinding.FragmentOrdercanelBinding;
import com.example.cargroup.viewmodel.OrderCanelViewModel;
import com.example.mvvmcommon.mvvm.view.BaseFragment;

public class OrderCanelFragment extends BaseFragment<FragmentOrdercanelBinding, OrderCanelViewModel> {
    @Override
    protected int initVariable() {
        return BR.vm;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_ordercanel;
    }

}
