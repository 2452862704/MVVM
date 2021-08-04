package com.example.cargroup.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.cargroup.R;

@Route(path = "/cargroup/carlistactivity")
public class CarListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carlist);
        Fragment fragment = (Fragment) ARouter.getInstance().build("/cargroup/carfragment").navigation();
        getSupportFragmentManager().beginTransaction().replace(R.id.carlist_group,fragment)
                .commit();
    }
}
