package com.example.cargroup.viewmodel;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.cargroup.adapter.ConfirmOrderAdapter;
import com.example.cargroup.data.OrderAction;
import com.example.cargroup.data.entity.Order;
import com.example.cargroup.data.entity.OrderEntity;
import com.example.cargroup.data.entity.OrderGoods;
import com.example.cargroup.data.entity.PayEntity;
import com.example.cargroup.data.entity.ShipAddress;
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
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ConfirmOrderViewModel extends BaseViewModel<OrderModel>
        implements View.OnClickListener, OnItemClickListener {

    public ObservableField<String>totalPrice = new ObservableField<>("合计: 0");
    public ConfirmOrderAdapter adapter = new ConfirmOrderAdapter();
    public ObservableField<String>shipname = new ObservableField<>("");
    public ObservableField<String>shipaddress = new ObservableField<>("");
    public ObservableField<Boolean>shipflag = new ObservableField<>(false);
    private List<OrderGoods> list;
    public int ordernum = 0;
    private float price = 0f;
    private ShipAddress shipAddressEntity = new ShipAddress();
    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        adapter.setOnItemClickListener(this);
        Map<String,Object>map = new HashMap<>();
        map.put("orderId",ordernum);
        m.requestGetOrder(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void payResult(OrderAction result){
        if ("finishConfirm".equals(result.orderAction)) {
            //验证订单是否支付成功
            Map<String, Object> map = new HashMap<>();
            map.put("orderId", ordernum);
            m.requestOrderPay(map).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this);
        }else {
            shipAddressEntity= (ShipAddress) result.data;
            shipname.set(shipAddressEntity.ship_user_name);
            shipaddress.set(shipAddressEntity.ship_address);
            shipflag.set(true);
        }
    }


    @Override
    protected OrderModel createModel() {
        return new OrderModel();
    }

    /**
     *  {"status":0,"message":"获取订单成功","data":{"id":221,"payType":0,"shipAddress":{"id":10,"ship_user_name":"2323","ship_user_mobile":"232","ship_address":"2323","ship_is_default":0,"user_id":10},"totalPrice":"220000","orderStatus":-1,"orderGoodsList":[{"id":513,"goods_id":1,"goods_desc":"Apple MacBook Air 13.3英寸笔记本电脑 银色(Core i5 处理器/8GB内存/128GB SSD闪存 MMGF2CH/A)","goods_icon":"https://img11.360buyimg.com/n7/jfs/t2968/143/2485546147/238650/70df281e/57b12a31N8f4f75a3.jpg","goods_price":"100","goods_count":9,"goods_sku":"1.6GHz i5处理器,GB内存/128GB SSD,1件","order_id":221},{"id":514,"goods_id":1,"goods_desc":"Apple MacBook Air 13.3英寸笔记本电脑 银色(Core i5 处理器/8GB内存/128GB SSD闪存 MMGF2CH/A)","goods_icon":"https://img11.360buyimg.com/n7/jfs/t2968/143/2485546147/238650/70df281e/57b12a31N8f4f75a3.jpg","goods_price":"100","goods_count":4,"goods_sku":"1.6GHz i5处理器,GB内存/128GB SSD,1件","order_id":221},{"id":515,"goods_id":5,"goods_desc":"Apple 苹果 MacBook Pro 笔记本电脑 16年新款 15英寸 Multi-Touch Bar 256G 深空灰色","goods_icon":"https://img14.360buyimg.com/n5/s450x450_jfs/t3397/55/762020838/184157/7e507d32/58131c17Nb108ca54.jpg","goods_price":"800","goods_count":1,"goods_sku":"13英寸 Core i5 8G内存 256G闪存,深空灰色,1件","order_id":221},{"id":516,"goods_id":1,"goods_desc":"Apple MacBook Air 13.3英寸笔记本电脑 银色(Core i5 处理器/8GB内存/128GB SSD闪存 MMGF2CH/A)","goods_icon":"https://img11.360buyimg.com/n7/jfs/t2968/143/2485546147/238650/70df281e/57b12a31N8f4f75a3.jpg","goods_price":"100","goods_count":1,"goods_sku":"1.6GHz i5处理器,GB内存/128GB SSD,1件","order_id":221}]}}
     * */

    @Override
    protected void sucessFull(BaseEntity entity) {
        if (entity instanceof OrderEntity){
            OrderEntity orderEntity = (OrderEntity) entity;
            list = orderEntity.data.orderGoodsList;
            if (list == null)
                return;
            if (list.size() == 0)
                return;
            price = 0f;
            for (OrderGoods goods : list){
                price+=(Float.valueOf(goods.goods_price)*goods.goods_count);
            }
            totalPrice.set("合计: "+price);
            adapter.setNewInstance(list);
        }else if (entity instanceof PayEntity){
            PayEntity payEntity = (PayEntity) entity;
            //支付宝信息返回
            String alipayStr = payEntity.data;
            Map<String,Object>map=new HashMap<>();
            map.put(UIField.ACTIONTYPE, UIField.AROUTERSTART);
            map.put(UIField.ACTIONROUTERKEY,"/app/alipayactivity");
            Bundle bundle = new Bundle();
            bundle.putString("alipayStr",alipayStr);
            map.put(UIField.VALUESKEY,bundle);
            getUiChangeLiveData().getStartActivity().setValue(map);
        }else {
            if (entity.getMessage().equals("订单提交成功")){
                //订单状态修改为等待支付
                Map<String ,Object>map = new HashMap<>();
                map.put("orderId",ordernum);
                map.put("totalPrice",price);
                m.requestPaySign(map).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this);
            }else if (entity.getMessage().equals("订单支付成功")){
                LogUtils.e("订单支付成功");
                getUiChangeLiveData().getFinishActivity().setValue(""+System.currentTimeMillis());
                EventBus.getDefault().post(new OrderAction("finishCarList"));
            }
        }
    }

    public void finish(){
        getUiChangeLiveData().getFinishActivity().setValue(""+System.currentTimeMillis());
    }


    public void subOrderListener(){
        if (list == null){
            ToastUtils.showShort("无任何待支付商品");
            return;
        }
        if (list.size() == 0){
            ToastUtils.showShort("无任何待支付商品");
            return;
        }
        if (TextUtils.isEmpty(shipAddressEntity.ship_user_name)){
            ToastUtils.showShort("请选择收货地址");
            return;
        }
        int id = ((UserProvider) ARouter.getInstance().build("/usergroup/userprovider").navigation()).getUserId();
        if (id == 0){
            //跳转到登录界面
            Map<String,Object>pageMap = new HashMap<>();
            pageMap.put(UIField.ACTIONTYPE,UIField.AROUTERSTART);
            pageMap.put(UIField.ACTIONROUTERKEY,"/usergroup/loginactivity");
            getUiChangeLiveData().getStartActivity().setValue(pageMap);
        }
        //计算总金额
        price = 0f;
        for (OrderGoods goods : list){
            price+=(Float.valueOf(goods.goods_price)*goods.goods_count);
        }
        Map<String,Object>map = new HashMap<>();
        map.put("userId",id);
//        shipAddressEntity.id = 10;
//        shipAddressEntity.ship_user_name = "2323";
//        shipAddressEntity.ship_user_mobile = "232";
//        shipAddressEntity.ship_address = "2323";
//        shipAddressEntity.ship_is_default = 0;
//        shipAddressEntity.user_id = 10;
        Order orderEntity = new Order();
        orderEntity.id = ordernum;
        orderEntity.totalPrice = ""+price;
        orderEntity.payType = 0;
        orderEntity.orderStatus = 0;
        orderEntity.orderGoodsList = list;
        orderEntity.shipAddress = shipAddressEntity;
        map.put("order",orderEntity);
        m.requestSubOrder(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public void onClick(View v) {
        //跳转到地址列表界面
        Map<String,Object>map = new HashMap<>();
        map.put(UIField.ACTIONTYPE,UIField.AROUTERSTART);
        map.put(UIField.ACTIONROUTERKEY,"/cargroup/shipaddressactivity");
        getUiChangeLiveData().getStartActivity().setValue(map);
    }

    @Override
    public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {
        //跳转到商品详情
        ConfirmOrderAdapter confirmOrderAdapter = (ConfirmOrderAdapter) adapter;
        Bundle bundle = new Bundle();
        bundle.putString("img",confirmOrderAdapter.getItem(position).goods_icon);
        bundle.putString("title",confirmOrderAdapter.getItem(position).goods_desc);
        bundle.putString("dec",confirmOrderAdapter.getItem(position).goods_sku);
        bundle.putInt("id",confirmOrderAdapter.getItem(position).goods_id);
        bundle.putString("price",confirmOrderAdapter.getItem(position).goods_price);
        Map<String,Object>map = new HashMap<>();
        map.put(UIField.ACTIONTYPE,UIField.AROUTERSTART);
        map.put(UIField.ACTIONROUTERKEY,"/homegroup/homevalueactivity");
        map.put(UIField.VALUESKEY,bundle);
        getUiChangeLiveData().getStartActivity().setValue(map);
    }
}
