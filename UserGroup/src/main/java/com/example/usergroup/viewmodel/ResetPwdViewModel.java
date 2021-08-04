package com.example.usergroup.viewmodel;

import androidx.databinding.ObservableField;

import com.blankj.utilcode.util.ToastUtils;
import com.example.mvvmcommon.mvvm.viewmodel.BaseViewModel;
import com.example.networkmoudle.entity.BaseEntity;
import com.example.usergroup.data.entity.UserEventEntity;
import com.example.usergroup.model.ResetPwdModel;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ResetPwdViewModel extends BaseViewModel<ResetPwdModel> {

    public ObservableField<String>pwd=new ObservableField<>();
    public ObservableField<String>confirmpwd=new ObservableField<>();
    public String verify,phone;

    @Override
    protected void initData() {
    }

    @Override
    protected ResetPwdModel createModel() {
        return new ResetPwdModel();
    }

    @Override
    protected void sucessFull(BaseEntity entity) {
        if (entity.getMessage().equals("密码修改成功")){
            UserEventEntity userEventEntity = new UserEventEntity();
            userEventEntity.action = "update";
            userEventEntity.phone = phone;
            userEventEntity.pwd = pwd.get();
            EventBus.getDefault().post(userEventEntity);
            UserEventEntity finishEntity = new UserEventEntity();
            finishEntity.action = "finish";
            EventBus.getDefault().post(finishEntity);
            getUiChangeLiveData().getFinishActivity().setValue(""+System.currentTimeMillis());
        }else {
            ToastUtils.showShort(entity.getMessage());
        }
    }

    public void finish(){
        getUiChangeLiveData().getFinishActivity().setValue(""+System.currentTimeMillis());
    }

    public void confirm(){
        if (pwd.get().isEmpty()){
            ToastUtils.showShort("密码为空");
            return;
        }
        if (confirmpwd.get().isEmpty()){
            ToastUtils.showShort("确认密码为空");
            return;
        }
        if (!pwd.get().equals(confirmpwd.get())){
            ToastUtils.showShort("密码不匹配");
            return;
        }
        Map<String,Object>map = new HashMap<>();
        map.put("mobile",phone);
        map.put("pwd",pwd.get());
        m.requestResetPwd(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

}
