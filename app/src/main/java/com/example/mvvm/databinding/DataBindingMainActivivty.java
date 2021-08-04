package com.example.mvvm.databinding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mvvm.R;

public class DataBindingMainActivivty extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bindmain);
        findViewById(R.id.bind1).setOnClickListener(this);
        findViewById(R.id.bind2).setOnClickListener(this);
        findViewById(R.id.bind3).setOnClickListener(this);
        findViewById(R.id.bind4).setOnClickListener(this);
        findViewById(R.id.bind5).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.bind1:
                startActivity(new Intent(this, BasicActivity.class));
                break;
            case R.id.bind2:
                startActivity(new Intent(this, SingleActivity.class));
                break;
            case R.id.bind3:
                startActivity(new Intent(this, BindActivity.class));
                break;
            case R.id.bind4:
                startActivity(new Intent(this, BindFragmentActivity.class));
                break;
            case R.id.bind5:
                startActivity(new Intent(this, AdapterActivity.class));
                break;
        }
    }
}
