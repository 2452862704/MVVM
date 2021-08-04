package com.example.mvvm.databinding;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.mvvm.R;
import com.example.mvvm.databinding.entity.ItemEntity;

import org.jetbrains.annotations.NotNull;

public class TestRVAdapter extends BaseQuickAdapter<ItemEntity, BaseViewHolder> {
    public TestRVAdapter() {
        super(R.layout.item_test);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, ItemEntity o) {
          baseViewHolder.setText(R.id.item_tv,o.getName());
    }
}
