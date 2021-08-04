package com.example.categorygroup.model;


import com.example.categorygroup.data.Api;
import com.example.categorygroup.data.entity.CategoryEntity;
import com.example.mvvmcommon.mvvm.model.BaseModel;
import com.example.networkmoudle.ChangeFunction;
import com.example.networkmoudle.HttpFactory;
import com.example.networkmoudle.HttpType;
import com.example.networkmoudle.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class CategoryModel extends BaseModel {

    private String[]names={"电脑办公","手机数码","男装","女装","家用电器"};

    public Observable<BaseEntity>requestCategoryValue(Map<String,Object>map){
        return HttpFactory.getInstance().factory(HttpType.TOKENSIGNTYPE)
                .getRetrofit().create(Api.class).requestCategoryValue(createBody(map))
                .map(new ChangeFunction());
    }

    public List<CategoryEntity> requestCategory(){
        List<CategoryEntity> list = new ArrayList<>();
        for (int i = 0;i < names.length;i ++){
            list.add(new CategoryEntity(i,names[i]));
        }
        list.get(0).selFlag=true;
        return list;
    }

}
