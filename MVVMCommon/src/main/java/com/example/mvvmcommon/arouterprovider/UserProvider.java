package com.example.mvvmcommon.arouterprovider;


import com.alibaba.android.arouter.facade.template.IProvider;

public interface UserProvider extends IProvider {

    //向其他非usergroup提供userid方法
    int getUserId();

}
