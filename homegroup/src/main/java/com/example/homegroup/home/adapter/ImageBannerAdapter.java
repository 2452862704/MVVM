package com.example.homegroup.home.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homegroup.R;
import com.example.homegroup.home.data.entity.HomeBannerEntity;
import com.example.loadbitmap.LoadImage;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.util.BannerUtils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ImageBannerAdapter extends BannerAdapter<HomeBannerEntity,ImageBannerAdapter.ImgBannerHolder> {


    public ImageBannerAdapter(List<HomeBannerEntity> datas) {
        super(datas);
    }

    @Override
    public ImgBannerHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new ImgBannerHolder(BannerUtils.getView(parent, R.layout.item_home_img_banner));
    }

    @Override
    public void onBindView(ImgBannerHolder holder, HomeBannerEntity data, int position, int size) {
        LoadImage.loadMatchImg(data.imgUrl,holder.img);
    }

    class ImgBannerHolder extends RecyclerView.ViewHolder{
        ImageView img;
        public ImgBannerHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgbanner_item);
        }
    }
}
