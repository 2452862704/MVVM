package com.example.homegroup.seckill.viewmodel;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.homegroup.seckill.adapter.SecKillAdapter;
import com.example.homegroup.seckill.data.entity.SecKillEntity;
import com.example.homegroup.seckill.data.entity.TimeAction;
import com.example.homegroup.seckill.model.SecKillModel;
import com.example.mvvmcommon.arouterprovider.UserProvider;
import com.example.mvvmcommon.field.UIField;
import com.example.mvvmcommon.mvvm.viewmodel.BaseViewModel;
import com.example.networkmoudle.entity.BaseEntity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SecKillesViewModel extends BaseViewModel<SecKillModel> implements
        OnItemChildClickListener, OnItemClickListener {
    private long endTime = 0;
    private long nowTime = 0;
    public ObservableField<String>titleStr = new ObservableField<>("");
    public SecKillAdapter adapter = new SecKillAdapter();
    private Map<String,Object>addMap;

    @Override
    protected void initData() {
        adapter.setOnItemChildClickListener(this);
        adapter.setOnItemClickListener(this);
        EventBus.getDefault().register(this);
        m.requestSecKilles().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateTime(TimeAction action){
        nowTime = action.time;
        //发起请求添加到购物车
        addMap.put("time",nowTime);
        m.requestAddSecKill(addMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    public void finish(){
        getUiChangeLiveData().getFinishActivity().setValue(""+System.currentTimeMillis());
    }

    @Override
    protected SecKillModel createModel() {
        return new SecKillModel();
    }

    @Override
    protected void sucessFull(BaseEntity entity) {
        if (entity instanceof SecKillEntity){
            SecKillEntity secKillEntity = (SecKillEntity) entity;
            if (secKillEntity.data == null)
                return;
            if (secKillEntity.data.size() == 0)
                return;
            nowTime = secKillEntity.data.get(0).nowtime;
            endTime = secKillEntity.data.get(0).endtime*1000;
            //将当前时间发送到ativity
            EventBus.getDefault().post(nowTime);
            //将结束时间转化为小时->设置到xml界面中
            Date date = TimeUtils.millis2Date(endTime);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            titleStr.set(hour+"点秒杀");
            adapter.setNewInstance(secKillEntity.data);
        }else {
            LogUtils.e("获取添加秒杀结果:"+entity.getMessage());
            ToastUtils.showShort(entity.getMessage());
        }
    }

    @Override
    public void onItemChildClick(@NonNull @NotNull BaseQuickAdapter adapter, @NonNull @NotNull View view, int position) {
        //立刻抢购
        /**
         * {
         *   "goodsCount": 0,
         *   "goodsDesc": "string",
         *   "goodsIcon": "string",
         *   "goodsId": 0,
         *   "goodsPrice": "string",
         *   "goodsSku": "string",
         *   "time": 0,
         *   "userId": 0
         * }
         * */
        int id = ((UserProvider)ARouter.getInstance().build("/usergroup/userprovider").navigation()).getUserId();
        LogUtils.e("id:"+id);
        SecKillEntity.Value value = (SecKillEntity.Value) adapter.getItem(position);
        addMap = new HashMap<>();
        addMap.put("goodsCount",1);
        addMap.put("goodsDesc",value.goods_desc);
        addMap.put("goodsIcon",value.goods_default_icon);
        addMap.put("goodsId",value.id);
        addMap.put("goodsPrice",value.goods_default_price);
        addMap.put("goodsSku",value.goods_default_sku);
        addMap.put("userId",id==0?14:id);
        EventBus.getDefault().post("getTime");
    }

    @Override
    public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {
        //跳转到商品详情页
        SecKillEntity.Value value = (SecKillEntity.Value) adapter.getItem(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("value",value);
        Map<String,Object>map= new HashMap<>();
        map.put(UIField.ACTIONTYPE, UIField.AROUTERSTART);
        map.put(UIField.ACTIONROUTERKEY,"/homegroup/seckillvaluesactivity");
        map.put(UIField.VALUESKEY,bundle);
        getUiChangeLiveData().getStartActivity().setValue(map);
    }
}
