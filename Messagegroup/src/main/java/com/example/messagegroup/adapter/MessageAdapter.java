package com.example.messagegroup.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.loadbitmap.LoadImage;
import com.example.messagegroup.R;
import com.example.messagegroup.data.entity.MessageEntity;

import org.jetbrains.annotations.NotNull;

public class MessageAdapter extends BaseQuickAdapter<MessageEntity.Values, BaseViewHolder> {
    public MessageAdapter() {
        super(R.layout.item_msg);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, MessageEntity.Values values) {
        baseViewHolder.setText(R.id.msg_item_title,values.msg_title);
        baseViewHolder.setText(R.id.msg_item_value,values.msg_content);
        baseViewHolder.setText(R.id.msg_item_time,values.msg_time);
        ImageView img = baseViewHolder.getView(R.id.msg_item_img);
        LoadImage.loadImg(values.msg_icon,img);
    }
}
