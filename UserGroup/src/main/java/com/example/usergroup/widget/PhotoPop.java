package com.example.usergroup.widget;

import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.usergroup.R;

public class PhotoPop implements View.OnClickListener {

    private Activity context;
    private View view;
    private Button camera_btn,photo_btn,canel_btn;
    private PopupWindow popupWindow;

    public PhotoPop(Activity ctx){
        context = ctx;
        view = LayoutInflater.from(context).inflate(R.layout.pop_user,null);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        camera_btn = view.findViewById(R.id.create_live_btn);
        camera_btn.setText("相机");
        camera_btn.setOnClickListener(this);
        photo_btn = view.findViewById(R.id.create_video_btn);
        photo_btn.setText("相册");
        photo_btn.setOnClickListener(this);
        canel_btn = view.findViewById(R.id.create_canel_btn);
        canel_btn.setOnClickListener(this);
    }

    public void show(View rootView){
        popupWindow.showAtLocation(rootView, Gravity.BOTTOM,0,0);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = new Intent(context, PhotoActivity.class);
        if (R.id.create_live_btn == id){
            //开启相机
            intent.putExtra("type",PhotoActivity.CAMERACODE);
            context.startActivityForResult(intent,1);
        }else if (R.id.create_video_btn == id){
            //开启相册
            intent.putExtra("type",PhotoActivity.PHOTOCODE);
            context.startActivityForResult(intent,1);
        }
        popupWindow.dismiss();
    }
}
