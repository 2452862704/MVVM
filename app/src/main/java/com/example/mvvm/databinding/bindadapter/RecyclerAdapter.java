package com.example.mvvm.databinding.bindadapter;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.mvvm.databinding.TestRVAdapter;

import java.util.List;

/**
 * 配置使用recyclerview的databinding各种情况的自定义属性
 * 网格布局管理器,有item点击事件的,有Item下自控件点击事件,
 * 水平或者垂直线性布局管理器
 * */
public class RecyclerAdapter {

    @BindingAdapter(value = {"datas","childclick"},requireAll = false)
    public static void LinearRecyclerView(RecyclerView recyclerView,
                                          List<Object>datas, OnItemChildClickListener childClickListener){
        BaseQuickAdapter adapter = (BaseQuickAdapter) recyclerView.getAdapter();
        if (adapter == null) {
            LinearLayoutManager manager = new LinearLayoutManager(recyclerView.getContext());
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            adapter = new TestRVAdapter();
            adapter.setOnItemChildClickListener(childClickListener);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
            adapter.setNewInstance(datas);
        }else {
            adapter.setNewInstance(datas);
        }
    }



}
