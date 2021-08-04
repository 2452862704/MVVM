package com.example.cargroup.binds;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cargroup.adapter.ShipeAddressAdapter;

public class ShipAddressBinds {

    @BindingAdapter(value = {"shipeadapter"},requireAll = false)
    public static void shipBinds(RecyclerView recyclerView, ShipeAddressAdapter shipeadapter){
        LinearLayoutManager manager = new LinearLayoutManager(recyclerView.getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(shipeadapter);
    }

}
