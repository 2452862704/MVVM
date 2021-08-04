package com.example.cargroup.viewmodel;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.cargroup.adapter.ConfirmOrderAdapter;
import com.example.cargroup.data.entity.OrderEntity;
import com.example.cargroup.data.entity.OrderGoods;
import com.example.cargroup.model.OrderModel;
import com.example.mvvmcommon.field.UIField;
import com.example.mvvmcommon.mvvm.viewmodel.BaseViewModel;
import com.example.networkmoudle.entity.BaseEntity;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class OrderDetailVieweModel extends BaseViewModel<OrderModel>
        implements  OnItemClickListener {

    public int ordernum = 0;
    public ConfirmOrderAdapter adapter = new ConfirmOrderAdapter();
    private List<OrderGoods> list;

    @Override
    protected void initData() {
        adapter.setOnItemClickListener(this);
        Map<String,Object> map = new HashMap<>();
        map.put("orderId",ordernum);
        m.requestGetOrder(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    protected OrderModel createModel() {
        return new OrderModel();
    }

    @Override
    protected void sucessFull(BaseEntity entity) {
        if (entity instanceof OrderEntity){
            OrderEntity orderEntity = (OrderEntity) entity;
            list = orderEntity.data.orderGoodsList;
            LogUtils.e("ConfirmOrderViewModel:"+list.size());
            if (list == null)
                return;
            if (list.size() == 0)
                return;
            adapter.setNewInstance(list);
        }
    }

    public void finish() {
         getUiChangeLiveData().getFinishActivity().setValue(""+System.currentTimeMillis());
    }

    @Override
    public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {
        //跳转到商品详情
        ConfirmOrderAdapter confirmOrderAdapter = (ConfirmOrderAdapter) adapter;
        Bundle bundle = new Bundle();
        bundle.putString("img",confirmOrderAdapter.getItem(position).goods_icon);
        bundle.putString("title",confirmOrderAdapter.getItem(position).goods_desc);
        bundle.putString("dec",confirmOrderAdapter.getItem(position).goods_sku);
        bundle.putInt("id",confirmOrderAdapter.getItem(position).goods_id);
        bundle.putString("price",confirmOrderAdapter.getItem(position).goods_price);
        Map<String,Object>map = new HashMap<>();
        map.put(UIField.ACTIONTYPE, UIField.AROUTERSTART);
        map.put(UIField.ACTIONROUTERKEY,"/homegroup/homevalueactivity");
        map.put(UIField.VALUESKEY,bundle);
        getUiChangeLiveData().getStartActivity().setValue(map);
    }
}
