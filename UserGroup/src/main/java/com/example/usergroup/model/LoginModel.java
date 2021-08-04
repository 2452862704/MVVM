package com.example.usergroup.model;


import com.example.mvvmcommon.mvvm.model.BaseModel;
import com.example.networkmoudle.ChangeFunction;
import com.example.networkmoudle.HttpFactory;
import com.example.networkmoudle.HttpType;
import com.example.networkmoudle.entity.BaseEntity;
import com.example.usergroup.data.Api;

import java.util.Map;

import io.reactivex.Observable;

public class LoginModel extends BaseModel {

    public Observable<BaseEntity>requestLogin(Map<String,Object>map){
        return HttpFactory.getInstance().factory(HttpType.TOKENSIGNTYPE)
                .getRetrofit().create(Api.class)
                .requestLogin(createBody(map))
                .map(new ChangeFunction());
    }

}
