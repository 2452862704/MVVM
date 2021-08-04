package com.example.usergroup.viewmodel;

import androidx.databinding.ObservableField;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.mvvmcommon.field.UIField;
import com.example.mvvmcommon.mvvm.viewmodel.BaseViewModel;
import com.example.networkmoudle.entity.BaseEntity;
import com.example.usergroup.data.entity.UserDaoEntity;
import com.example.usergroup.data.entity.UserEntity;
import com.example.usergroup.data.entity.UserEventEntity;
import com.example.usergroup.model.LoginModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends BaseViewModel<LoginModel> {

    public ObservableField<String>phone=new ObservableField<>("");
    public ObservableField<String>pwd=new ObservableField<>("");

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void update(UserEventEntity entity){
        if (entity.action.equals("update")) {
            phone.set(entity.phone);
            pwd.set(entity.pwd);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected LoginModel createModel() {
        return new LoginModel();
    }

    @Override
    protected void sucessFull(BaseEntity entity) {
        if (entity instanceof UserEntity){
            UserEntity userEntity = (UserEntity) entity;
            if (userEntity.getStatus() == -1)
                return;
            if (userEntity.getMessage().equals("用户不存在"))
                return;
            new UserDaoEntity().update(userEntity.data);
            UserEventEntity userEventEntity = new UserEventEntity();
            userEventEntity.action = "updateUser";
            EventBus.getDefault().post(userEventEntity);
            finish();
        }
    }

    public void finish(){
        getUiChangeLiveData().getFinishActivity().setValue(""+System.currentTimeMillis());
    }

    public void login(){
        LogUtils.e("login()");
        if (phone.get().isEmpty()){
            ToastUtils.showShort("请输入手机号");
            return;
        }
        if (pwd.get().isEmpty()){
            ToastUtils.showShort("请输入密码");
            return;
        }
        Map<String ,Object>map = new HashMap<>();
        map.put("mobile",phone.get());
        map.put("pushId","");
        map.put("pwd",pwd.get());
        m.requestLogin(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    public void forget(){
        Map<String,Object>map= new HashMap<>();
        map.put(UIField.ACTIONTYPE, UIField.AROUTERSTART);
        map.put(UIField.ACTIONROUTERKEY,"/usergroup/forgetactivity");
        getUiChangeLiveData().getStartActivity().setValue(map);
    }

    public void register(){
        Map<String,Object>map= new HashMap<>();
        map.put(UIField.ACTIONTYPE,UIField.AROUTERSTART);
        map.put(UIField.ACTIONROUTERKEY,"/usergroup/registeractivity");
        getUiChangeLiveData().getStartActivity().setValue(map);
    }

}
