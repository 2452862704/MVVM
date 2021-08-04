package com.example.mvvmcommon.mvvm.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.mvvmcommon.field.NetWorkField;
import com.example.mvvmcommon.field.UIField;
import com.example.mvvmcommon.mvvm.model.IModel;
import com.example.networkmoudle.entity.BaseEntity;

import org.jetbrains.annotations.NotNull;
import java.util.Map;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseViewModel <M extends IModel>extends ViewModel implements IViewModel,
        Observer<BaseEntity> {

    protected M m;
    protected CompositeDisposable compositeDisposable;
    private UIChangeLiveData uiChangeLiveData;

    public BaseViewModel() {
    }

    protected void showMsg(String msg){
        ToastUtils.showShort(msg);
    }

    @Override
    public void onCreate() {
        compositeDisposable = new CompositeDisposable();
        m = createModel();
        requestAll();
    }

    //发起全部请求方法
    private void requestAll(){
        if (NetworkUtils.isConnected()){
            initData();
        }else{
            BaseEntity entity = new BaseEntity();
            entity.setStatus(NetWorkField.NETWORKERRO);
            entity.setMessage("无网络连接");
            Observable observable = Observable.just(entity);
            observable.subscribe(this);
        }
    }

    protected abstract void initData();

    public void refresh(){
        initData();
    }

    @Override
    public void onSubscribe(@NotNull Disposable d) {
        compositeDisposable.add(d);
        uiChangeLiveData.setShowDialog(UIField.SHOWDIALOG+
                System.currentTimeMillis());
    }

    @Override
    public void onNext(@NotNull BaseEntity baseEntity) {
        if (baseEntity.getStatus() == NetWorkField.NETWORKERRO){
            ToastUtils.showShort("无网络连接");
            return;
        }
        sucessFull(baseEntity);
    }

    @Override
    public void onError(@NotNull Throwable e) {
        ToastUtils.showShort(e.getMessage());
    }

    @Override
    public void onComplete() {
        uiChangeLiveData.setHideDialog(UIField.HIDEDIALOG+
                System.currentTimeMillis());
    }

    protected abstract M createModel();

    protected abstract void sucessFull(BaseEntity entity);

    @Override
    public void onDestroy() {
        if (compositeDisposable !=null){
            compositeDisposable.dispose();
            compositeDisposable.clear();
        }
    }

    public UIChangeLiveData getUiChangeLiveData() {
        if (uiChangeLiveData == null)
            uiChangeLiveData = new UIChangeLiveData();
        return uiChangeLiveData;
    }

    public class UIChangeLiveData{
        MutableLiveData<String>showDialog=new MutableLiveData<>();
        MutableLiveData<String>hideDialog=new MutableLiveData<>();
        MutableLiveData<String>finishActivity=new MutableLiveData<>();
        MutableLiveData<Map<String,Object>>startActivity=new MutableLiveData<>();
        MutableLiveData<String>selFragment = new MutableLiveData<>();

        public void setSelFragment(String selFragment) {
            this.selFragment.setValue(selFragment);
        }

        public MutableLiveData<String> getSelFragment() {
            return selFragment;
        }

        public MutableLiveData<String> getShowDialog() {
            return showDialog;
        }

        public void setShowDialog(String showDialogStr) {
            this.showDialog.setValue(showDialogStr);
        }

        public MutableLiveData<String> getHideDialog() {
            return hideDialog;
        }

        public void setHideDialog(String hideDialogStr) {
            this.hideDialog.setValue(hideDialogStr);
        }

        public MutableLiveData<String> getFinishActivity() {
            return finishActivity;
        }

        public void setFinishActivity(String finishActivityStr) {
            this.finishActivity.setValue(finishActivityStr);
        }

        public MutableLiveData<Map<String, Object>> getStartActivity() {
            return startActivity;
        }

        public void setStartActivity(Map<String, Object> startActivityMap) {
            this.startActivity.setValue(startActivityMap);
        }
    }

}
