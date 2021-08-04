package com.example.homegroup.seckill.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.homegroup.R;
import com.example.homegroup.seckill.data.entity.SecKillEntity;
import com.example.loadbitmap.LoadImage;

import org.jetbrains.annotations.NotNull;

public class SecKillAdapter extends BaseQuickAdapter<SecKillEntity.Value, BaseViewHolder> {
    public SecKillAdapter() {
        super(R.layout.item_seckill);
        addChildClickViewIds(R.id.seckill_add_tv);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, SecKillEntity.Value value) {
        ImageView imageView = baseViewHolder.getView(R.id.seckill_item_img);
        baseViewHolder.setText(R.id.seckill_item_title,value.goods_desc);
        baseViewHolder.setText(R.id.seckill_item_dec,value.goods_default_sku);
        baseViewHolder.setText(R.id.seckill_item_price,value.goods_default_price);
        LoadImage.loadImg(value.goods_default_icon,imageView);
    }
}
