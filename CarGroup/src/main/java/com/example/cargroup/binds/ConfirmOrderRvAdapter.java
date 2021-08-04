package com.example.cargroup.binds;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.cargroup.R;
import com.example.cargroup.adapter.ConfirmOrderAdapter;
import com.example.cargroup.widget.CarLineDivider;

public class ConfirmOrderRvAdapter {

    @BindingAdapter(value = {"confirmorderadapter",
            "shipclick","shipname","shipaddress",
            "shipflag"},requireAll = false)
    public static void confirmorderbind(RecyclerView recyclerView,
                                        ConfirmOrderAdapter confirmorderadapter,
                                        View.OnClickListener shipclick,
                                        String shipname,String shipaddress,
                                        Boolean shipflag){
        LinearLayoutManager manager = new LinearLayoutManager(recyclerView.getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        View headView = LayoutInflater.from(recyclerView.getContext())
                .inflate(R.layout.view_confirm_head,null,false);
        View footView = LayoutInflater.from(recyclerView.getContext())
                .inflate(R.layout.view_confirm_foot,null,false);
        //替换头布局以及脚布局
        confirmorderadapter.setHeaderView(headView);
        confirmorderadapter.setFooterView(footView);
        //处理头布局
        RelativeLayout mShipView = headView.findViewById(R.id.mShipView);
        mShipView.setOnClickListener(shipclick);
        TextView mSelectShipTv = headView.findViewById(R.id.mSelectShipTv);
        mSelectShipTv.setOnClickListener(shipclick);
        TextView mShipNameTv = headView.findViewById(R.id.mShipNameTv);
        TextView mShipAddressTv= headView.findViewById(R.id.mShipAddressTv);
        mShipNameTv.setText(shipname);
        mShipAddressTv.setText(shipaddress);
        if (shipflag){
            mShipView.setVisibility(View.VISIBLE);
            mSelectShipTv.setVisibility(View.GONE);
        }else {
            mShipView.setVisibility(View.GONE);
            mSelectShipTv.setVisibility(View.VISIBLE);
        }
        recyclerView.setAdapter(confirmorderadapter);
    }

    @BindingAdapter(value = {"orderadapter"},requireAll = false)
    public static void orderListRvBinds(RecyclerView recyclerView, BaseQuickAdapter orderadapter){
        LinearLayoutManager manager = new LinearLayoutManager(recyclerView.getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new CarLineDivider(recyclerView.getContext()));
        recyclerView.setAdapter(orderadapter);
    }

}
