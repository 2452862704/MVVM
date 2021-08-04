package com.example.usergroup.viewmodel;

import androidx.databinding.ObservableField;

import com.blankj.utilcode.util.ToastUtils;
import com.example.mvvmcommon.mvvm.viewmodel.BaseViewModel;
import com.example.networkmoudle.entity.BaseEntity;
import com.example.usergroup.data.entity.UserEventEntity;
import com.example.usergroup.model.RegisterModel;
import com.example.usergroup.time.VerifyTimeHander;
import com.example.usergroup.time.VerifyTimeThread;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RegisterViewModel extends BaseViewModel<RegisterModel>
        implements VerifyTimeHander.VerifyTimeListener {

    public ObservableField<String>phone=new ObservableField<>();
    public ObservableField<String>verify=new ObservableField<>();
    public ObservableField<String>pwd=new ObservableField<>();
    public ObservableField<String>confirmpwd=new ObservableField<>();
    public ObservableField<String>timeStr = new ObservableField<>("获取验证码");
    private boolean timeflag = true;
    private VerifyTimeHander hander;

    @Override
    protected void initData() {
        hander = new VerifyTimeHander(this);
        hander.initTime();
    }

    @Override
    protected RegisterModel createModel() {
        return new RegisterModel();
    }

    @Override
    protected void sucessFull(BaseEntity entity) {
        if (entity.getMessage().equals("注册成功")){
            //更新登录数据
            UserEventEntity userEventEntity = new UserEventEntity();
            userEventEntity.phone = phone.get();
            userEventEntity.pwd = pwd.get();
            userEventEntity.action = "update";
            EventBus.getDefault().post(userEventEntity);
            getUiChangeLiveData().getFinishActivity().setValue(""+System.currentTimeMillis());
        }else
            ToastUtils.showShort(entity.getMessage());
    }

    public void finish(){
        getUiChangeLiveData().getFinishActivity().setValue(""+System.currentTimeMillis());
    }

    public void verify(){
        if (timeflag){
            hander.initTime();
            timeflag = false;
            new VerifyTimeThread(hander).start();
        }
    }

    public void confirm(){
        if (phone.get().isEmpty()){
            ToastUtils.showShort("请输入手机号");
            return;
        }
        if (pwd.get().isEmpty()){
            ToastUtils.showShort("请输入密码");
            return;
        }
        if (verify.get().isEmpty()){
            ToastUtils.showShort("请输入验证码");
            return;
        }
        if (confirmpwd.get().isEmpty()){
            ToastUtils.showShort("请输入确认密码");
            return;
        }
        if (!confirmpwd.get().equals(pwd.get())){
            ToastUtils.showShort("密码不符");
            return;
        }
        Map<String,Object>map = new HashMap<>();
        map.put("mobile",phone.get());
        map.put("pwd",pwd.get());
        map.put("verifyCode",verify.get());
        m.requestRegister(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
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
