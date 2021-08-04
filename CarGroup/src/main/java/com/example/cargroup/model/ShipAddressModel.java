package com.example.cargroup.model;


import com.example.cargroup.data.Api;
import com.example.mvvmcommon.mvvm.model.BaseModel;
import com.example.networkmoudle.ChangeFunction;
import com.example.networkmoudle.HttpFactory;
import com.example.networkmoudle.HttpType;
import com.example.networkmoudle.entity.BaseEntity;

import java.util.Map;

import io.reactivex.Observable;

public class ShipAddressModel extends BaseModel {

    //添加
    public Observable<BaseEntity>requestAddAddress(Map<String,Object>map){
        return HttpFactory.getInstance().factory(HttpType.TOKEN)
                .getRetrofit().create(Api.class)
                .requestAddAddress(createBody(map));
    }

    //列表
    public Observable<BaseEntity>requestAddressList(Map<String,Object>map){
        return HttpFactory.getInstance().factory(HttpType.TOKENSIGNTYPE)
                .getRetrofit().create(Api.class)
                .requestAddressList(createBody(map))
                .map(new ChangeFunction());
    }

    //修改
    public Observable<BaseEntity>requestUpdateAddress(Map<String,Object>map){
        return HttpFactory.getInstance().factory(HttpType.TOKEN)
                .getRetrofit().create(Api.class)
                .requestUpdateAddress(createBody(map));
    }

    //删除
    public Observable<BaseEntity>requestDelAddress(Map<String,Object>map){
        return HttpFactory.getInstance().factory(HttpType.TOKENSIGNTYPE)
                .getRetrofit().create(Api.class)
                .requestDelAddress(createBody(map));
    }

}
