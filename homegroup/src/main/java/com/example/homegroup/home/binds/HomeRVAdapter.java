package com.example.homegroup.home.binds;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.homegroup.R;
import com.example.homegroup.home.adapter.HomeHotAdapter;
import com.example.homegroup.home.adapter.HomeMenuAdapter;
import com.example.homegroup.home.adapter.HomeTopAdapter;
import com.example.homegroup.home.adapter.ImageBannerAdapter;
import com.example.homegroup.home.adapter.TextBannerAdapter;
import com.example.homegroup.home.data.entity.HomeBannerEntity;
import com.example.homegroup.home.data.entity.HomeGoodsEntity;
import com.example.homegroup.home.data.entity.HomeMenuEntity;
import com.example.homegroup.home.widget.HomeDivider;
import com.example.loadbitmap.LoadImage;
import com.youth.banner.Banner;

import java.util.List;

public class HomeRVAdapter {

    @BindingAdapter(value = {"itemClickListener","adapter",
            "imagebanner","txtbanner","menudata","hotdata","topdata","transform"},requireAll = true)
    public static void homeRVBind(RecyclerView recyclerView, OnItemClickListener itemClickListener,
                                  BaseQuickAdapter adapter,List<HomeBannerEntity>imagebanner,
                                  List<String>txtbanner, List<HomeMenuEntity>menudata,
                                  List<String> hotdata,List<HomeBannerEntity> topdata,
                                  ViewPager.PageTransformer transform){
        GridLayoutManager manager = new GridLayoutManager(recyclerView.getContext(),2);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter.setOnItemClickListener(itemClickListener);
        recyclerView.addItemDecoration(new HomeDivider(recyclerView.getContext()));
        //载入头布局xml
        View headView = LayoutInflater.from(recyclerView.getContext()).inflate(R.layout.view_home_banner,null);
        adapter.addHeaderView(headView);
        //初始化菜单recyclerview
        RecyclerView menuRv = headView.findViewById(R.id.home_menu_rv);
        GridLayoutManager menuManager = new GridLayoutManager(recyclerView.getContext(),4);
        menuManager.setOrientation(GridLayoutManager.VERTICAL);
        menuRv.setLayoutManager(menuManager);
        HomeMenuAdapter menuAdapter = new HomeMenuAdapter();
        menuAdapter.setOnItemClickListener(itemClickListener);
        menuRv.setAdapter(menuAdapter);
        menuAdapter.setNewInstance(menudata);
        //热门商品水平列表
        RecyclerView hotRv = headView.findViewById(R.id.home_hot_rv);
        LinearLayoutManager hotManager = new LinearLayoutManager(recyclerView.getContext());
        hotManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        hotRv.setLayoutManager(hotManager);
        HomeHotAdapter hotAdapter = new HomeHotAdapter();
        hotRv.setAdapter(hotAdapter);
        hotAdapter.setNewInstance(hotdata);
        //通告banner
        Banner<String, TextBannerAdapter>txtBanner = headView.findViewById(R.id.home_text_banner);
        txtBanner.setOrientation(Banner.VERTICAL);
        txtBanner.setAdapter(new TextBannerAdapter(txtbanner));
        txtBanner.start();
        //顶部广告抡博banner
        Banner<HomeBannerEntity, ImageBannerAdapter>imgBanner = headView.findViewById(R.id.home_view_banner);
        imgBanner.setOrientation(Banner.HORIZONTAL);
        imgBanner.setAdapter(new ImageBannerAdapter(imagebanner));
        imgBanner.start();
        //设置top viewpager
        ViewPager vp = headView.findViewById(R.id.home_vp);
        vp.setOffscreenPageLimit(4);
        vp.setPageTransformer(true,transform);
        vp.setAdapter(new HomeTopAdapter(topdata));
        recyclerView.setAdapter(adapter);
    }


    @BindingAdapter(value = {"valueadapter","entity"},requireAll = false)
    public static void valueRVBind(RecyclerView recyclerView,
                                   BaseQuickAdapter valueadapter,
                                   HomeGoodsEntity.DataBean entity){
        BaseQuickAdapter baseQuickAdapter = (BaseQuickAdapter) recyclerView.getAdapter();
        if (baseQuickAdapter == null) {
            GridLayoutManager manager = new GridLayoutManager(recyclerView.getContext(), 2);
            manager.setOrientation(GridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(manager);
            View view = LayoutInflater.from(recyclerView.getContext())
                    .inflate(R.layout.view_homevalue, null);
            ImageView img = view.findViewById(R.id.value_head_img);
            TextView title = view.findViewById(R.id.value_head_title);
            TextView dec = view.findViewById(R.id.value_head_dec);
            LoadImage.loadMatchImg(entity.getGoods_default_icon(), img);
            title.setText(entity.getGoods_desc());
            dec.setText(entity.getGoods_default_sku());
            valueadapter.setHeaderView(view);
            recyclerView.setAdapter(valueadapter);
        }else{
            View view = LayoutInflater.from(recyclerView.getContext())
                    .inflate(R.layout.view_homevalue, null);
            ImageView img = view.findViewById(R.id.value_head_img);
            TextView title = view.findViewById(R.id.value_head_title);
            TextView dec = view.findViewById(R.id.value_head_dec);
            LoadImage.loadMatchImg(entity.getGoods_default_icon(), img);
            title.setText(entity.getGoods_desc());
            dec.setText(entity.getGoods_default_sku());
            valueadapter.setHeaderView(view);
        }
    }
}
