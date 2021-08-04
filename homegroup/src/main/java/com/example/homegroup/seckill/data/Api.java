package com.example.homegroup.seckill.data;

import com.example.homegroup.seckill.data.entity.SecKillEntity;
import com.example.networkmoudle.entity.BaseEntity;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {

    @POST("secKill/seckilles")
    Observable<SecKillEntity>requestSecKilles();

    @POST("secKill/seckill")
    Observable<BaseEntity>requestAddSecKill(@Body RequestBody body);

}
