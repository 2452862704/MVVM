package com.example.usergroup.model;


import com.example.mvvmcommon.mvvm.model.BaseModel;
import com.example.networkmoudle.HttpFactory;
import com.example.networkmoudle.HttpType;
import com.example.networkmoudle.entity.BaseEntity;
import com.example.usergroup.data.Api;

import java.util.Map;

import io.reactivex.Observable;

public class RegisterModel extends BaseModel {

    public Observable<BaseEntity>requestRegister(Map<String,Object>map){
        return HttpFactory.getInstance().factory(HttpType.TOKENSIGNTYPE)
                .getRetrofit().create(Api.class)
                .requestRegisterUser(createBody(map));
    }

}
