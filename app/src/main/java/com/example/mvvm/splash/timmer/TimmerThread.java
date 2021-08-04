package com.example.mvvm.splash.timmer;

import android.os.Message;

public class TimmerThread extends Thread{

    private TimmerHandler handler;
    public TimmerThread(TimmerHandler handler){
        this.handler = handler;
    }

    @Override
    public void run() {
        super.run();
        for (int i = 5;i >0;i--){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message msg = handler.obtainMessage();
            msg.obj = i;
            handler.sendMessage(msg);
        }
    }
}
