package com.example.homegroup.seckill.timmer;

import android.os.Handler;

public class TimmerThread extends Thread{

    private Handler handler;
    public boolean flag = true;
    public TimmerThread(Handler handler){
        this.handler = handler;
    }

    @Override
    public void run() {
        super.run();
        while (flag){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (handler == null){
                flag = false;
                continue;
            }
            handler.sendEmptyMessage(100);
        }
    }
}
