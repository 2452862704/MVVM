package com.example.mvvm.splash.model;

import com.example.mvvm.splash.data.Api;
import com.example.mvvm.splash.data.ToKenEntity;
import com.example.mvvmcommon.mvvm.model.BaseModel;
import com.example.networkmoudle.ChangeFunction;
import com.example.networkmoudle.HttpFactory;
import com.example.networkmoudle.HttpType;
import com.example.networkmoudle.entity.BaseEntity;

import java.util.Map;

import io.reactivex.Observable;

public class SplashModel extends BaseModel {

    public Observable<BaseEntity>requestToken(Map<String,Object>map){
        return HttpFactory.getInstance().factory(HttpType.SIGNTYPE)
                .getRetrofit().create(Api.class)
                .requestToken(createBody(map))
                .map(new ChangeFunction<ToKenEntity>());
    }

}
