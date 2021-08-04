package com.example.networkmoudle;

import com.example.networkmoudle.entity.BaseEntity;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class ChangeFunction<T extends BaseEntity> implements Function <T ,BaseEntity>{
    @Override
    public BaseEntity apply(@NonNull T t) throws Exception {
        return t;
    }
}
