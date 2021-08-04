package com.example.categorygroup.binds;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;

public class CategoryRVBinds {

//    @BindingAdapter(value = {"layoutmanager"},requireAll = true)
//    public static void bindManager(RecyclerView recyclerView, RecyclerView.LayoutManager layoutmanager){
//        recyclerView.setLayoutManager(layoutmanager);
//    }

    @BindingAdapter(value = {"adapter","type"},requireAll = true)
    public static void bindAdapter(RecyclerView recyclerView, BaseQuickAdapter adapter,Integer type){
        if (type == 0){
            LinearLayoutManager manager = new LinearLayoutManager(recyclerView.getContext());
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(manager);
        }else if (type==1){
            GridLayoutManager manager = new GridLayoutManager(recyclerView.getContext(),3);
            manager.setOrientation(GridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(manager);
        }
        recyclerView.setAdapter(adapter);
    }


}
