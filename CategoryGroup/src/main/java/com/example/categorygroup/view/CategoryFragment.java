package com.example.categorygroup.view;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.categorygroup.BR;
import com.example.categorygroup.R;
import com.example.categorygroup.databinding.FragmentCategoryBinding;
import com.example.categorygroup.viewmodel.CategoryViewModel;
import com.example.mvvmcommon.mvvm.view.BaseFragment;

@Route(path = "/categorygroup/categoryfragment")
public class CategoryFragment extends BaseFragment<FragmentCategoryBinding, CategoryViewModel> {
    @Override
    protected int initVariable() {
        return BR.vm;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_category;
    }
}
