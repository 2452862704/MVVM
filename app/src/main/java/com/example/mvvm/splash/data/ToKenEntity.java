package com.example.mvvm.splash.data;

import com.example.networkmoudle.entity.BaseEntity;
import com.google.gson.Gson;

public class ToKenEntity extends BaseEntity {

    public String values;

    public Values getValues() {
        if (values == null)
            return null;
        return new Gson().fromJson(values,Values.class);
    }

    public static class Values{
        public String token;
    }

}
