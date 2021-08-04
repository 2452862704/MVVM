package com.example.mvvm.databinding;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.mvvm.databinding.entity.ItemEntity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AdapterViewModel extends ViewModel {

    public ObservableField<List<Object>>datas;

    public AdapterViewModel(){
        datas = new ObservableField<>();
        List<Object> list = new ArrayList<>();
        for (int i = 0;i < 15;i++){
            ItemEntity entity = new ItemEntity();
            entity.setName("name:"+i);
            list.add(entity);
        }
        datas.set(list);
    }

    public class ChildClick implements OnItemChildClickListener{

        @Override
        public void onItemChildClick(@NonNull @NotNull BaseQuickAdapter adapter, @NonNull @NotNull View view, int position) {

        }
    }

}
