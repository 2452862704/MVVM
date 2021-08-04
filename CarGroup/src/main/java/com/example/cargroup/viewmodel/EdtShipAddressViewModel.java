package com.example.cargroup.viewmodel;

import android.text.TextUtils;

import androidx.databinding.ObservableField;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.example.cargroup.data.entity.CarEvent;
import com.example.cargroup.data.entity.ShipAddress;
import com.example.cargroup.model.ShipAddressModel;
import com.example.mvvmcommon.arouterprovider.UserProvider;
import com.example.mvvmcommon.mvvm.viewmodel.BaseViewModel;
import com.example.networkmoudle.entity.BaseEntity;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class EdtShipAddressViewModel extends BaseViewModel<ShipAddressModel> {

    public ShipAddress shipAddress;
    public boolean addFlag = true;
    public ObservableField<String>name=new ObservableField<>();
    public ObservableField<String>phone=new ObservableField<>();
    public ObservableField<String>address=new ObservableField<>();

    @Override
    protected void initData() {
        if (shipAddress==null)
            return;
        name.set(shipAddress.ship_user_name);
        phone.set(shipAddress.ship_user_mobile);
        address.set(shipAddress.ship_address);
    }

    @Override
    protected ShipAddressModel createModel() {
        return new ShipAddressModel();
    }

    @Override
    protected void sucessFull(BaseEntity entity) {
        ToastUtils.showShort(entity.getMessage());
//        if (entity.getMessage().equals("添加地址成功")||entity.getMessage().equals("修改成功")) {
        EventBus.getDefault().post(new CarEvent("shipUpdate"));
        getUiChangeLiveData().getFinishActivity().setValue("" + System.currentTimeMillis());
//        }
    }

    public void finish(){
        getUiChangeLiveData().getFinishActivity().setValue(""+System.currentTimeMillis());
    }

    public void save(){
        if (TextUtils.isEmpty(name.get())){
            ToastUtils.showShort("请输入收件人");
            return;
        }
        if (TextUtils.isEmpty(phone.get())){
            ToastUtils.showShort("请输入手机");
            return;
        }
        if (TextUtils.isEmpty(address.get())){
            ToastUtils.showShort("请输入地址");
            return;
        }
        int id = ((UserProvider) ARouter.getInstance().build("/usergroup/userprovider").navigation()).getUserId();
        Map<String,Object>map=new HashMap<>();
        map.put("shipAddress",address.get());
        map.put("shipIsDefault",1);
        map.put("shipUserMobile",phone.get());
        map.put("shipUserName",name.get());
        map.put("userId",id);
        if (addFlag){
            m.requestAddAddress(map)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this);
        }else {
            map.put("id",shipAddress.id);
            m.requestUpdateAddress(map)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this);
        }
    }

}
