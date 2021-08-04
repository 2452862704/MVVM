package com.example.homegroup.home.data.entity;

public class HomeMenuEntity {

    public int iconId;//图片id
    public String title;//标题
    public String pagePath;//Arouter路由地址
    public HomeMenuEntity(int iconId,String title,String pagePath){
        this.iconId = iconId;
        this.title = title;
        this.pagePath = pagePath;
    }
}
