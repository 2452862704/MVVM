package com.example.usergroup.viewmodel;

import android.os.Bundle;

import androidx.databinding.ObservableField;

import com.blankj.utilcode.util.ToastUtils;
import com.example.mvvmcommon.field.UIField;
import com.example.mvvmcommon.mvvm.viewmodel.BaseViewModel;
import com.example.networkmoudle.entity.BaseEntity;
import com.example.usergroup.data.entity.UserEventEntity;
import com.example.usergroup.model.ForgetPwdModel;
import com.example.usergroup.time.VerifyTimeHander;
import com.example.usergroup.time.VerifyTimeThread;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

public class ForgetPwdViewModel extends BaseViewModel<ForgetPwdModel>
        implements VerifyTimeHander.VerifyTimeListener{

    public ObservableField<String>phone=new ObservableField<>();
    public ObservableField<String>verify = new ObservableField<>();
    public ObservableField<String>timeStr = new ObservableField<>("获取验证码");
    private boolean timeflag = true;
    private VerifyTimeHander hander;

    @Override
    protected void initData() {
        hander = new VerifyTimeHander(this);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void update(UserEventEntity entity){
        if (entity.action.equals("finish")) {
            finish();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected ForgetPwdModel createModel() {
        return new ForgetPwdModel();
    }

    @Override
    protected void sucessFull(BaseEntity entity) {
    }

    public void finish(){
        getUiChangeLiveData().getFinishActivity().setValue(""+System.currentTimeMillis());
    }

    public void next(){
        if (phone.get().isEmpty()){
            ToastUtils.showShort("请先输入手机号");
            return;
        }
        if (verify.get().isEmpty()){
            ToastUtils.showShort("请先输入验证码");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("phone",phone.get());
        bundle.putString("verify",verify.get());
        Map<String,Object> map= new HashMap<>();
        map.put(UIField.ACTIONTYPE, UIField.AROUTERSTART);
        map.put(UIField.ACTIONROUTERKEY,"/usergroup/resetctivity");
        map.put(UIField.VALUESKEY,bundle);
        getUiChangeLiveData().getStartActivity().setValue(map);
    }

    public void verify(){
        if (timeflag){
            hander.initTime();
            timeflag = false;
            new VerifyTimeThread(hander).start();
        }

    }

    @Override
    public void timeCall(int time) {
        if (time == 0) {
            timeflag = true;
            timeStr.set("获取验证码");
            return;
        }
        timeStr.set(""+time+"s");
    }
}
