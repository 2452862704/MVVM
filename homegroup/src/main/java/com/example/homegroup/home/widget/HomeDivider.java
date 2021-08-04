package com.example.homegroup.home.widget;

import android.content.Context;

import androidx.annotation.Nullable;

import com.yanyusong.y_divideritemdecoration.Y_Divider;
import com.yanyusong.y_divideritemdecoration.Y_DividerBuilder;
import com.yanyusong.y_divideritemdecoration.Y_DividerItemDecoration;

public class HomeDivider extends Y_DividerItemDecoration {
    public HomeDivider(Context context) {
        super(context);
    }

    @Nullable
    @Override
    public Y_Divider getDivider(int itemPosition) {
        Y_Divider divider = null;
        if (itemPosition%4<3){
            //左侧列->底部以及右侧
            divider =  new Y_DividerBuilder()
                    .setRightSideLine(true, 0xffffffff, 5, 0, 0)
                    .setBottomSideLine(true, 0xffffffff, 5, 0, 0).create();
        }else {
            //右侧列->底部
            divider =  new Y_DividerBuilder()
                    .setBottomSideLine(true, 0xffffffff, 5, 0, 0).create();
        }
        return divider;
    }
}
