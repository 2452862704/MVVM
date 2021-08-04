package com.example.cargroup.model;


import com.example.cargroup.data.Api;
import com.example.mvvmcommon.mvvm.model.BaseModel;
import com.example.networkmoudle.ChangeFunction;
import com.example.networkmoudle.HttpFactory;
import com.example.networkmoudle.HttpType;
import com.example.networkmoudle.entity.BaseEntity;

import java.util.Map;

import io.reactivex.Observable;

public class CarModel extends BaseModel {

    public Observable<BaseEntity>requestCarList(Map<String,Object>map){
        return HttpFactory.getInstance().factory(HttpType.TOKENSIGNTYPE)
                .getRetrofit().create(Api.class)
                .requestCarList(createBody(map))
                .map(new ChangeFunction());
    }

    public Observable<BaseEntity>requestSubCar(Map<String,Object>map){
        return HttpFactory.getInstance().factory(HttpType.TOKEN)
                .getRetrofit().create(Api.class)
                .requestSubCar(createBody(map))
                .map(new ChangeFunction<>());
    }

}
