package com.example.usergroup.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.mvvmcommon.mvvm.view.BaseActivity;
import com.example.usergroup.BR;
import com.example.usergroup.R;
import com.example.usergroup.data.entity.UserEventEntity;
import com.example.usergroup.databinding.ActivityEdtBinding;
import com.example.usergroup.viewmodel.EdtUserViewModel;
import com.example.usergroup.widget.PhotoPop;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

@Route(path = "/usergroup/edtuseractivity")
public class EdtUserActivity extends BaseActivity<ActivityEdtBinding, EdtUserViewModel> {

    private PhotoPop photoPop;

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
        return R.layout.activity_edt;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void selPhoto(UserEventEntity userEventEntity){
        if ("photo".equals(userEventEntity.action)) {
            photoPop = new PhotoPop(this);
            photoPop.show(v.getRoot());
        }
    }

    //获取选择头像
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            String path = data.getStringExtra("path");
            UserEventEntity userEventEntity = new UserEventEntity();
            userEventEntity.action="headimg";
            userEventEntity.icon = path;
            EventBus.getDefault().post(userEventEntity);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
