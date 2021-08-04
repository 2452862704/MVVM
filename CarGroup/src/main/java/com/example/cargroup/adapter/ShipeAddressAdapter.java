package com.example.cargroup.adapter;

import android.widget.RadioButton;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.cargroup.R;
import com.example.cargroup.data.entity.ShipAddress;

import org.jetbrains.annotations.NotNull;

public class ShipeAddressAdapter extends BaseQuickAdapter<ShipAddress, BaseViewHolder> {
    public ShipeAddressAdapter() {
        super(R.layout.item_address_item);
        addChildClickViewIds(R.id.mSetDefaultTv,R.id.mEditTv,R.id.mDeleteTv);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, ShipAddress shipAddress) {
        baseViewHolder.setText(R.id.mShipNameTv,shipAddress.ship_user_name);
        baseViewHolder.setText(R.id.mShipAddressTv,shipAddress.ship_address);
        RadioButton radioButton = baseViewHolder.getView(R.id.mSetDefaultTv);
        LogUtils.e("ShipeAddressAdapter:"+shipAddress.ship_is_default);
        LogUtils.e("ShipeAddressAdapter:"+(shipAddress.ship_is_default==0));
        radioButton.setChecked(shipAddress.ship_is_default==0);
    }
}
