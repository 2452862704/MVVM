package com.example.usergroup.model;


import com.example.mvvmcommon.mvvm.model.BaseModel;
import com.example.networkmoudle.ChangeFunction;
import com.example.networkmoudle.HttpFactory;
import com.example.networkmoudle.HttpType;
import com.example.networkmoudle.entity.BaseEntity;
import com.example.usergroup.data.Api;
import com.example.usergroup.data.entity.UpdateImgEntity;

import java.io.File;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class EdtUserModel extends BaseModel {

    public Observable<UpdateImgEntity>requestUploadImg(String path, String name){
        RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"),new File(path));
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart("file",name,body);
        return HttpFactory.getInstance().factory(HttpType.UPLOADTYPE)
                .getRetrofit().create(Api.class)
                .requestUploadImg(builder.build().parts());
    }

    public Observable<BaseEntity>requestEdtUser(Map<String,Object>map){
        return HttpFactory.getInstance().factory(HttpType.TOKEN)
                .getRetrofit().create(Api.class)
                .requestEdtUser(createBody(map))
                .map(new ChangeFunction());
    }

}
