package com.example.usergroup.data.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@org.greenrobot.greendao.annotation.Entity
public class UserEntityValues {
    @Id(autoincrement = false)
    public long id;
    public String user_name;
    public String user_pwd;
    public String user_mobile;
    public String user_icon;
    public String user_real_name;
    public String user_identity_card;
    public String user_nick_name;
    public String user_gender;
    public String user_birthday;
    public String user_address;
    public String user_sign;
    public String push_id;
    @Generated(hash = 883198267)
    public UserEntityValues(long id, String user_name, String user_pwd,
            String user_mobile, String user_icon, String user_real_name,
            String user_identity_card, String user_nick_name, String user_gender,
            String user_birthday, String user_address, String user_sign,
            String push_id) {
        this.id = id;
        this.user_name = user_name;
        this.user_pwd = user_pwd;
        this.user_mobile = user_mobile;
        this.user_icon = user_icon;
        this.user_real_name = user_real_name;
        this.user_identity_card = user_identity_card;
        this.user_nick_name = user_nick_name;
        this.user_gender = user_gender;
        this.user_birthday = user_birthday;
        this.user_address = user_address;
        this.user_sign = user_sign;
        this.push_id = push_id;
    }
    @Generated(hash = 1125714491)
    public UserEntityValues() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUser_name() {
        return this.user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public String getUser_pwd() {
        return this.user_pwd;
    }
    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }
    public String getUser_mobile() {
        return this.user_mobile;
    }
    public void setUser_mobile(String user_mobile) {
        this.user_mobile = user_mobile;
    }
    public String getUser_icon() {
        return this.user_icon;
    }
    public void setUser_icon(String user_icon) {
        this.user_icon = user_icon;
    }
    public String getUser_real_name() {
        return this.user_real_name;
    }
    public void setUser_real_name(String user_real_name) {
        this.user_real_name = user_real_name;
    }
    public String getUser_identity_card() {
        return this.user_identity_card;
    }
    public void setUser_identity_card(String user_identity_card) {
        this.user_identity_card = user_identity_card;
    }
    public String getUser_nick_name() {
        return this.user_nick_name;
    }
    public void setUser_nick_name(String user_nick_name) {
        this.user_nick_name = user_nick_name;
    }
    public String getUser_gender() {
        return this.user_gender;
    }
    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }
    public String getUser_birthday() {
        return this.user_birthday;
    }
    public void setUser_birthday(String user_birthday) {
        this.user_birthday = user_birthday;
    }
    public String getUser_address() {
        return this.user_address;
    }
    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }
    public String getUser_sign() {
        return this.user_sign;
    }
    public void setUser_sign(String user_sign) {
        this.user_sign = user_sign;
    }
    public String getPush_id() {
        return this.push_id;
    }
    public void setPush_id(String push_id) {
        this.push_id = push_id;
    }

}
