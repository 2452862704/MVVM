package com.example.messagegroup.model;


import com.example.messagegroup.data.Api;
import com.example.messagegroup.data.entity.MessageEntity;
import com.example.mvvmcommon.mvvm.model.BaseModel;
import com.example.networkmoudle.ChangeFunction;
import com.example.networkmoudle.HttpFactory;
import com.example.networkmoudle.HttpType;

import java.util.Map;

import io.reactivex.Observable;

public class MessageModel extends BaseModel {

    public Observable<MessageEntity> requestMessage(Map<String,Object>map){
        return HttpFactory.getInstance().factory(HttpType.TOKENSIGNTYPE)
                .getRetrofit().create(Api.class)
                .requestMessage(createBody(map))
                .map(new ChangeFunction());
    }

}
