package com.example.messagegroup.data;


import com.example.messagegroup.data.entity.MessageEntity;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {

    @POST("msg/getList")
    Observable<MessageEntity>requestMessage(@Body RequestBody body);
}
