package com.example.homegroup.seckill.model;


import com.example.homegroup.seckill.data.Api;
import com.example.homegroup.seckill.data.entity.SecKillEntity;
import com.example.mvvmcommon.mvvm.model.BaseModel;
import com.example.networkmoudle.ChangeFunction;
import com.example.networkmoudle.HttpFactory;
import com.example.networkmoudle.HttpType;
import com.example.networkmoudle.entity.BaseEntity;

import java.util.Map;

import io.reactivex.Observable;

public class SecKillModel extends BaseModel {

    //获取秒杀列表
    public Observable<BaseEntity> requestSecKilles(){
        return HttpFactory.getInstance().factory(HttpType.TOKEN)
                .getRetrofit().create(Api.class)
                .requestSecKilles().map(new ChangeFunction<SecKillEntity>());
    }
    //秒杀
    public Observable<BaseEntity>requestAddSecKill(Map<String,Object>map){
        return HttpFactory.getInstance().factory(HttpType.TOKEN)
                .getRetrofit().create(Api.class)
                .requestAddSecKill(createBody(map));
    }
}
