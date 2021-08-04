package com.example.mvvmcommon.mvvm.model;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * MVVM框架中Model层顶层接口
 * */
public interface IModel {
    RequestBody createBody(Map<String,Object>map);
}
