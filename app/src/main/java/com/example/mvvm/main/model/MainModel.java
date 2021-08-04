package com.example.mvvm.main.model;


import com.example.mvvm.main.data.Api;
import com.example.mvvm.main.data.BottomConfigEntity;
import com.example.mvvmcommon.mvvm.model.BaseModel;
import com.example.networkmoudle.ChangeFunction;
import com.example.networkmoudle.HttpFactory;
import com.example.networkmoudle.HttpType;
import com.example.networkmoudle.entity.BaseEntity;

import java.util.Map;

import io.reactivex.Observable;

public class MainModel extends BaseModel {

    public Observable<BaseEntity>requestBottomConfig(Map<String,Object> map){
        return HttpFactory.getInstance().factory(HttpType.TOKENSIGNTYPE)
                .getRetrofit().create(Api.class).requestBottomConfig(createBody(map))
                .map(new ChangeFunction<BottomConfigEntity>());
    }

}
