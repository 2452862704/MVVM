package com.example.usergroup.viewmodel;

import androidx.databinding.ObservableField;

import com.blankj.utilcode.util.ToastUtils;
import com.example.mvvmcommon.mvvm.viewmodel.BaseViewModel;
import com.example.networkmoudle.entity.BaseEntity;
import com.example.usergroup.data.entity.UpdateImgEntity;
import com.example.usergroup.data.entity.UserDaoEntity;
import com.example.usergroup.data.entity.UserEntity;
import com.example.usergroup.data.entity.UserEntityValues;
import com.example.usergroup.data.entity.UserEventEntity;
import com.example.usergroup.model.EdtUserModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class EdtUserViewModel extends BaseViewModel<EdtUserModel> {

    public ObservableField<String>head=new ObservableField<>("");
    private String headUrl;
    private String headName;
    private UserEntityValues userEntityValues;
    public ObservableField<String>nick=new ObservableField<>();
    public ObservableField<String>phone=new ObservableField<>();
    public ObservableField<String>sign=new ObservableField<>();
    public ObservableField<Boolean>man = new ObservableField<>(true);
    public ObservableField<Boolean>woMan = new ObservableField<>(false);

    @Override
    protected void initData() {
        List<UserEntityValues>list = new UserDaoEntity().selectAll();
        if (list == null)
            return;
        if (list.size()==0)
            return;
        userEntityValues = list.get(0);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected EdtUserModel createModel() {
        return new EdtUserModel();
    }

    @Override
    protected void sucessFull(BaseEntity entity) {
         if (entity.getMessage().equals("修改成功")){
             UserEntity userEntity = (UserEntity) entity;
             new UserDaoEntity().update(userEntity.data);
             UserEventEntity userEventEntity = new UserEventEntity();
             userEventEntity.action = "updateUser";
             EventBus.getDefault().post(userEventEntity);
             finish();
         }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void selPhoto(UserEventEntity userEventEntity){
        if ("headimg".equals(userEventEntity.action)) {
            //上传头像操作
            headName = System.currentTimeMillis()+".jpg";
            m.requestUploadImg(userEventEntity.icon,headName)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<UpdateImgEntity>() {
                        @Override
                        public void onSubscribe(@NotNull Disposable d) {
                            compositeDisposable.add(d);
                            ToastUtils.showShort("上传头像中");
                        }

                        @Override
                        public void onNext(@NotNull UpdateImgEntity updateImgEntity) {
                            head.set(userEventEntity.icon);
                            headUrl="https://118.195.161.134:8077/fileDownload?fileName="+headName;
                        }

                        @Override
                        public void onError(@NotNull Throwable e) {
                            ToastUtils.showShort("上传头像出错");
                        }

                        @Override
                        public void onComplete() {
                            ToastUtils.showShort("上传头像完成");
                        }
                    });

        }
    }

    public void finish(){
        getUiChangeLiveData().getFinishActivity().setValue(""+System.currentTimeMillis());
    }

    public void selPhoto(){
        UserEventEntity userEventEntity = new UserEventEntity();
        userEventEntity.action = "photo";
        EventBus.getDefault().post(userEventEntity);
    }

    public void save(){
        if (nick.get() == null){
            ToastUtils.showShort("请输入昵称");
            return;
        }
        if (phone.get() == null){
            ToastUtils.showShort("请输入手机");
            return;
        }
        if (sign.get() == null){
            ToastUtils.showShort("请输入签名");
            return;
        }
        Map<String,Object>map=new HashMap<>();
        if (man.get())
            map.put("gender","男");
        if (woMan.get())
            map.put("gender","女");
        map.put("id",userEntityValues.id);
        map.put("sign",sign.get());
        map.put("userIcon",headUrl);
        map.put("userName",nick.get());
        m.requestEdtUser(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }
}
