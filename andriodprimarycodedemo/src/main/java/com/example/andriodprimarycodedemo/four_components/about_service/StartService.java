package com.example.andriodprimarycodedemo.four_components.about_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;


/**
 * Create by chenlei on 2018-11-26
 */
public class StartService extends Service {
    private int count=0;
    private boolean flag=true;
    /**
     * 服务绑定是调用
     * onCreate()->onBind()
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 服务初始化时调用，只调用一次 onCreate()
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.e("onCreate");
    }

    /**
     * 服务启动时调用 onCreate()->onStartCommand()
     * 如果读多次启动同一服务则该方法调用多次
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.e("onStartCommand");
        flag=true;
        startCount();
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 服务解绑时调用
     * onCreate()->onBind()->onUnbind()
     * @param intent
     * @return
     */
    @Override
    public boolean onUnbind(Intent intent) {
        Logger.e("onUnbind");
        return super.onUnbind(intent);
    }

    /**
     * 服务销毁时调用
     * 启动服务 onCreate()->onStartCommand()->onDestroy()
     * 绑定服务 onCreate()->onBind()->onUnbind()->onDestroy()
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        flag=false;
        Logger.e("onDestory");
    }

    /**
     * 启动一个线程来计数模拟后台任务
     */
    private void startCount(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (flag){
                        count++;
                        Logger.e(count+"");
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Logger.e("error");
                }
            }
        }).start();
    }
}
