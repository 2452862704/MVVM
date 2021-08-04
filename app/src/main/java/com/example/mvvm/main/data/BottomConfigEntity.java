package com.example.mvvm.main.data;


import com.example.networkmoudle.entity.BaseEntity;

import java.util.List;

public class BottomConfigEntity extends BaseEntity {

    public List<Values>data;

    public static class Values{
        public String arouterURL;
        public String selImgURL;
        public String unSelImgURL;
        public boolean selFlag =false;//当前bottomButton是否被选中
    }

}
