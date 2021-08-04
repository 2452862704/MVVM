package com.example.homegroup.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.homegroup.R;
import com.example.homegroup.home.data.entity.HomeBannerEntity;
import com.example.loadbitmap.LoadImage;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeTopAdapter extends PagerAdapter {

    private List<HomeBannerEntity> datas;

    public HomeTopAdapter(List<HomeBannerEntity> datas){
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull @NotNull View view, @NonNull @NotNull Object object) {
        return view == object;
    }

    @NonNull
    @NotNull
    @Override
    public Object instantiateItem(@NonNull @NotNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.itme_home_top,null);
        ImageView img = view.findViewById(R.id.home_top_img);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(lp);
        container.addView(view);
        LoadImage.loadMatchImg(datas.get(position).imgUrl,img);
        return view;
    }

    @Override
    public void destroyItem(@NonNull @NotNull ViewGroup container,
                            int position, @NonNull @NotNull Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
