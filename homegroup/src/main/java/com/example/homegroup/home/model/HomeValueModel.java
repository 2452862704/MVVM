package com.example.homegroup.home.model;


import com.example.homegroup.home.data.Api;
import com.example.mvvmcommon.mvvm.model.BaseModel;
import com.example.networkmoudle.ChangeFunction;
import com.example.networkmoudle.HttpFactory;
import com.example.networkmoudle.HttpType;
import com.example.networkmoudle.entity.BaseEntity;

import java.util.Map;

import io.reactivex.Observable;

public class HomeValueModel extends BaseModel {

    public Observable<BaseEntity>requestAddCar(Map<String,Object>map){
        return HttpFactory.getInstance().factory(HttpType.TOKEN)
                .getRetrofit().create(Api.class)
                .requestAddCar(createBody(map));
    }

    public Observable<BaseEntity>requestGoods(Map<String,Object>map){
        return HttpFactory.getInstance().factory(HttpType.TOKENSIGNTYPE)
                .getRetrofit().create(Api.class)
                .requestHomeGoods(createBody(map))
                .map(new ChangeFunction());
    }

}
