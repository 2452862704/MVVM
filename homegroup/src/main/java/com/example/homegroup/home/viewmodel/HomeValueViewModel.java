package com.example.homegroup.home.viewmodel;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.homegroup.home.adapter.HomeGoodsAdapter;
import com.example.homegroup.home.data.entity.HomeGoodsEntity;
import com.example.homegroup.home.model.HomeValueModel;
import com.example.homegroup.home.view.HomeValueActivity;
import com.example.mvvmcommon.arouterprovider.UserProvider;
import com.example.mvvmcommon.field.UIField;
import com.example.mvvmcommon.mvvm.viewmodel.BaseViewModel;
import com.example.networkmoudle.entity.BaseEntity;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeValueViewModel extends BaseViewModel<HomeValueModel> implements OnItemClickListener {

    private int pageNo = 1;
    public ObservableField<Boolean> refresh = new ObservableField<>(false);
    public ObservableField<Boolean> load = new ObservableField<>(false);
    public ObservableField<HomeGoodsEntity.DataBean> entity = new ObservableField<>();
    public HomeGoodsAdapter adapter = new HomeGoodsAdapter();

    @Override
    protected void initData() {
        adapter.setOnItemClickListener(this);
        //请求获取推荐商品数据
        Map<String, Object> map = new HashMap<>();
        map.put("categoryId", "14");
        map.put("pageNo", pageNo);
        m.requestGoods(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    protected HomeValueModel createModel() {
        return new HomeValueModel();
    }

    @Override
    protected void sucessFull(BaseEntity entity) {
        if (entity instanceof HomeGoodsEntity) {
            refresh.set(false);
            HomeGoodsEntity homeGoodsEntity = (HomeGoodsEntity) entity;
            adapter.setNewInstance(homeGoodsEntity.getData());
        } else {
            ToastUtils.showShort(entity.getMessage());
            //加入购物车成功跳转到购物车列表
            if (entity.getMessage().equals("添加购物车成功")) {
                //跳转到购物车列表界面
                Map<String, Object> pageMap = new HashMap<>();
                pageMap.put(UIField.ACTIONTYPE, UIField.AROUTERSTART);
                pageMap.put(UIField.ACTIONROUTERKEY, "/cargroup/carlistactivity");
                getUiChangeLiveData().getStartActivity().setValue(pageMap);
            }
        }
    }

    public void refreshListener() {
        refresh.set(true);
        pageNo = 1;
        Map<String, Object> map = new HashMap<>();
        map.put("categoryId", "14");
        map.put("pageNo", pageNo);
        m.requestGoods(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    public void loadMoreListener() {
        load.set(true);
        pageNo += 1;
        Map<String, Object> map = new HashMap<>();
        map.put("categoryId", "14");
        map.put("pageNo", pageNo);
        m.requestGoods(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseEntity>() {
                    @Override
                    public void onSubscribe(@NotNull Disposable d) {
                        compositeDisposable.add(d);
                        getUiChangeLiveData().getShowDialog().setValue("" + System.currentTimeMillis());
                    }

                    @Override
                    public void onNext(@NotNull BaseEntity entity) {
                        load.set(false);
                        HomeGoodsEntity homeGoodsEntity = (HomeGoodsEntity) entity;
                        if (homeGoodsEntity.getData() == null) {
                            ToastUtils.showShort("最后一页");
                            return;
                        }
                        if (homeGoodsEntity.getData().size() == 0) {
                            ToastUtils.showShort("最后一页");
                            return;
                        }
                        adapter.addData(homeGoodsEntity.getData());
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        showMsg(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        getUiChangeLiveData().getHideDialog().setValue("" + System.currentTimeMillis());
                    }
                });
    }

    public void finishActivity() {
        getUiChangeLiveData().getFinishActivity().setValue("" + System.currentTimeMillis());
    }

    public void share() {

    }

    public void carList() {
        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put(UIField.ACTIONTYPE, UIField.AROUTERSTART);
        pageMap.put(UIField.ACTIONROUTERKEY, "/cargroup/carlistactivity");
        getUiChangeLiveData().getStartActivity().setValue(pageMap);
    }

    public void addCar() {
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
        Map<String, Object> map = new HashMap<>();
        map.put("goodsCount", 1);
        map.put("goodsDesc", entity.get().getGoods_desc());
        map.put("goodsIcon", entity.get().getGoods_default_icon());
        map.put("goodsId", entity.get().getId());
        map.put("goodsPrice", entity.get().getGoods_default_price());
        map.put("goodsSku", entity.get().getGoods_default_sku());
        map.put("time", System.currentTimeMillis() / 1000);
        int id = ((UserProvider) ARouter.getInstance().build("/usergroup/userprovider").navigation()).getUserId();
        if (id == 0) {
            //跳转到登录界面
            Map<String, Object> pageMap = new HashMap<>();
            pageMap.put(UIField.ACTIONTYPE, UIField.AROUTERSTART);
            pageMap.put(UIField.ACTIONROUTERKEY, "/usergroup/loginactivity");
            getUiChangeLiveData().getStartActivity().setValue(pageMap);
        }
        map.put("userId", id);
        m.requestAddCar(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {
        //跳转到商品详情界面
        HomeGoodsAdapter homeGoodsAdapter = (HomeGoodsAdapter) adapter;
        Bundle bundle = new Bundle();
        bundle.putSerializable("entity", homeGoodsAdapter.getItem(position));
        Map<String, Object> map = new HashMap<>();
        map.put(UIField.ACTIONTYPE, UIField.NATIVESTART);
        map.put(UIField.NATIVEACTION, HomeValueActivity.class);
        map.put(UIField.VALUESKEY, bundle);
        getUiChangeLiveData().getStartActivity().setValue(map);
    }
}
