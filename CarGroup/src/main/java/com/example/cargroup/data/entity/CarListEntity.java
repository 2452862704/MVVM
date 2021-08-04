package com.example.cargroup.data.entity;


import com.example.networkmoudle.entity.BaseEntity;

import java.util.List;

public class CarListEntity extends BaseEntity {

    public List<Values>data;

    public static class Values{
        public int id;
        public int goods_id;
        public String goods_desc;
        public String goods_icon;
        public String goods_price;
        public int goods_count;
        public int user_id;
        public String goods_sku;
        public boolean selFlag = true;//选中标志
        public boolean edtFlag = false;//编辑标志

    }
}
