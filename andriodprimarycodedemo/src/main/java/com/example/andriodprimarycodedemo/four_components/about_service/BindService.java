package com.example.andriodprimarycodedemo.four_components.about_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;

/**
 * Create by chenlei on 2018-11-26
 */
public class BindService extends Service {

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
        Logger.e("onDestory");
    }
}
