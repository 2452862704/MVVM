package com.example.categorygroup.adapter;

import android.graphics.drawable.ColorDrawable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.categorygroup.R;
import com.example.categorygroup.data.entity.CategoryEntity;

import org.jetbrains.annotations.NotNull;

public class CategoryAdapter extends BaseQuickAdapter<CategoryEntity, BaseViewHolder> {
    public CategoryAdapter() {
        super(R.layout.item_category);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, CategoryEntity categoryEntity) {
        baseViewHolder.setText(R.id.category_item_tv,categoryEntity.name);
        TextView tv = baseViewHolder.getView(R.id.category_item_tv);
        if (categoryEntity.selFlag){
            tv.setBackground(new ColorDrawable());
        }else {
            tv.setBackgroundResource(R.drawable.categoty_item_bg);
        }
    }
}
