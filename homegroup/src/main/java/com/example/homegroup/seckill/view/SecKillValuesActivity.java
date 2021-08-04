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
import com.example.homegroup.databinding.ActivitySeckillValuesBinding;
import com.example.homegroup.seckill.data.entity.SecKillEntity;
import com.example.homegroup.seckill.service.SecKillService;
import com.example.homegroup.seckill.viewmodel.SecKillValueViewModel;
import com.example.mvvmcommon.mvvm.view.BaseActivity;

@Route(path = "/homegroup/seckillvaluesactivity")
public class SecKillValuesActivity extends BaseActivity<ActivitySeckillValuesBinding, SecKillValueViewModel> {
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
        return R.layout.activity_seckill_values;
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        SecKillEntity.Value value = (SecKillEntity.Value) bundle.getSerializable("value");
        vm.value = value;
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            bind = (SecKillService.SecKillBind) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
