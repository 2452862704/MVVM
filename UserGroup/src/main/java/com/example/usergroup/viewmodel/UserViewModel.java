package com.example.usergroup.viewmodel;

import android.os.Bundle;

import androidx.databinding.ObservableField;

import com.blankj.utilcode.util.Utils;
import com.example.greendaomoudle.DaoSessionUtils;
import com.example.mvvmcommon.field.UIField;
import com.example.mvvmcommon.mvvm.viewmodel.BaseViewModel;
import com.example.networkmoudle.entity.BaseEntity;
import com.example.usergroup.dao.DaoMaster;
import com.example.usergroup.dao.DaoSession;
import com.example.usergroup.data.entity.UserDaoEntity;
import com.example.usergroup.data.entity.UserEntityValues;
import com.example.usergroup.data.entity.UserEventEntity;
import com.example.usergroup.model.UserModdel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserViewModel extends BaseViewModel<UserModdel> {
    public ObservableField<String>nickName = new ObservableField<>("登录/注册");
    public ObservableField<String>url = new ObservableField<>("");

    @Override
    protected void initData() {
        //初始化greendao
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(Utils.getApp(),"user.db");
        DaoMaster master = new DaoMaster(helper.getWritableDatabase());
        DaoSession session = master.newSession();
        DaoSessionUtils.getInstance().initDaoSession(session);
        EventBus.getDefault().register(this);
        List<UserEntityValues> list = new UserDaoEntity().selectAll();
        if (list.size()>0) {
            UserEntityValues values = new UserDaoEntity().selectAll().get(0);
            nickName.set(values.user_nick_name);
            url.set(values.user_icon);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void update(UserEventEntity entity){
        if (entity.action.equals("updateUser")) {
            List<UserEntityValues> list = new UserDaoEntity().selectAll();
            if (list.size()>0) {
                UserEntityValues values = new UserDaoEntity().selectAll().get(0);
                nickName.set(values.user_nick_name);
                url.set(values.user_icon);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected UserModdel createModel() {
        return new UserModdel();
    }

    @Override
    protected void sucessFull(BaseEntity entity) {

    }

    public void edtUser(){
        Map<String,Object> map= new HashMap<>();
        map.put(UIField.ACTIONTYPE,UIField.AROUTERSTART);
        map.put(UIField.ACTIONROUTERKEY,"/usergroup/edtuseractivity");
        getUiChangeLiveData().getStartActivity().setValue(map);
    }

    public void login(){
        if (!nickName.get().equals("登录/注册"))
            return;
        Map<String,Object> map= new HashMap<>();
        map.put(UIField.ACTIONTYPE,UIField.AROUTERSTART);
        map.put(UIField.ACTIONROUTERKEY,"/usergroup/loginactivity");
        getUiChangeLiveData().getStartActivity().setValue(map);
    }

    public void userOrder(){
        Map<String,Object> map= new HashMap<>();
        map.put(UIField.ACTIONTYPE,UIField.AROUTERSTART);
        map.put(UIField.ACTIONROUTERKEY,"/cargroup/ordergroupactivity");
        Bundle bundle = new Bundle();
        bundle.putInt("index",0);
        map.put(UIField.VALUESKEY,bundle);
        getUiChangeLiveData().getStartActivity().setValue(map);
    }

    public void completeOrder(){
        Map<String,Object> map= new HashMap<>();
        map.put(UIField.ACTIONTYPE,UIField.AROUTERSTART);
        map.put(UIField.ACTIONROUTERKEY,"/cargroup/ordergroupactivity");
        Bundle bundle = new Bundle();
        bundle.putInt("index",3);
        map.put(UIField.VALUESKEY,bundle);
        getUiChangeLiveData().getStartActivity().setValue(map);
    }

    public void edtShipe(){
        Map<String,Object> map= new HashMap<>();
        map.put(UIField.ACTIONTYPE,UIField.AROUTERSTART);
        map.put(UIField.ACTIONROUTERKEY,"/cargroup/shipaddressactivity");
        getUiChangeLiveData().getStartActivity().setValue(map);
    }

    public void payOrder(){
        Map<String,Object> map= new HashMap<>();
        map.put(UIField.ACTIONTYPE,UIField.AROUTERSTART);
        map.put(UIField.ACTIONROUTERKEY,"/cargroup/ordergroupactivity");
        Bundle bundle = new Bundle();
        bundle.putInt("index",1);
        map.put(UIField.VALUESKEY,bundle);
        getUiChangeLiveData().getStartActivity().setValue(map);
    }
    public void receivingOrder(){
        Map<String,Object> map= new HashMap<>();
        map.put(UIField.ACTIONTYPE,UIField.AROUTERSTART);
        map.put(UIField.ACTIONROUTERKEY,"/cargroup/ordergroupactivity");
        Bundle bundle = new Bundle();
        bundle.putInt("index",2);
        map.put(UIField.VALUESKEY,bundle);
        getUiChangeLiveData().getStartActivity().setValue(map);
    }
}
