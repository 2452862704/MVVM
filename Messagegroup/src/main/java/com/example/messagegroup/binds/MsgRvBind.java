package com.example.messagegroup.binds;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;

public class MsgRvBind {

    @BindingAdapter(value = {"adapter"})
    public static void bindAdapter(RecyclerView recyclerView,
                                   BaseQuickAdapter adapter){
        LinearLayoutManager manager = new LinearLayoutManager(recyclerView.getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}
