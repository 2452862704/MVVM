package com.example.mvvm.splash.viewmodel;


import androidx.databinding.ObservableField;

import com.blankj.utilcode.util.LogUtils;
import com.example.mvvm.main.view.MainActivity;
import com.example.mvvm.splash.data.ToKenEntity;
import com.example.mvvm.splash.model.SplashModel;
import com.example.mvvm.splash.timmer.TimmerHandler;
import com.example.mvvm.splash.timmer.TimmerThread;
import com.example.mvvmcommon.field.UIField;
import com.example.mvvmcommon.mvvm.viewmodel.BaseViewModel;
import com.example.networkmoudle.entity.BaseEntity;
import com.example.networkmoudle.utils.SpUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 启屏页ViewModel
 * */
public class SplashViewModel extends BaseViewModel<SplashModel> implements TimmerHandler.TimmerCallBack {

    public ObservableField<String>time=new ObservableField<>();

    @Override
    protected void initData() {
        Map<String,Object>map = new HashMap<>();
        map.put("imie",""+System.currentTimeMillis());
        m.requestToken(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    protected SplashModel createModel() {
        return new SplashModel();
    }

    @Override
    protected void sucessFull(BaseEntity entity) {
        if (entity instanceof ToKenEntity){
            ToKenEntity toKenEntity = (ToKenEntity) entity;
            LogUtils.e("SplashModel:"+toKenEntity.getValues().token);
            SpUtils.saveData("token",toKenEntity.getValues().token);
            //开启定时器
            new TimmerThread(new TimmerHandler(this)).start();
        }else
            getUiChangeLiveData().getFinishActivity().setValue(""+System.currentTimeMillis());
    }

    @Override
    public void callTimmer(int second) {
        time.set(""+second);
        if (second == 1){
            Map<String,Object>map = new HashMap<>();
            map.put(UIField.ACTIONTYPE,UIField.NATIVESTART);
            map.put(UIField.NATIVEACTION, MainActivity.class);
            getUiChangeLiveData().getStartActivity().setValue(map);
            getUiChangeLiveData().getFinishActivity().setValue(""+System.currentTimeMillis());
        }
    }
}
