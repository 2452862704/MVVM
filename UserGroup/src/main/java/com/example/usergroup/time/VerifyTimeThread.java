package com.example.usergroup.time;

import android.os.Handler;

public class VerifyTimeThread extends Thread{

    private Handler handler;

    public VerifyTimeThread(Handler handler){
        this.handler = handler;
    }

    @Override
    public void run() {
        super.run();
        int i = 60;
        while (i>0){
            i--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.sendEmptyMessage(100);
        }
    }
}
