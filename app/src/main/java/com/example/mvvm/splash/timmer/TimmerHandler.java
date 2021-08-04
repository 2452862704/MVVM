package com.example.mvvm.splash.timmer;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

public class TimmerHandler extends Handler {

    private WeakReference<TimmerCallBack>weakReference;

    public TimmerHandler(TimmerCallBack callBack){
        weakReference = new WeakReference<>(callBack);
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        int second = (int) msg.obj;
        if (weakReference.get()!=null)
            weakReference.get().callTimmer(second);
    }

    public interface TimmerCallBack{
        void callTimmer(int second);
    }

}
