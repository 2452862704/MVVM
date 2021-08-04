package com.example.categorygroup.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.categorygroup.R;
import com.example.categorygroup.data.entity.CategoryValueEntity;
import com.example.loadbitmap.LoadImage;

import org.jetbrains.annotations.NotNull;

public class CategoryValueAdapter extends BaseQuickAdapter<CategoryValueEntity.DataBean, BaseViewHolder> {
    public CategoryValueAdapter() {
        super(R.layout.item_category_value);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder,
                           CategoryValueEntity.DataBean dataBean) {
        baseViewHolder.setText(R.id.category_value_item_tv, dataBean.category_name);
        ImageView img = baseViewHolder.getView(R.id.category_value_item_img);
        LoadImage.loadMatchImg(dataBean.category_icon,img);
    }
}
