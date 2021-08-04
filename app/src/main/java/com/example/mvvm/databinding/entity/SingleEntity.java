package com.example.mvvm.databinding.entity;

import androidx.databinding.BaseObservable;

//databinding 使用数据源
public class SingleEntity extends BaseObservable {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        notifyChange();//刷新数据
    }
}
