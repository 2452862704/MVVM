package com.example.mvvmcommon.mvvm.viewmodel;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public interface IViewModel extends LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate();
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy();
}
