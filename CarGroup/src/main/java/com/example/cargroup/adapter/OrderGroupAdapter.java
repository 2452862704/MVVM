package com.example.cargroup.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import com.example.cargroup.view.OrderCanelFragment;
import com.example.cargroup.view.OrderCompleteFragment;
import com.example.cargroup.view.OrderListFragment;
import com.example.cargroup.view.OrderPayFragment;
import com.example.cargroup.view.OrderReceivingFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class OrderGroupAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragments;
    private String[] titles = {"全部","待付款","待收货","已完成","已取消"};

    public OrderGroupAdapter(@NonNull @NotNull FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        fragments.add(new OrderListFragment());
        fragments.add(new OrderPayFragment());
        fragments.add(new OrderReceivingFragment());
        fragments.add(new OrderCompleteFragment());
        fragments.add(new OrderCanelFragment());
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
