package com.example.homegroup.seckill.view;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.homegroup.BR;
import com.example.homegroup.R;
import com.example.homegroup.databinding.ActivitySeckillBinding;
import com.example.homegroup.seckill.data.entity.TimeAction;
import com.example.homegroup.seckill.service.SecKillService;
import com.example.homegroup.seckill.viewmodel.SecKillesViewModel;
import com.example.mvvmcommon.mvvm.view.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

@Route(path = "/homegroup/seckillesactivity")
public class SecKillesActivity extends BaseActivity<ActivitySeckillBinding, SecKillesViewModel> {
    private SecKillService.SecKillBind bind;
    @Override
    protected boolean isFullScreen() {
        return false;
    }

    @Override
    protected int initVariable() {
        return BR.vm;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_seckill;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        Intent intent = new Intent(this, SecKillService.class);
        stopService(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this,SecKillService.class);
        bindService(intent,connection,BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(connection);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void seckillTime(Long time){
        bind.setTime(time);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getTime(String action){
        long time = bind.getTime();
        TimeAction action1 = new TimeAction();
        action1.time = time;
        EventBus.getDefault().post(action1);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        Intent intent = new Intent(this, SecKillService.class);
        startService(intent);

    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            SecKillesActivity.this.bind = (SecKillService.SecKillBind) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

}
