package com.example.categorygroup.data;


import com.example.categorygroup.data.entity.CategoryValueEntity;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {
    @POST("category/getCategory")
    Observable<CategoryValueEntity> requestCategoryValue(@Body RequestBody body);
}
