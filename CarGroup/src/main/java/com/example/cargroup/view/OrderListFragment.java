package com.example.cargroup.view;


import com.example.cargroup.BR;
import com.example.cargroup.R;
import com.example.cargroup.databinding.FragmentOrderlistBinding;
import com.example.cargroup.viewmodel.OrderListViewModel;
import com.example.mvvmcommon.mvvm.view.BaseFragment;

public class OrderListFragment extends BaseFragment<FragmentOrderlistBinding, OrderListViewModel> {
    @Override
    protected int initVariable() {
        return BR.vm;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_orderlist;
    }

    @Override
    public void onResume() {
        super.onResume();
        vm.refresh();
    }
}
