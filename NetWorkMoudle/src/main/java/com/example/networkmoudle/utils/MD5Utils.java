package com.example.networkmoudle.utils;

import com.blankj.utilcode.util.EncryptUtils;

import java.util.Arrays;

public class MD5Utils {

    /**
     * 针对服务器中每个接口中sign参数的加密方法
     * */
    public static String createSign(String oldStr){
        if (oldStr.isEmpty())
            return "";
        char[]arrs = oldStr.toCharArray();
        Arrays.sort(arrs);
        String str = new String(arrs);
        return EncryptUtils.encryptMD5ToString(str+"tamboo").toLowerCase();
    }

}
