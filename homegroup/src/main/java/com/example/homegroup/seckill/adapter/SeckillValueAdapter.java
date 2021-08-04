package com.example.homegroup.seckill.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homegroup.R;
import com.example.loadbitmap.LoadImage;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.util.BannerUtils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SeckillValueAdapter extends BannerAdapter<String,
        SeckillValueAdapter.SeckillBannerHolder> {

    public SeckillValueAdapter(List<String> datas) {
        super(datas);
    }

    @Override
    public SeckillBannerHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new SeckillBannerHolder(BannerUtils.getView(parent, R.layout.item_home_img_banner));
    }

    @Override
    public void onBindView(SeckillBannerHolder holder, String data, int position, int size) {
        LoadImage.loadMatchImg(data,holder.img);
    }

    class SeckillBannerHolder extends RecyclerView.ViewHolder{
        ImageView img;
        public SeckillBannerHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgbanner_item);
        }
    }

}
