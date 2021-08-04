package com.example.usergroup.view;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.mvvmcommon.mvvm.view.BaseFragment;
import com.example.usergroup.BR;
import com.example.usergroup.R;
import com.example.usergroup.databinding.FragmentUserBinding;
import com.example.usergroup.viewmodel.UserViewModel;

@Route(path = "/usergroup/userfragment")
public class UserFragment extends BaseFragment<FragmentUserBinding, UserViewModel> {
    @Override
    protected int initVariable() {
        return BR.vm;
    }



    @Override
    public int bindLayout() {
        return R.layout.fragment_user;
    }
}
