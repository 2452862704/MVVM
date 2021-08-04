package com.example.cargroup.model;


import com.example.cargroup.data.Api;
import com.example.cargroup.data.entity.SubCarEntity;
import com.example.mvvmcommon.mvvm.model.BaseModel;
import com.example.networkmoudle.ChangeFunction;
import com.example.networkmoudle.HttpFactory;
import com.example.networkmoudle.HttpType;
import com.example.networkmoudle.entity.BaseEntity;

import java.util.Map;

import io.reactivex.Observable;

public class OrderModel extends BaseModel {

    //购物车列表提交订单
    public Observable<SubCarEntity>requestSubCar(Map<String,Object>map){
        return HttpFactory.getInstance().factory(HttpType.TOKEN)
                .getRetrofit().create(Api.class)
                .requestSubCar(createBody(map))
                .map(new ChangeFunction());
    }

    //确认购物车生成的订单
    public Observable<BaseEntity>requestSubOrder(Map<String,Object>map){
        return HttpFactory.getInstance().factory(HttpType.TOKEN)
                .getRetrofit().create(Api.class)
                .requestSubOrder(createBody(map));
    }

    //获取支付宝签名
    public Observable<BaseEntity>requestPaySign(Map<String,Object>map){
        return HttpFactory.getInstance().factory(HttpType.TOKEN)
                .getRetrofit().create(Api.class)
                .requestPaySign(createBody(map))
                .map(new ChangeFunction());
    }

    //获取订单详情
    public Observable<BaseEntity>requestGetOrder(Map<String,Object>map){
        return HttpFactory.getInstance().factory(HttpType.TOKENSIGNTYPE)
                .getRetrofit().create(Api.class)
                .requestGetOrder(createBody(map))
                .map(new ChangeFunction());
    }

    //获取订单列表
    public Observable<BaseEntity>requestOrderList(Map<String,Object>map){
        return HttpFactory.getInstance().factory(HttpType.TOKENSIGNTYPE)
                .getRetrofit().create(Api.class)
                .requestOrderList(createBody(map))
                .map(new ChangeFunction());
    }

    //取消订单
    public Observable<BaseEntity>requestCancelOrder(Map<String,Object>map){
        return HttpFactory.getInstance().factory(HttpType.TOKENSIGNTYPE)
                .getRetrofit().create(Api.class)
                .requestCancelOrder(createBody(map));
    }
    //确认支付状态
    public Observable<BaseEntity>requestOrderPay(Map<String,Object>map){
        return HttpFactory.getInstance().factory(HttpType.TOKENSIGNTYPE)
                .getRetrofit().create(Api.class)
                .requestOrderPay(createBody(map));
    }
    //确认收货
    public Observable<BaseEntity>requestConfirm(Map<String,Object>map){
        return HttpFactory.getInstance().factory(HttpType.TOKENSIGNTYPE)
                .getRetrofit().create(Api.class)
                .requestConfirm(createBody(map));
    }
}
