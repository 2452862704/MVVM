package com.example.usergroup.data;


import com.example.networkmoudle.entity.BaseEntity;
import com.example.usergroup.data.entity.UpdateImgEntity;
import com.example.usergroup.data.entity.UserEntity;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Api {

    @POST("userCenter/editUser")
    Observable<UserEntity>requestEdtUser(@Body RequestBody body);

    @Multipart
    @POST("fileUpload")
    Observable<UpdateImgEntity>requestUploadImg(@Part List<MultipartBody.Part>list);

    @POST("userCenter/login")
    Observable<UserEntity>requestLogin(@Body RequestBody body);

    @POST("userCenter/register")
    Observable<BaseEntity>requestRegisterUser(@Body RequestBody body);

    @POST("userCenter/resetPwd")
    Observable<BaseEntity>requestResetPwdrUser(@Body RequestBody body);

    @POST("userCenter/forgetPwd")
    Observable<BaseEntity>requestForgetPwdUser(@Body RequestBody body);

}
