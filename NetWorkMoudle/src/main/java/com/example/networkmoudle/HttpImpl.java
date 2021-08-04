package com.example.networkmoudle;

import retrofit2.Retrofit;

/**
 * 接口工厂中公共获取retrofit对象入口
 * */
public interface HttpImpl {
    Retrofit getRetrofit();
}
