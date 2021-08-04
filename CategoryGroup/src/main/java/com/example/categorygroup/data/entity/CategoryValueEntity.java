package com.example.categorygroup.data.entity;


import com.example.networkmoudle.entity.BaseEntity;

import java.util.List;

public class CategoryValueEntity extends BaseEntity {

    public List<DataBean>data;

    public static class DataBean{
        public int id;
        public String category_name;
        public String category_icon;
        public int parent_id;
    }

}
