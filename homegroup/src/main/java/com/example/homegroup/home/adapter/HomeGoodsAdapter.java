package com.example.homegroup.home.adapter;

import android.widget.ImageView;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.homegroup.R;
import com.example.homegroup.home.data.entity.HomeGoodsEntity;
import com.example.loadbitmap.LoadImage;

import org.jetbrains.annotations.NotNull;

public class HomeGoodsAdapter extends BaseQuickAdapter<HomeGoodsEntity.DataBean, BaseViewHolder> {

    public HomeGoodsAdapter() {
        super(R.layout.item_home_goods);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, HomeGoodsEntity.DataBean dataBean) {
        ImageView mGoodsDetailItemIv = baseViewHolder.getView(R.id.mGoodsDetailItemIv);
        baseViewHolder.setText(R.id.mGoodsDetailItemTitleTv,dataBean.getGoods_desc());
        baseViewHolder.setText(R.id.mGoodsDetailItemPriceTv,"$"+dataBean.getGoods_default_price());
        LoadImage.loadMatchImg(dataBean.getGoods_default_icon(),mGoodsDetailItemIv);
    }
}
