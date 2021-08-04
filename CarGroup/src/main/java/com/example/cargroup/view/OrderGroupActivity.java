package com.example.cargroup.view;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.BarUtils;
import com.example.cargroup.R;
import com.example.cargroup.adapter.OrderGroupAdapter;
import com.flyco.tablayout.SlidingTabLayout;

@Route(path = "/cargroup/ordergroupactivity")
public class OrderGroupActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout order_group_root;
    private ImageView order_group_back_img;
    private SlidingTabLayout order_group_tab;
    private ViewPager order_group_vp;
    private OrderGroupAdapter adapter;
    private int index = 0;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordergroup);
        index = getIntent().getExtras().getInt("index");
        order_group_root = findViewById(R.id.order_group_root);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            //把状态栏标记为浅色，然后状态栏的字体颜色自动转换为深色
        }
        int color = getResources().getColor(R.color.common_blue);
        BarUtils.setStatusBarColor(this, color);
        //添加让出状态栏高度->防止contentView布局绘制到状态栏上
        BarUtils.addMarginTopEqualStatusBarHeight(order_group_root);
        init();
    }

    private void init(){
        order_group_back_img = findViewById(R.id.order_group_back_img);
        order_group_tab = findViewById(R.id.order_group_tab);
        order_group_vp = findViewById(R.id.order_group_vp);
        order_group_back_img.setOnClickListener(this);
        adapter = new OrderGroupAdapter(getSupportFragmentManager());
        order_group_vp.setAdapter(adapter);
        order_group_tab.setViewPager(order_group_vp);
        order_group_vp.setCurrentItem(index);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
