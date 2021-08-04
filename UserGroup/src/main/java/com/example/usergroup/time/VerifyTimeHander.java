package com.example.usergroup.time;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

public class VerifyTimeHander extends Handler {

    private WeakReference<VerifyTimeListener>weakReference;
    private int time = 60;

    public VerifyTimeHander(VerifyTimeListener listener){
        weakReference = new WeakReference<>(listener);
    }

    public void initTime() {
        this.time = 60;
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        if (msg.what == 100&&weakReference.get()!=null){
            time -=1;
            weakReference.get().timeCall(time);
        }
    }

    public interface VerifyTimeListener{
        void timeCall(int time);
    }

}
