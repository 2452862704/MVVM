package com.example.mvvmcommon.mvvm.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.example.mvvmcommon.R;
import com.example.mvvmcommon.field.UIField;
import com.example.mvvmcommon.mvvm.viewmodel.BaseViewModel;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public abstract class BaseActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity implements IView {

    protected V v;
    protected VM vm;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();//创建viewmodel对象
        registerUIChange();
        binding();//关联注册databing
        //修改状态栏当中的图片以及文字颜色
        if (isFullScreen()){
            ScreenUtils.setFullScreen(this);
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                //把状态栏标记为浅色，然后状态栏的字体颜色自动转换为深色
            }
            int color = getResources().getColor(setColor());
            BarUtils.setStatusBarColor(this, color);
            //添加让出状态栏高度->防止contentView布局绘制到状态栏上
            BarUtils.addMarginTopEqualStatusBarHeight(v.getRoot());
        }
        getLifecycle().addObserver(vm);//绑定生命周期到View Model
    }

    protected int setColor(){
        return R.color.common_blue;
    }

    protected abstract boolean isFullScreen();

    @Override
    protected void onDestroy() {
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
        v = DataBindingUtil.setContentView(this,bindLayout());
        v.setVariable(initVariable(),vm);
    }

    protected abstract int initVariable();

    @Override
    public void initViewModel() {
        //通过反射获取范性类型
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
        vm.getUiChangeLiveData().getSelFragment().observe(this,selFragmentOB);
    }

    private void unRegisterUIChange(){
        vm.getUiChangeLiveData().getShowDialog().removeObserver(selFragmentOB);
        vm.getUiChangeLiveData().getShowDialog().removeObserver(hideDialogOB);
        vm.getUiChangeLiveData().getShowDialog().removeObserver(finishActivityOB);
        vm.getUiChangeLiveData().getShowDialog().removeObserver(startActivityOB);
        vm.getUiChangeLiveData().getSelFragment().removeObserver(selFragmentOB);
    }

    //showdialog观察者
    Observer<String>showDialogOB=new Observer<String>() {
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
    Observer<String>selFragmentOB = new Observer<String>() {
        @Override
        public void onChanged(String s) {
            selFragment(s);
        }
    };

    protected void selFragment(String s){

    }

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
        finish();
    }

    private void startActivity(Map<String,Object>map){
        int type = (int) map.get(UIField.ACTIONTYPE);
        if (type == UIField.AROUTERSTART){
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
            Bundle bundle = (Bundle) map.get(UIField.VALUESKEY);
            Log.i("fkt", "startActivity: "+bundle);
            if (bundle == null)
                startActivity(new Intent(this,clazz));
            else {
                Intent intent = new Intent(this,clazz);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
    }

}
