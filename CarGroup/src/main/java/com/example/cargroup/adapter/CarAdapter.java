package com.example.cargroup.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.cargroup.R;
import com.example.cargroup.data.entity.CarListEntity;
import com.example.loadbitmap.LoadImage;

import org.jetbrains.annotations.NotNull;

public class CarAdapter extends BaseQuickAdapter<CarListEntity.Values, BaseViewHolder> {
    public CarAdapter() {
        super(R.layout.item_car);
        addChildClickViewIds(R.id.car_item_radio,R.id.car_item_left,R.id.car_item_right);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, CarListEntity.Values values) {
        ImageView img = baseViewHolder.getView(R.id.car_item_img);
        LoadImage.loadImg(values.goods_icon,img);
        baseViewHolder.setText(R.id.car_item_title,values.goods_desc);
        baseViewHolder.setText(R.id.car_item_dec,values.goods_sku);
        baseViewHolder.setText(R.id.car_item_price,"¥"+values.goods_price);
        baseViewHolder.setText(R.id.car_item_num,""+values.goods_count);
        RadioButton car_item_radio = baseViewHolder.getView(R.id.car_item_radio);
        car_item_radio.setChecked(values.selFlag);//设置选中状态
        if (values.edtFlag){
            //当前出于购物车列表编辑状态
            car_item_radio.setVisibility(View.VISIBLE);
        }else {
            //当前出于购物车列表非编辑状态
            car_item_radio.setVisibility(View.GONE);
        }
    }
}
