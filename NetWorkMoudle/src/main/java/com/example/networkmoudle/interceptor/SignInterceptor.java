package com.example.networkmoudle.interceptor;


import com.example.networkmoudle.utils.MD5Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

public class SignInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        Request.Builder build = new Request.Builder();
        build.url(oldRequest.url());
        build.headers(oldRequest.headers());
        RequestBody oldBody = oldRequest.body();
        //获取requestbody中字符串
        Buffer buffer = new Buffer();
        oldBody.writeTo(buffer);
        String oldJson ="";
        byte[]buff = new byte[1024];
        while (buffer.read(buff)!=-1){
            oldJson+=new String(buff);
        }
        buffer.close();
        oldJson.trim();
//        LogUtils.e("oldJson:"+oldJson);
        //[text={json}]
//        oldJson = oldJson.substring(6,oldJson.lastIndexOf("}"));
//        oldJson+="}";
//        LogUtils.e("oldJson:"+oldJson);
        String values = "";
        String json = "";
        //由于每个接口当中请求参数不同，所以oldjson当中的key不能确定
        try {
            JSONObject job = new JSONObject(oldJson);
            //获取全部json中包含的key
            Iterator<String> it =  job.keys();
            while (it.hasNext()){
                values += job.getString(it.next());
            }
            String sign = MD5Utils.createSign(values);
            job.put("sign",sign);
            json = job.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        LogUtils.e("json:"+json);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"),json);
        build.post(body);
        return chain.proceed(build.build());
    }
}
