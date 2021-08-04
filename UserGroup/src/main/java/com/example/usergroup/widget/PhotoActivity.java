package com.example.usergroup.widget;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;


import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.io.File;

public class PhotoActivity extends AppCompatActivity {

    private int REQUESTPERMISSIONCODE = 100;
    private int REQUESTPHOTOCODE = 101;
    private int REQUESTCAMERACODE = 102;
    public static int CAMERACODE = 0;
    public static int PHOTOCODE = 1;
    private Uri uri;//开启相机时，传递到相机设置相机保存图片路径
    private File file;//开启相机时，保存图片路径
    private int type = -1;//popupwindow开启是传递过来参数，调用开启相机/图库

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getIntent().getIntExtra("type",-1);
        requestPermission();
        if (type == CAMERACODE){
            //开启图库
            openCamera();
        }else if (type==PHOTOCODE){
            //开启相机

            openPhoto();
        }
    }


    //申请权限方法
    private void requestPermission(){
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.M)
            return;
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_EXTERNAL_STORAGE)!=
                PackageManager.PERMISSION_GRANTED&&
                ContextCompat.checkSelfPermission(
                        this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!=
                        PackageManager.PERMISSION_GRANTED&&
                ContextCompat.checkSelfPermission(
                        this, Manifest.permission.CAMERA)!=
                        PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA},
                    REQUESTPERMISSIONCODE);
        }
    }

    //获取申请结果
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean flag = true;
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_EXTERNAL_STORAGE)!=
                PackageManager.PERMISSION_GRANTED)
            flag = false;
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!=
                PackageManager.PERMISSION_GRANTED)
            flag = false;
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA)!=
                PackageManager.PERMISSION_GRANTED)
            flag = false;
        if (!flag){
            ToastUtils.showShort("请手动赋予当前应用程序权限");
            finish();
        }
        if (type == 0){
            //开启图库
            openPhoto();
        }else if (type==1){
            //开启相机
            openCamera();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode!=RESULT_OK){
            finish();
            return;
        }
        //根据requestCode区分图库或相机
        if (REQUESTPHOTOCODE == requestCode){
            Cursor cursor =  getContentResolver().query(data.getData(),new String[]{MediaStore.Images.Media.DATA},
                    null,null,null);
            String path = null;
            while (cursor.moveToNext()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
            LogUtils.e("图库获取的本地图片路径");
//            BitmapFactory.Options op = new BitmapFactory.Options();
//            op.inSampleSize = 2;
//            Bitmap bitmap = BitmapFactory.decodeFile(path);
            LogUtils.e("图库获取的本地图片路径"+path);
//            img.setImageBitmap(bitmap);
            Intent intent = new Intent();
            intent.putExtra("path",path);
            setResult(RESULT_OK,intent);
            finish();
        }
        if (REQUESTCAMERACODE == requestCode){
            String path = null;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
//                Cursor cursor = getContentResolver().query(uri, new String[]{
//                                MediaStore.Images.Media.DATA},
//                        null, null, null);
//                LogUtils.show("onActivityResult:"+(cursor==null));
//                if (cursor != null && cursor.moveToFirst()) {
//                    int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//                    LogUtils.show("onActivityResult->columnIndex:"+columnIndex);
//                    path = cursor.getString(columnIndex);
//                    cursor.close();
//                }
//            }else {
            path = file.getAbsolutePath();
//            }
            LogUtils.e("相机获取的本地图片路径"+path);
//            Bitmap bitmap = BitmapFactory.decodeFile(path);
//            img.setImageBitmap(bitmap);
            Intent intent = new Intent();
            intent.putExtra("path",path);
            setResult(RESULT_OK,intent);
            finish();
        }

    }

    //开启图库
    private void openPhoto(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,REQUESTPHOTOCODE);
    }
    //开启相机
    private void openCamera(){
        String name = ""+System.currentTimeMillis();
        file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath(),name+".jpg");
        //判断SDK版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            //SDK版本大于10->兼容处理沙箱机制
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.DISPLAY_NAME,name);
            values.put(MediaStore.Images.Media.RELATIVE_PATH,"Pictures");
            values.put(MediaStore.Images.Media.MIME_TYPE,"image/JPEG");
            uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            //7-9
            uri = FileProvider.getUriForFile(this,"com.bw.usergroup.camera_file",file);
        }else {
            //低版本
            uri = Uri.fromFile(file);
        }
        LogUtils.e("REQUESTCAMERACODE:"+file.getAbsolutePath());
        LogUtils.e("REQUESTCAMERACODE:"+uri.toString());
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        startActivityForResult(intent,REQUESTCAMERACODE);

    }


}
