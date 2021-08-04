package com.example.loadbitmap;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

public class LoadImage {

    public static void loadMatchImg(String url, ImageView img){

        Glide.with(img.getContext()).load(url)
                .placeholder(R.drawable.cutdown_dialog_bg)
                .error(R.drawable.tt_default_image_error)
                .centerCrop()
                .into(img);
    }

    public static void loadImg(String url, ImageView img){
        Glide.with(img.getContext()).load(url)
                .into(img);
    }

    public static void loadCircleImg(String url, ImageView img){
        Glide.with(img.getContext())
                .load(url)
                .error(R.drawable.tt_default_image_error)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(img);
    }

//    public static void loadRoundImg(String url, ImageView img,int radius){
//        Glide.with(img.getContext())
//                .load(url)
//                // 圆角效果
//                .transform(new MultiTransformation(new RoundedCornersTransformation(radius, 0)))
//                .into(img);
//    }

}
