package com.example.homegroup.home.adapter;

import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.homegroup.R;
import com.example.loadbitmap.LoadImage;

import org.jetbrains.annotations.NotNull;

public class HomeHotAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public HomeHotAdapter() {
        super(R.layout.item_home_hot);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, String s) {
        ImageView img = baseViewHolder.getView(R.id.home_hot_img);
        LoadImage.loadMatchImg(s,img);
    }
}
