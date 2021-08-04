package com.example.cargroup.binds;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.cargroup.widget.CarLineDivider;

public class CarRvAdapter {

    @BindingAdapter(value = {"adapter"},requireAll = false)
    public static void carBind(RecyclerView recyclerView, BaseQuickAdapter adapter){

        LinearLayoutManager manager = new LinearLayoutManager(recyclerView.getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new CarLineDivider(recyclerView.getContext()));
        recyclerView.setAdapter(adapter);
    }

}
