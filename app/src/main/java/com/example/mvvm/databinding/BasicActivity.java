package com.example.mvvm.databinding;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.mvvm.R;


/**
 * databinding基本使用
 * */
public class BasicActivity extends AppCompatActivity {

    String content = "hehe";
    private ActivityBasicBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //当前activity关联databinding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_basic);
        //设置databinding下使用的数据源
        binding.setContent(content);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除databinding绑定
        binding.unbind();
    }
}
