package com.example.categorygroup.data.entity;

public class CategoryEntity {
    public int parent_id;
    public String name;
    public boolean selFlag = false;

    public CategoryEntity(int parent_id,String name){
        this.parent_id = parent_id;
        this.name = name;
    }

}
