package com.example.homegroup.seckill.data.entity;


import com.example.networkmoudle.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

public class SecKillEntity extends BaseEntity {

    public List<Value>data;

    public static class Value implements Serializable {

        public int id;
        public int category_id;
        public int goods_num;
        public String goods_desc;
        public String goods_default_icon;
        public String goods_default_price;
        public String goods_banner;
        public String goods_detail_one;
        public String goods_detail_two;
        public int goods_sales_count;
        public int goods_stock_count;
        public String goods_code;
        public String goods_default_sku;
        public long endtime;
        public long starttime;
        public long nowtime;
    }

}
