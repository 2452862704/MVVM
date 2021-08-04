package com.example.homegroup.seckill.viewmodel;

import androidx.databinding.ObservableField;


import com.example.homegroup.seckill.data.entity.SecKillEntity;
import com.example.homegroup.seckill.model.SecKillModel;
import com.example.mvvmcommon.mvvm.viewmodel.BaseViewModel;
import com.example.networkmoudle.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

public class SecKillValueViewModel extends BaseViewModel<SecKillModel> {

    public SecKillEntity.Value value;
    public ObservableField<List<String>>bannerimgs = new ObservableField<>();

    @Override
    protected void initData() {
        List<String>banners = new ArrayList<>();
        banners.add(value.goods_default_icon);
        bannerimgs.set(banners);
    }

    @Override
    protected SecKillModel createModel() {
        return new SecKillModel();
    }

    public void finishActivity(){
        getUiChangeLiveData().getFinishActivity().setValue(""+System.currentTimeMillis());
    }

    public void share(){

    }

    public void carList(){

    }

    public void addCar(){

    }

    @Override
    protected void sucessFull(BaseEntity entity) {

    }

}
