package com.example.mvvmcommon.mvvm.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.mvvmcommon.field.UIField;
import com.example.mvvmcommon.mvvm.viewmodel.BaseViewModel;

import org.jetbrains.annotations.NotNull;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public abstract class BaseFragment <V extends ViewDataBinding,
        VM extends BaseViewModel> extends
        Fragment implements IView {

    protected V v;
    protected VM vm;
    private LayoutInflater inflater;

    @Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        initViewModel();//创建viewmodel对象
        registerUIChange();
        binding();
        getLifecycle().addObserver(vm);
        return v.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //解除livedata注册
        if (vm.getUiChangeLiveData()!=null){
            unRegisterUIChange();
        }
        v.unbind();
        getLifecycle().removeObserver(vm);
    }

    @Override
    public void binding() {
        v = DataBindingUtil.inflate(inflater,bindLayout(),null,false);
        v.setVariable(initVariable(),vm);
    }

    protected abstract int initVariable();

    @Override
    public void initViewModel() {
        ParameterizedType type = (ParameterizedType)
                getClass().getGenericSuperclass();
        Type[] types = type.getActualTypeArguments();
        Class clazz = (Class) types[1];
        vm = find(clazz);
    }
    private <VM extends BaseViewModel>VM find(Class clz){
        return (VM) new ViewModelProvider(this,
                new ViewModelProvider.NewInstanceFactory()).get(clz);
    }

    //注册到viewmodel下的Livedata
    private void registerUIChange(){
        vm.getUiChangeLiveData().getShowDialog().observe(this,showDialogOB);
        vm.getUiChangeLiveData().getHideDialog().observe(this,hideDialogOB);
        vm.getUiChangeLiveData().getFinishActivity().observe(this,finishActivityOB);
        vm.getUiChangeLiveData().getStartActivity().observe(this,startActivityOB);
    }

    private void unRegisterUIChange(){
        vm.getUiChangeLiveData().getShowDialog().removeObserver(showDialogOB);
        vm.getUiChangeLiveData().getShowDialog().removeObserver(hideDialogOB);
        vm.getUiChangeLiveData().getShowDialog().removeObserver(finishActivityOB);
        vm.getUiChangeLiveData().getShowDialog().removeObserver(startActivityOB);
    }

    //showdialog观察者
    Observer<String> showDialogOB=new Observer<String>() {
        @Override
        public void onChanged(String s) {
            showDialog();
        }
    };
    //hidedialog观察者
    Observer<String>hideDialogOB = new Observer<String>() {
        @Override
        public void onChanged(String s) {
            hideDialog();
        }
    };
    //finishActivity观察者
    Observer<String>finishActivityOB = new Observer<String>() {
        @Override
        public void onChanged(String s) {
            finishActivity();
        }
    };
    //startActivity观察者
    Observer<Map<String,Object>>startActivityOB = new Observer<Map<String, Object>>() {
        @Override
        public void onChanged(Map<String, Object> map) {
            startActivity(map);
        }
    };

    private void showDialog(){

    }

    private void hideDialog(){

    }

    private void finishActivity(){
        getActivity().finish();
    }

    private void startActivity(Map<String,Object>map){
        int type = (int) map.get(UIField.ACTIONTYPE);
        if (type == UIField.AROUTERSTART){
            //路由启动
            String path = (String) map.get(UIField.ACTIONROUTERKEY);
            Bundle bundle = (Bundle) map.get(UIField.VALUESKEY);
            //路由启动
            if (bundle == null){
                ARouter.getInstance().build(path).navigation();
            }else {
                ARouter.getInstance().build(path).with(bundle).navigation();
            }
        }else if (type == UIField.NATIVESTART){
            Class clazz = (Class) map.get(UIField.NATIVEACTION);
            startActivity(new Intent(getActivity(),clazz));
        }
    }

}
