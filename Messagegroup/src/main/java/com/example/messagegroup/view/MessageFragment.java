package com.example.messagegroup.view;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.messagegroup.BR;
import com.example.messagegroup.R;
import com.example.messagegroup.databinding.FragmentMsgBinding;
import com.example.messagegroup.viewmodel.MessageViewModdel;
import com.example.mvvmcommon.mvvm.view.BaseFragment;

@Route(path = "/messagegroup/msgfragment")
public class MessageFragment extends BaseFragment<FragmentMsgBinding, MessageViewModdel> {

    @Override
    protected int initVariable() {
        return BR.vm;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_msg;
    }
}
