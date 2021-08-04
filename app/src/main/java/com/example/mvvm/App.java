package com.example.mvvm;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alipay.sdk.app.EnvUtils;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openDebug();
        ARouter.openLog();
        ARouter.init(this);
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
    }
}
