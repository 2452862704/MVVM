package com.example.cargroup.viewmodel;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.cargroup.R;
import com.example.cargroup.adapter.ShipeAddressAdapter;
import com.example.cargroup.data.OrderAction;
import com.example.cargroup.data.entity.CarEvent;
import com.example.cargroup.data.entity.ShipAddress;
import com.example.cargroup.data.entity.ShipAddressEntity;
import com.example.cargroup.model.ShipAddressModel;
import com.example.mvvmcommon.arouterprovider.UserProvider;
import com.example.mvvmcommon.field.UIField;
import com.example.mvvmcommon.mvvm.viewmodel.BaseViewModel;
import com.example.networkmoudle.entity.BaseEntity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ShipAddressListViewModel extends BaseViewModel<ShipAddressModel>
        implements OnItemChildClickListener {

    public ShipeAddressAdapter addressAdapter = new ShipeAddressAdapter();

    @Override
    protected void initData() {
        addressAdapter.setOnItemChildClickListener(this);
        EventBus.getDefault().register(this);
        int id = ((UserProvider) ARouter.getInstance().build("/usergroup/userprovider").navigation()).getUserId();
        if (id == 0){
            //跳转到登录界面
            Map<String,Object>pageMap = new HashMap<>();
            pageMap.put(UIField.ACTIONTYPE,UIField.AROUTERSTART);
            pageMap.put(UIField.ACTIONROUTERKEY,"/usergroup/loginactivity");
            getUiChangeLiveData().getStartActivity().setValue(pageMap);
        }
        Map<String,Object>map=new HashMap<>();
        map.put("id",id);
        m.requestAddressList(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    protected ShipAddressModel createModel() {
        return new ShipAddressModel();
    }

    @Override
    protected void sucessFull(BaseEntity entity) {
        if (entity instanceof ShipAddressEntity){
            ShipAddressEntity shipAddressEntity = (ShipAddressEntity) entity;
            addressAdapter.setNewInstance(shipAddressEntity.data);
        }else {
            ToastUtils.showShort(entity.getMessage());
            int id = ((UserProvider) ARouter.getInstance().build("/usergroup/userprovider").navigation()).getUserId();
            if (id == 0){
                //跳转到登录界面
                Map<String,Object>pageMap = new HashMap<>();
                pageMap.put(UIField.ACTIONTYPE,UIField.AROUTERSTART);
                pageMap.put(UIField.ACTIONROUTERKEY,"/usergroup/loginactivity");
                getUiChangeLiveData().getStartActivity().setValue(pageMap);
            }
            Map<String,Object>map=new HashMap<>();
            map.put("id",id);
            m.requestAddressList(map).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateList(CarEvent event){
        if (event.action.equals("shipUpdate")){
            int id = ((UserProvider) ARouter.getInstance().build("/usergroup/userprovider").navigation()).getUserId();
            if (id == 0){
                //跳转到登录界面
                Map<String,Object>pageMap = new HashMap<>();
                pageMap.put(UIField.ACTIONTYPE,UIField.AROUTERSTART);
                pageMap.put(UIField.ACTIONROUTERKEY,"/usergroup/loginactivity");
                getUiChangeLiveData().getStartActivity().setValue(pageMap);
            }
            Map<String,Object>map=new HashMap<>();
            map.put("id",id);
            m.requestAddressList(map).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void finish(){
        getUiChangeLiveData().getFinishActivity().setValue(""+System.currentTimeMillis());
        List<ShipAddress>list = addressAdapter.getData();
        for (ShipAddress shipAddress : list){
            if (shipAddress.ship_is_default == 0){
                OrderAction action = new OrderAction("shipe");
                action.data = shipAddress;
                EventBus.getDefault().post(action);
                return;
            }
        }

    }

    public void createAddress(){
        Bundle bundle = new Bundle();
        bundle.putBoolean("addFlag",true);
        Map<String,Object>pageMap = new HashMap<>();
        pageMap.put(UIField.ACTIONTYPE,UIField.AROUTERSTART);
        pageMap.put(UIField.VALUESKEY,bundle);
        pageMap.put(UIField.ACTIONROUTERKEY,"/cargroup/edtshipaddressactivity");
        getUiChangeLiveData().getStartActivity().setValue(pageMap);
    }

    @Override
    public void onItemChildClick(@NonNull @NotNull BaseQuickAdapter adapter, @NonNull @NotNull View view, int position) {
        int id = view.getId();
        if (R.id.mSetDefaultTv == id){
            int flag = addressAdapter.getItem(position).ship_is_default;
            if (flag == 0)
                flag=1;
            else
                flag = 0;
            LogUtils.e("onItemChildClick:"+flag);
            int userId = ((UserProvider) ARouter.getInstance().build("/usergroup/userprovider").navigation()).getUserId();
            Map<String,Object>map=new HashMap<>();
            map.put("id",addressAdapter.getItem(position).id);
            map.put("shipAddress",addressAdapter.getItem(position).ship_address);
            map.put("shipIsDefault",flag);
            map.put("shipUserMobile",addressAdapter.getItem(position).ship_user_mobile);
            map.put("shipUserName",addressAdapter.getItem(position).ship_user_name);
            map.put("userId",userId);
            m.requestUpdateAddress(map).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this);
        }else if (R.id.mEditTv == id){
            Bundle bundle = new Bundle();
            bundle.putBoolean("addFlag",false);
            bundle.putSerializable("shipAddress", (Serializable) adapter.getItem(position));
            Map<String,Object>pageMap = new HashMap<>();
            pageMap.put(UIField.ACTIONTYPE,UIField.AROUTERSTART);
            pageMap.put(UIField.VALUESKEY,bundle);
            pageMap.put(UIField.ACTIONROUTERKEY,"/cargroup/edtshipaddressactivity");
            getUiChangeLiveData().getStartActivity().setValue(pageMap);
        }else if (R.id.mDeleteTv == id){
            Map<String,Object>map=new HashMap<>();
            map.put("id",addressAdapter.getItem(position).id);
            map.put("userId",addressAdapter.getItem(position).user_id);
            m.requestDelAddress(map).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this);
        }
    }
}
