package com.example.categorygroup.viewmodel;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.categorygroup.adapter.CategoryAdapter;
import com.example.categorygroup.adapter.CategoryValueAdapter;
import com.example.categorygroup.data.entity.CategoryEntity;
import com.example.categorygroup.data.entity.CategoryValueEntity;
import com.example.categorygroup.model.CategoryModel;
import com.example.mvvmcommon.mvvm.viewmodel.BaseViewModel;
import com.example.networkmoudle.entity.BaseEntity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * ObservableField:databinding下提供的数据容器->实现数据单向绑定->
 * ObservableField数据改变xml中引入ObservableField封装的数据的控件
 * 数据跟着改变
 * List->未被ObservableField封装->只能使用一次且在viewmodel设置到databinding前必须具有数据
 * 无数据xml中对应属性值为空
 */

public class CategoryViewModel extends BaseViewModel<CategoryModel> implements
        OnItemClickListener {

    List<CategoryValueEntity.DataBean>categoryValues=new ArrayList();
    List<CategoryEntity> categories=new ArrayList<>();
    public ObservableField<BaseQuickAdapter>categoryAdapter = new ObservableField<>();
    public ObservableField<BaseQuickAdapter>valueAdapter = new ObservableField<>();
    private int parentId=0;
    public Integer linear = 0;
    public Integer grid = 1;
    @Override
    protected void initData() {
        //初始化分类界面右侧数据源
        Map<String,Object>map = new HashMap<>();
        map.put("parentId",parentId);
        m.requestCategoryValue(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
        //初始化分类界面左侧数据源
        categories = m.requestCategory();
        categoryAdapter.set(new CategoryAdapter());
        valueAdapter.set(new CategoryValueAdapter());
        categoryAdapter.get().setOnItemClickListener(this);
        valueAdapter.get().setOnItemClickListener(this);
        categoryAdapter.get().setNewInstance(categories);
    }


    @Override
    protected CategoryModel createModel() {
        return new CategoryModel();
    }

    @Override
    protected void sucessFull(BaseEntity entity) {
        if (entity instanceof CategoryValueEntity){
            CategoryValueEntity categoryValueEntity = (CategoryValueEntity) entity;
            categoryValues = categoryValueEntity.data;
            LogUtils.e("CategoryViewModel:sucessFull()"+categoryValues.size());
//            valueAdapter.set(new CategoryValueAdapter());
//            valueAdapter.get().setOnItemClickListener(this);
            valueAdapter.get().setNewInstance(categoryValues);
        }
    }

    @Override
    public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter,
                            @NonNull @NotNull View view, int position) {
        //区分被点击的适配器
        if (adapter instanceof CategoryAdapter){
            //左侧适配器被点击
            //左侧recyclerview中item选中状态改变
            //右侧列表数据刷新
            List<CategoryEntity> list = new ArrayList<>();

            for (int i = 0; i < categories.size();i ++){
                CategoryEntity entity = categories.get(i);
                entity.selFlag = false;
                if (position == i){
                    entity.selFlag = true;
                }
                list.add(entity);
            }
            categories = new ArrayList<>(list);
            for (CategoryEntity categoryEntity : categories)
                LogUtils.e("onItemClick:"+categoryEntity.selFlag);
            categoryAdapter.get().setNewInstance(categories);
            Map<String,Object>map=new HashMap<>();
            map.put("parentId",categories.get(position).parent_id);
            m.requestCategoryValue(map).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this);
        }else {
            //右侧适配器被点击
            //跳转到对应商品列表界面
        }
    }
}
