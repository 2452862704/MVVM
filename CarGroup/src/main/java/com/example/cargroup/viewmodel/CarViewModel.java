package com.example.cargroup.viewmodel;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.cargroup.R;
import com.example.cargroup.adapter.CarAdapter;
import com.example.cargroup.data.OrderAction;
import com.example.cargroup.data.entity.CarListEntity;
import com.example.cargroup.data.entity.SubCarEntity;
import com.example.cargroup.model.CarModel;
import com.example.mvvmcommon.arouterprovider.UserProvider;
import com.example.mvvmcommon.field.UIField;
import com.example.mvvmcommon.mvvm.viewmodel.BaseViewModel;
import com.example.networkmoudle.entity.BaseEntity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CarViewModel extends BaseViewModel<CarModel> implements OnItemChildClickListener {

    public CarAdapter adapter = new CarAdapter();
    public ObservableField<String>allPrice = new ObservableField<>("合计:¥0");
    public ObservableField<Boolean>allCheck = new ObservableField<>(false);
    public ObservableField<String>edtStr = new ObservableField<>("编辑");
    private float price = 0f;
    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        adapter.setOnItemChildClickListener(this);
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
        m.requestCarList(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void payResult(OrderAction result){
        if (result.orderAction.equals("finishCarList"))
            getUiChangeLiveData().getFinishActivity().setValue(""+System.currentTimeMillis());
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    protected CarModel createModel() {
        return new CarModel();
    }

    @Override
    protected void sucessFull(BaseEntity entity) {
        if (entity instanceof CarListEntity){
            CarListEntity carListEntity = (CarListEntity) entity;
            adapter.setNewInstance(carListEntity.data);
            checkPrice();
        }else if (entity instanceof SubCarEntity){
            SubCarEntity subCarEntity = (SubCarEntity) entity;
            if (subCarEntity.getMessage().equals("购物车提交成功")){
                int orderId = subCarEntity.data;
                Bundle bundle = new Bundle();
                bundle.putInt("orderId",orderId);
                Map<String,Object>map = new HashMap<>();
                map.put(UIField.ACTIONTYPE,UIField.AROUTERSTART);
                map.put(UIField.VALUESKEY,bundle);
                map.put(UIField.ACTIONROUTERKEY,"/cargroup/confirmorderactivity");
                getUiChangeLiveData().getStartActivity().setValue(map);
            }
        }
    }

    //计算全部选中数据金额方法
    private void checkPrice(){
        List<CarListEntity.Values>list = adapter.getData();
        if (list.size()==0){
            allPrice.set("合计:¥0");
            return;
        }
        price = 0f;
        boolean flag = true;
        for (CarListEntity.Values values:list){
            if (values.selFlag) {
                if (TextUtils.isEmpty(values.goods_price))
                    values.goods_price = "0";
                price += (Float.valueOf(values.goods_price) * values.goods_count);
            }else
                flag = false;
        }
        allPrice.set("合计:¥"+price);
        allCheck.set(flag);
    }

    //编辑按钮点击监听
    public void edtClickListener(){
        List<CarListEntity.Values>list = adapter.getData();
        if (list.size() == 0)
            return;
        List<CarListEntity.Values> datas = new ArrayList<>();
        for (CarListEntity.Values values : list){
            values.edtFlag = !values.edtFlag;
            datas.add(values);
        }
        if (datas.get(0).edtFlag){
            edtStr.set("完成");
        }else
            edtStr.set("编辑");
        adapter.setNewInstance(datas);
    }
    //全选按钮点击监听
    public void checkAllListener(){
        //判断全选按钮是否可以点击
        List<CarListEntity.Values>list = adapter.getData();
        if (list.size() == 0){
            allCheck.set(false);
        }
        if (!list.get(0).edtFlag){
            ToastUtils.showShort("先点击编辑");
            boolean flag = allCheck.get();
            allCheck.set(flag);
            return;
        }
        boolean allFlag = !allCheck.get();
        allCheck.set(allFlag);
        List<CarListEntity.Values> datas = new ArrayList<>(list);
        for (int i = 0;i < datas.size();i ++){
            datas.get(i).selFlag = allFlag;
        }
        adapter.setNewInstance(datas);
    }
    //去结算按钮生成订单监听
    /**
     * {
     *   "goodsList": [
     *     {
     *       "goods_count": 0,
     *       "goods_desc": "string",
     *       "goods_icon": "string",
     *       "goods_id": 0,
     *       "goods_price": "string",
     *       "goods_sku": "string",
     *       "id": 0,
     *       "order_id": 0
     *     }
     *   ],
     *   "totalPrice": 0,
     *   "userId": 0
     * }
     * */
    public void orderListener(){
        List<CarListEntity.Values>list = adapter.getData();
        if (list.size() == 0){
            ToastUtils.showShort("无购物车数据");
            return;
        }
        List<CarListEntity.Values>goodsList = new ArrayList<>();
        for (CarListEntity.Values value : list ){
            if (value.selFlag&&value.goods_count>0){
                goodsList.add(value);
            }
        }
        int id = ((UserProvider) ARouter.getInstance().build("/usergroup/userprovider").navigation()).getUserId();
        if (id == 0){
            //跳转到登录界面
            Map<String,Object>pageMap = new HashMap<>();
            pageMap.put(UIField.ACTIONTYPE,UIField.AROUTERSTART);
            pageMap.put(UIField.ACTIONROUTERKEY,"/usergroup/loginactivity");
            getUiChangeLiveData().getStartActivity().setValue(pageMap);
        }
        if (goodsList.size() == 0){
            //为选择任何商品
            ToastUtils.showShort("未选择要支付商品");
            return;
        }
        Map<String,Object>map = new HashMap<>();
        map.put("goodsList",goodsList);
        map.put("totalPrice",(long)price);
        map.put("userId",id);
        m.requestSubCar(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public void onItemChildClick(@NonNull @NotNull BaseQuickAdapter adapter, @NonNull @NotNull View view, int position) {
        CarListEntity.Values values = (CarListEntity.Values) adapter.getItem(position);
        if (!values.edtFlag){
            ToastUtils.showShort("非编辑状态");
            return;
        }
        if (R.id.car_item_left == view.getId()){
            //-操作
            values.goods_count-=1;
            if (values.goods_count<0)
                values.goods_count = 0;
        }else if (R.id.car_item_right == view.getId()){
            //+操作
            values.goods_count+=1;
        }else if (R.id.car_item_radio == view.getId()){
            //选择操作
            values.selFlag = !values.selFlag;
        }
        adapter.setData(position,values);
        //计算总金额
        checkPrice();
    }
}
