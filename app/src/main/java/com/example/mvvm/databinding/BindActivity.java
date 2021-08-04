package com.example.mvvm.databinding;

import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.mvvm.R;
import com.example.mvvm.databinding.entity.SingleEntity;

public class BindActivity extends AppCompatActivity {

    private SingleEntity entity;
    private ActivityBindBinding binding;
    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bind);
        entity = new SingleEntity();
        binding.setEntity(entity);
        binding.setSingleClick(new SingleClick());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.unbind();
    }

    public class SingleClick{
        public void btnClick(){
            //xml中button点击回调监听
            //entity->content复制并刷新到UI
//            entity.setContent("夏瑞诺是个好人");
            Toast.makeText(BindActivity.this,entity.getContent(),Toast.LENGTH_LONG).show();
        }
    }

}
