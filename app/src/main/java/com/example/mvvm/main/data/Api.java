package com.example.mvvm.main.data;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {

    String IMAGEFILE = "http://118.195.161.134:8066/fileDownload?fileName=";

    @POST("userCenter/getConfig")
    Observable<BottomConfigEntity>requestBottomConfig(
            @Body RequestBody body);
}
