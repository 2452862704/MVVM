package com.example.messagegroup.data.entity;


import com.example.networkmoudle.entity.BaseEntity;

import java.util.List;

public class MessageEntity extends BaseEntity {

    public List<Values>data;

    public static class Values{
        public int id;
        public String msg_icon;
        public String msg_title;
        public String msg_content;
        public String msg_time;
        public int user_id;
    }
}
