package com.example.networkmoudle.entity;

/**
 * 全部网络请求接口返回值的基类
 * */
public class BaseEntity {
    private int status;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
