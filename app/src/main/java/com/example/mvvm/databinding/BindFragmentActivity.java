package com.example.mvvm.databinding;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mvvm.R;

public class BindFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_fragment);
        getSupportFragmentManager().beginTransaction().add(R.id.fg_relative
                ,new BindFragment()).commit();
    }
}
