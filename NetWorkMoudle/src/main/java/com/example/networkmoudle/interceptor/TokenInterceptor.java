package com.example.networkmoudle.interceptor;

import com.example.networkmoudle.utils.SpUtils;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 针对接口中token限定的拦截器，
 * 像每个接口中的请求头添加token
 * */
public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //先获取原有Request
        Request oldRequest = chain.request();
        //配置添加过token的Request
        Request.Builder build = new Request.Builder();
        build.url(oldRequest.url());
        build.headers(oldRequest.headers());
        build.addHeader("token", SpUtils.readData("token"));
        build.post(oldRequest.body());
        Request request = build.build();
        return chain.proceed(request);
    }
}
