package com.example.mvvm.splash.data;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {

    @POST("sysToken/getToken")
    Observable<ToKenEntity>requestToken(@Body RequestBody body);

}
