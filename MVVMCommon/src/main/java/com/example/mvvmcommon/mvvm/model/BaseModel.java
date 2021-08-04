package com.example.mvvmcommon.mvvm.model;

import com.google.gson.Gson;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public abstract class BaseModel implements IModel{
    @Override
    public RequestBody createBody(Map<String, Object> map) {
        return RequestBody.create(MediaType.parse("application/json"),
                new Gson().toJson(map));
    }
}
