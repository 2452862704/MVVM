package com.example.networkmoudle.utils;

import android.content.SharedPreferences;

import com.blankj.utilcode.util.Utils;

public class SpUtils {
    private static String name = "netcatch";

    public static void saveData(String key,String value){
        SharedPreferences sp = Utils.getApp().getSharedPreferences(name,0);
        sp.edit().putString(key,value).commit();
    }

    public static String readData(String key){
        SharedPreferences sp = Utils.getApp().getSharedPreferences(name,0);
        return sp.getString(key,"");
    }


}
