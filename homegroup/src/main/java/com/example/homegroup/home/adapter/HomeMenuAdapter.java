package com.example.homegroup.home.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.homegroup.R;
import com.example.homegroup.home.data.entity.HomeMenuEntity;

import org.jetbrains.annotations.NotNull;

public class HomeMenuAdapter extends BaseQuickAdapter<HomeMenuEntity, BaseViewHolder> {
    public HomeMenuAdapter() {
        super(R.layout.item_home_menu);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, HomeMenuEntity homeMenuEntity) {
        ImageView imageView = baseViewHolder.getView(R.id.home_menu_img);
        imageView.setImageResource(homeMenuEntity.iconId);
        baseViewHolder.setText(R.id.home_menu_tv,homeMenuEntity.title);
    }
}
