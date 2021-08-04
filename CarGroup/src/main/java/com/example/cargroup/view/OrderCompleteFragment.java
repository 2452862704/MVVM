package com.example.cargroup.view;

import com.example.cargroup.BR;
import com.example.cargroup.R;
import com.example.cargroup.databinding.FragmentOrdercompleteBinding;
import com.example.cargroup.viewmodel.OrderCompleteViewModel;
import com.example.mvvmcommon.mvvm.view.BaseFragment;

public class OrderCompleteFragment extends BaseFragment<FragmentOrdercompleteBinding, OrderCompleteViewModel> {
    @Override
    protected int initVariable() {
        return BR.vm;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_ordercomplete;
    }

}
