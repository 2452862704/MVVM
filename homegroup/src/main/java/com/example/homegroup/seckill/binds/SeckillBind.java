package com.example.homegroup.seckill.binds;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.homegroup.seckill.adapter.SeckillValueAdapter;
import com.example.mvvmcommon.widget.LineDivider;
import com.youth.banner.Banner;

import java.util.List;

public class SeckillBind {

    @BindingAdapter(value = {"seckilladapter"},requireAll = true)
    public static void secKillRVBind(RecyclerView recyclerView, BaseQuickAdapter seckilladapter){
        LinearLayoutManager manager = new LinearLayoutManager(recyclerView.getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new LineDivider(recyclerView.getContext()));
        recyclerView.setAdapter(seckilladapter);
    }

    @BindingAdapter(value = {"seckillbanneres"},requireAll = true)
    public static void secKillBannerAdapter(Banner banner, List<String>list){
        banner.setOrientation(Banner.VERTICAL);
        banner.setAdapter(new SeckillValueAdapter(list));
        banner.start();
    }

}
