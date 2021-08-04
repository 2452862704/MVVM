package com.example.homegroup.home.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.homegroup.BR;
import com.example.homegroup.R;
import com.example.homegroup.databinding.ActivityHomevalueBinding;
import com.example.homegroup.home.data.entity.HomeGoodsEntity;
import com.example.homegroup.home.viewmodel.HomeValueViewModel;
import com.example.mvvmcommon.mvvm.view.BaseActivity;

@Route(path = "/homegroup/homevalueactivity")
public class HomeValueActivity extends BaseActivity<ActivityHomevalueBinding, HomeValueViewModel> {

    @Override
    protected boolean isFullScreen() {
        return false;
    }

    @Override
    protected int initVariable() {
        return BR.vm;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_homevalue;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        HomeGoodsEntity.DataBean dataBean = new HomeGoodsEntity.DataBean();
        dataBean.setGoods_default_icon(bundle.getString("img"));
        dataBean.setGoods_desc(bundle.getString("title"));
        dataBean.setGoods_default_sku(bundle.getString("dec"));
        dataBean.setGoods_default_price(bundle.getString("price"));
        dataBean.setId(bundle.getInt("id"));
        vm.entity.set(dataBean);
    }

    //当使用非默认启动模式
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //由于为栈顶复用activity在自己启动自己时不会进入生命周期oncreate方法
        //可以进入其他声明周期方法以及onNewIntent方法->自启动时使用intent传递参数
        Bundle bundle = intent.getExtras();
        HomeGoodsEntity.DataBean dataBean = new HomeGoodsEntity.DataBean();
        dataBean.setGoods_default_icon(bundle.getString("img"));
        dataBean.setGoods_desc(bundle.getString("title"));
        dataBean.setGoods_default_sku(bundle.getString("dec"));
        dataBean.setGoods_default_price(bundle.getString("price"));
        dataBean.setId(bundle.getInt("id"));
        vm.entity.set(dataBean);
    }
}
