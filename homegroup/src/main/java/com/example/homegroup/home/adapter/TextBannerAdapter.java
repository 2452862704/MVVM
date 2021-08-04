package com.example.homegroup.home.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homegroup.R;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.util.BannerUtils;
import java.util.List;

public class TextBannerAdapter extends BannerAdapter<String,TextBannerAdapter.TextBannerHolder> {


    public TextBannerAdapter(List<String> datas) {
        super(datas);
    }

    @Override
    public TextBannerHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new TextBannerHolder(BannerUtils.getView(parent,R.layout.item_home_txt_banner));
    }

    @Override
    public void onBindView(TextBannerHolder holder, String data, int position, int size) {
         holder.title.setText(data);
    }

    class TextBannerHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public TextBannerHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.txtbanner_item);
        }
    }
}
