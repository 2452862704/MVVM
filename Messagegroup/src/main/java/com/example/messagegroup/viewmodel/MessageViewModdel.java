package com.example.messagegroup.viewmodel;

import androidx.databinding.ObservableField;

import com.example.messagegroup.adapter.MessageAdapter;
import com.example.messagegroup.data.entity.MessageEntity;
import com.example.messagegroup.model.MessageModel;
import com.example.mvvmcommon.mvvm.viewmodel.BaseViewModel;
import com.example.networkmoudle.entity.BaseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MessageViewModdel extends BaseViewModel<MessageModel> {

    private List<MessageEntity.Values>datas;
    public ObservableField<MessageAdapter>adapter=new ObservableField<>();

    @Override
    protected void initData() {
        adapter.set(new MessageAdapter());
        Map<String,Object>map = new HashMap<>();
        map.put("id","10");
        m.requestMessage(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    protected MessageModel createModel() {
        return new MessageModel();
    }

    @Override
    protected void sucessFull(BaseEntity entity) {
        if (entity instanceof MessageEntity){
            MessageEntity messageEntity = (MessageEntity) entity;
            adapter.get().setNewInstance(messageEntity.data);
        }
    }
}
