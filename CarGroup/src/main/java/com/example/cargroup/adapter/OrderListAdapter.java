package com.example.cargroup.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.cargroup.R;
import com.example.cargroup.data.entity.Order;
import com.example.loadbitmap.LoadImage;

import org.jetbrains.annotations.NotNull;

public class OrderListAdapter extends BaseQuickAdapter<Order, BaseViewHolder> {

    public OrderListAdapter() {
        super(R.layout.item_order_item);
        addChildClickViewIds(R.id.mConfirmBtn,R.id.mPayBtn,R.id.mCancelBtn);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Order order) {
        ImageView img = baseViewHolder.getView(R.id.mGoodsIconIv);
        LoadImage.loadMatchImg(order.orderGoodsList.get(0).goods_icon,img);
        String name = "";
        Button mConfirmBtn = baseViewHolder.getView(R.id.mConfirmBtn);
        Button mPayBtn = baseViewHolder.getView(R.id.mPayBtn);
        Button mCancelBtn = baseViewHolder.getView(R.id.mCancelBtn);
        switch (order.orderStatus){
            case 1:
                name = "待支付";
                mConfirmBtn.setVisibility(View.GONE);
                break;
            case 2:
                name = "待收货";
                mPayBtn.setVisibility(View.GONE);
                break;
            case 3:
                name = "已完成";
                mConfirmBtn.setVisibility(View.GONE);
                mPayBtn.setVisibility(View.GONE);
                mCancelBtn.setVisibility(View.GONE);
                break;
            case 4:
                name = "已取消";
                mConfirmBtn.setVisibility(View.GONE);
                mPayBtn.setVisibility(View.GONE);
                mCancelBtn.setVisibility(View.GONE);
                break;
            default:
                name="";
        }
        baseViewHolder.setText(R.id.mOrderStatusNameTv,name);
        baseViewHolder.setText(R.id.mGoodsDescTv,order.orderGoodsList.get(0).goods_desc);
        baseViewHolder.setText(R.id.mGoodsPriceTv,order.orderGoodsList.get(0).goods_price);
        baseViewHolder.setText(R.id.mOrderInfoTv,order.orderGoodsList.get(0).goods_sku);
    }
}
