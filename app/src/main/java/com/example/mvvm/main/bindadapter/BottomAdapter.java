package com.example.mvvm.main.bindadapter;

import android.graphics.Color;
import android.util.Log;
import android.widget.LinearLayout;
import androidx.databinding.BindingAdapter;

import com.example.mvvm.main.data.BottomConfigEntity;
import com.example.mvvm.main.widget.BottomButton;

import java.util.List;

/**
 * @description 自定义软件框架页底部导航linearlayout
 * databinding属性
 * @author zhangxinyi
 * */
public class BottomAdapter {

    @BindingAdapter(value = {"datas","selectListener"})
    public static void bottomConfig(LinearLayout linearLayout,
                                    List<BottomConfigEntity.Values> datas,
                                    BottomButton.SelectListener selectListener
    ){
        if (datas == null)
            return;
        if (datas.size() == 0)
            return;
        if (selectListener == null)
            return;
        linearLayout.removeAllViews();
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        for (int i = 0;i < datas.size();i ++){
            BottomConfigEntity.Values entity = datas.get(i);
            BottomButton button = new BottomButton(linearLayout.getContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    0,LinearLayout.LayoutParams.MATCH_PARENT);
            lp.weight = 1;
            button.setLayoutParams(lp);
            button.setId(i);
            Log.e("ZXY","onSelect:"+entity.selFlag);
            button.setSelFlag(entity.selFlag)
                    .setSelColor(Color.RED)
                    .setNomalColor(Color.GRAY)
                    .setShowPoint(false)
                    .setSelURLImg(entity.selImgURL)
                    .setNomalURLImg(entity.unSelImgURL)
                    .setListener(selectListener);
            linearLayout.addView(button);
        }
    }

}
