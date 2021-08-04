package com.example.cargroup.viewmodel;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.cargroup.R;
import com.example.cargroup.adapter.OrderListAdapter;
import com.example.cargroup.data.OrderAction;
import com.example.cargroup.data.entity.OrderListEntity;
import com.example.cargroup.data.entity.PayEntity;
import com.example.cargroup.model.OrderModel;
import com.example.mvvmcommon.arouterprovider.UserProvider;
import com.example.mvvmcommon.field.UIField;
import com.example.mvvmcommon.mvvm.viewmodel.BaseViewModel;
import com.example.networkmoudle.entity.BaseEntity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class OrderListViewModel extends BaseViewModel<OrderModel> implements OnItemClickListener, OnItemChildClickListener {

    public OrderListAdapter orderAdapter = new OrderListAdapter();

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {
        orderAdapter.setOnItemChildClickListener(this);
        orderAdapter.setOnItemClickListener(this);
        int id = ((UserProvider) ARouter.getInstance().build("/usergroup/userprovider").navigation()).getUserId();
        if (id == 0){
            //跳转到登录界面
            Map<String,Object>pageMap = new HashMap<>();
            pageMap.put(UIField.ACTIONTYPE,UIField.AROUTERSTART);
            pageMap.put(UIField.ACTIONROUTERKEY,"/usergroup/loginactivity");
            getUiChangeLiveData().getStartActivity().setValue(pageMap);
        }
        Map<String,Object>map=new HashMap<>();
        map.put("orderStatus",0);
        map.put("userId",id);
        m.requestOrderList(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected OrderModel createModel() {
        return new OrderModel();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void payResult(OrderAction result){
        if (result.orderAction.equals("refresh")){
            int id = ((UserProvider) ARouter.getInstance().build("/usergroup/userprovider").navigation()).getUserId();
            Map<String,Object>map=new HashMap<>();
            map.put("orderStatus",2);
            map.put("userId",id);
            m.requestOrderList(map)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this);
            return;
        }
        //验证订单是否支付成功
        Map<String,Object>map = new HashMap<>();
        map.put("orderId",ordernum);
        m.requestOrderPay(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    private int ordernum = 0;

    @Override
    protected void sucessFull(BaseEntity entity) {
        if (entity instanceof OrderListEntity){
            OrderListEntity orderListEntity = (OrderListEntity) entity;
            orderAdapter.setNewInstance(orderListEntity.data);
        }else if (entity instanceof PayEntity){
            PayEntity payEntity = (PayEntity) entity;
            //支付宝信息返回
            String alipayStr = payEntity.data;
            Map<String,Object>map=new HashMap<>();
            map.put(UIField.ACTIONTYPE,UIField.AROUTERSTART);
            map.put(UIField.ACTIONROUTERKEY,"/app/alipayactivity");
            Bundle bundle = new Bundle();
            bundle.putString("alipayStr",alipayStr);
            map.put(UIField.VALUESKEY,bundle);
            getUiChangeLiveData().getStartActivity().setValue(map);
        }else {
            int id = ((UserProvider) ARouter.getInstance().build("/usergroup/userprovider").navigation()).getUserId();
            if (id == 0){
                //跳转到登录界面
                Map<String,Object>pageMap = new HashMap<>();
                pageMap.put(UIField.ACTIONTYPE,UIField.AROUTERSTART);
                pageMap.put(UIField.ACTIONROUTERKEY,"/usergroup/loginactivity");
                getUiChangeLiveData().getStartActivity().setValue(pageMap);
            }
            Map<String,Object>map=new HashMap<>();
            map.put("orderStatus",0);
            map.put("userId",id);
            m.requestOrderList(map)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this);
        }
    }

    @Override
    public void onItemChildClick(@NonNull @NotNull BaseQuickAdapter adapter, @NonNull @NotNull View view, int position) {
        int id = view.getId();
        if (R.id.mConfirmBtn == id){
            //确认收货
            Map<String,Object>map=new HashMap<>();
            map.put("orderId",orderAdapter.getItem(position).id);
            m.requestConfirm(map).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this);
        }else if (R.id.mPayBtn == id){
            //去支付
            ordernum = orderAdapter.getItem(position).id;
            Map<String,Object>map=new HashMap<>();
            map.put("orderId",orderAdapter.getItem(position).id);
            map.put("totalPrice",orderAdapter.getItem(position).totalPrice);
            m.requestPaySign(map).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this);
        }else {
            //取消
            Map<String,Object>map=new HashMap<>();
            map.put("orderId",orderAdapter.getItem(position).id);
            m.requestCancelOrder(map)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this);
        }
    }

    @Override
    public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {
        Map<String,Object>map = new HashMap<>();
        map.put(UIField.ACTIONTYPE,UIField.AROUTERSTART);
        map.put(UIField.ACTIONROUTERKEY,"/cargroup/orderdetailactivity");
        Bundle bundle = new Bundle();
        bundle.putInt("ordernum",orderAdapter.getItem(position).id);
        map.put(UIField.VALUESKEY,bundle);
        getUiChangeLiveData().getStartActivity().setValue(map);
    }
}
