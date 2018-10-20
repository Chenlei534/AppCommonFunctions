package com.chenlei.other;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chen Lei
 * 作为Activity的基类，扩展了Activity的动态权限申请功能
 */
public class BaseActivity extends AppCompatActivity {
    private final static String TAG="BaseActivity";
    public static boolean AUDIO_PERMISSION=false;

    private static final int PERMISSIONS_REQUEST_CODE=3;
    private List<String> permissionList=new ArrayList<>();
    private String[] permissions=new String[]{
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPermission();
    }

    /**
     * 动态申请相关权限
     */
    public void getPermission(){
        //遍历要申请的权限列表，如果没有权限则添加到申请列表
        for(int i=0;i<permissions.length;i++){
            if(ContextCompat.checkSelfPermission(this,permissions[i])!= PackageManager.PERMISSION_GRANTED){
                permissionList.add(permissions[i]);
            }
        }
        //申请权限
        if(permissionList.size()>0){
            ActivityCompat.requestPermissions(this,permissions,PERMISSIONS_REQUEST_CODE);
        }else{
            Log.e(TAG,"已获得权限");
            AUDIO_PERMISSION=true;
        }
    }

    /**
     * 权限申请回调监听
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean hasPermissionDismiss = false;//有权限没有通过

        if (PERMISSIONS_REQUEST_CODE == requestCode) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == -1) {
                    hasPermissionDismiss = true;
                }
            }
            //如果有权限没有被允许
            if (hasPermissionDismiss) {
                showPermissionDialog();//跳转到系统设置权限页面，或者直接关闭页面，不让他继续访问
            }else{
                //全部权限通过，可以进行下一步操作。。。
                Log.e(TAG,"权限申请成功");
                AUDIO_PERMISSION=true;
            }
        }
    }

    /**
     * 权限被禁止后弹窗提示用户手动开启相关权限
     */
    public void showPermissionDialog(){
        Log.e(TAG,"000");
        new AlertDialog.Builder(BaseActivity.this)
                .setMessage("需要开启权限才能使用此功能")
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //引导用户到设置中去进行设置
                        Intent intent = new Intent();
                        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                        intent.setData(Uri.fromParts("package", getPackageName(), null));
                        startActivity(intent);
                    }
                })
                .setNegativeButton("取消", null)
                .create()
                .show();
    }
}