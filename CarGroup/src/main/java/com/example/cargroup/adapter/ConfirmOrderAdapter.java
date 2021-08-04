package com.example.cargroup.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.cargroup.R;
import com.example.cargroup.data.entity.OrderGoods;
import com.example.loadbitmap.LoadImage;

import org.jetbrains.annotations.NotNull;

public class ConfirmOrderAdapter extends BaseQuickAdapter<OrderGoods, BaseViewHolder> {
    public ConfirmOrderAdapter() {
        super(R.layout.item_order_goods_item);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, OrderGoods orderGoods) {
        ImageView img = baseViewHolder.getView(R.id.mGoodsIconIv);
        LoadImage.loadMatchImg(orderGoods.goods_icon,img);
        baseViewHolder.setText(R.id.mGoodsDescTv,orderGoods.goods_desc);
        baseViewHolder.setText(R.id.mGoodsSkuTv, orderGoods.goods_sku);
        baseViewHolder.setText(R.id.mGoodsPriceTv,orderGoods.goods_price);
        baseViewHolder.setText(R.id.mGoodsCountTv,"x"+orderGoods.goods_count);
    }
}
