package com.example.usergroup.binds;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.example.loadbitmap.LoadImage;
import com.example.usergroup.R;


public class UserImgBind {

    @BindingAdapter(value = {"url"},requireAll = false)
    public static void imageBind(ImageView img,String url){
        if (url==null) {
            img.setImageResource(R.drawable.icon_default_user);
            return;
        }
        if (url.isEmpty()) {
            img.setImageResource(R.drawable.icon_default_user);
            return;
        }
        LoadImage.loadCircleImg(url,img);
    }

}
