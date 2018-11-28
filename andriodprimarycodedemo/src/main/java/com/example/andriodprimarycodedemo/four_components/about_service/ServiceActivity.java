package com.example.andriodprimarycodedemo.four_components.about_service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.andriodprimarycodedemo.R;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Create by chenlei on 2018-11-26
 * 用来启动服务和绑定服务
 */
public class ServiceActivity extends AppCompatActivity {
    private Button mStartButton;
    private Button mStopButton;
    private Button mBindButton;
    private Button mUnbindButton;
    //启动服务和绑定服务所需要的Intent
    private Intent startServiceIntent;
    private Intent bindServiceIntent;
    //绑定服务时获取Service中Binder的类
    private ServiceConnection mServiceConnection;
    //绑定服务时与Service交互的类
    private BindService.MyService myService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        mStartButton=findViewById(R.id.start_service_button);
        mStopButton=findViewById(R.id.stop_service_button);
        mBindButton=findViewById(R.id.bind_service_button);
        mUnbindButton=findViewById(R.id.unbind_service_button);
        //监听事件
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startServiceIntent=new Intent(ServiceActivity.this,StartService.class);
                startService(startServiceIntent);
            }
        });
        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(startServiceIntent);
            }
        });
        mBindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bindServiceIntent=new Intent(ServiceActivity.this,BindService.class);
                bindService(bindServiceIntent,mServiceConnection,BIND_AUTO_CREATE);
            }
        });
        mUnbindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unbindService(mServiceConnection);
            }
        });
        /**
         * 与Service连接的类
         */
        mServiceConnection=new ServiceConnection() {
            /**
             * 与服务器端交互的接口方法 绑定服务的时候被回调，在这个方法获取绑定Service传递过来的IBinder对象，
             * 通过这个IBinder对象，实现组件和Service的交互。
             */
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                myService=(BindService.MyService)iBinder;
                myService.serviceFuncation("haha");
            }
            /**
             * 当取消绑定的时候不会被回调。正常情况下是不被调用的，它的调用时机是当Service服务被意外销毁时，
             * 例如内存的资源不足时这个方法才被自动调用。
             */
            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                myService=null;
            }
        };
    }
}
