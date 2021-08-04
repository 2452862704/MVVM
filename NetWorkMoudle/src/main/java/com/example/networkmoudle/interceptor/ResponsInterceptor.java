package com.example.networkmoudle.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ResponsInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        if (response.code() == 200)
            return response;
        Response.Builder builder = new Response.Builder();
        builder.request(response.request());
        builder.code(200);
        builder.message(response.message());
        builder.body(response.body());
        builder.headers(response.headers());
        builder.protocol(response.protocol());
        return builder.build();
    }
}
