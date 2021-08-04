package com.example.homegroup.home.data;
;

import com.example.homegroup.home.data.entity.HomeGoodsEntity;
import com.example.networkmoudle.entity.BaseEntity;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {

    @POST("goods/getGoodsList")
    Observable<HomeGoodsEntity>requestHomeGoods(@Body RequestBody body);

    @POST("cart/add")
    Observable<BaseEntity>requestAddCar(@Body RequestBody body);
}
