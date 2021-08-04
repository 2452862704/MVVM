package com.example.mvvm.main.viewmodel;

import androidx.databinding.ObservableField;
import com.example.mvvm.main.data.BottomConfigEntity;
import com.example.mvvm.main.model.MainModel;
import com.example.mvvm.main.widget.BottomButton;
import com.example.mvvmcommon.mvvm.viewmodel.BaseViewModel;
import com.example.networkmoudle.entity.BaseEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends BaseViewModel<MainModel> implements BottomButton.SelectListener {

    //DataBinding提供的数据容器ObservableField->观察者模式->被观察者->
    //作用包含databinding使用的数据->数据改变时databinding框架下的观察者
    //观察到数据改变，获取到改变后的数据->databinding框架自动设置到XML中
    public ObservableField<List<BottomConfigEntity.Values>>bottomes;//userCenter/getConfig返回json中的data集合

    @Override
    protected void initData() {
        bottomes = new ObservableField<List<BottomConfigEntity.Values>>();
        Map<String,Object>map = new HashMap<>();
        map.put("imie",""+System.currentTimeMillis());
        m.requestBottomConfig(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    protected MainModel createModel() {
        return new MainModel();
    }

    @Override
    protected void sucessFull(BaseEntity entity) {
        if (entity instanceof BottomConfigEntity){
            BottomConfigEntity bottomConfigEntity = (BottomConfigEntity) entity;
            bottomConfigEntity.data.get(0).selFlag = true;//设置默认选中首页bottomButton
            bottomes.set(bottomConfigEntity.data);
            getUiChangeLiveData().getSelFragment().setValue(bottomConfigEntity.data.get(0).arouterURL);
        }
    }

    //buttomButton选中回掉监听器
    @Override
    public void onSelect(int id) {
        List<BottomConfigEntity.Values> datas = new ArrayList(bottomes.get());
        for (int i = 0;i < datas.size();i++){
            if (id == i){
                datas.get(i).selFlag=true;
                getUiChangeLiveData().getSelFragment().setValue(datas.get(i).arouterURL);
            }else
                datas.get(i).selFlag=false;
        }
        bottomes.set(datas);

    }
}
