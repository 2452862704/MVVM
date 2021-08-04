package com.example.homegroup.seckill.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.homegroup.seckill.timmer.TimmerThread;

import java.lang.ref.WeakReference;

public class SecKillService extends Service {

    private long time = 0;//服务器接口返回的时间戳->秒杀商品时使用
    private TimeHandler timeHandler;//Service下的handler->接收定时线程传来的消息
    //更新服务器时间戳,time+=1;
    private TimmerThread timeThread;//定时线程

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        time = intent.getLongExtra("time",0);
        timeHandler = new TimeHandler(this);
        timeThread = new TimmerThread(timeHandler);
        timeThread.start();
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new SecKillBind();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timeThread.flag = false;//关闭定时线程
    }

    //自定义bind声明Activity bind调用方法
    public class SecKillBind extends Binder{

        public long getTime(){
            return SecKillService.this.time;
        }

        public void setTime(long time){
            SecKillService.this.time = time;
        }

        public SecKillService getService(){
            return SecKillService.this;
        }
    }

    private static class TimeHandler extends Handler{
        WeakReference<SecKillService>weakReference;
        public TimeHandler(SecKillService service){
            weakReference = new WeakReference<>(service);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100&&weakReference.get()!=null){
                weakReference.get().time+=1;
            }
        }
    }

}
