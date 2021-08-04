package com.example.homegroup.home.binds;

import android.text.TextUtils;
import android.widget.ImageView;
import androidx.databinding.BindingAdapter;

import com.example.loadbitmap.LoadImage;

public class HomeImgBind {

    @BindingAdapter(value = {"imgUrl"},requireAll = false)
    public static void bindImg(ImageView img,String imgUrl){
        if (TextUtils.isEmpty(imgUrl))
            return;
        LoadImage.loadMatchImg(imgUrl,img);
    }

}
