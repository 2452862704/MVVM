package com.example.cargroup.data.entity;


import com.example.networkmoudle.entity.BaseEntity;

import java.util.List;

public class AddressEntity extends BaseEntity {

    public List<Value>data;

    public static class Value{
        public int id;
        public String ship_address;
        public int ship_is_default;
        public String ship_user_mobile;
        public String ship_user_name;
        public int user_id;
    }

}
