package com.example.cargroup.widget;

import android.content.Context;
import android.graphics.Color;

import androidx.annotation.Nullable;

import com.yanyusong.y_divideritemdecoration.Y_Divider;
import com.yanyusong.y_divideritemdecoration.Y_DividerBuilder;
import com.yanyusong.y_divideritemdecoration.Y_DividerItemDecoration;

public class CarLineDivider extends Y_DividerItemDecoration {

    public CarLineDivider(Context context) {
        super(context);
    }

    @Nullable
    @Override
    public Y_Divider getDivider(int itemPosition) {
        Y_Divider divider = new Y_DividerBuilder()
                .setBottomSideLine(true, Color.GRAY,1,0,0).create();
        return divider;
    }
}
