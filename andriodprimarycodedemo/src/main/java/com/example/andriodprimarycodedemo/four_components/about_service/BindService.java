package com.example.andriodprimarycodedemo.four_components.about_service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;

/**
 * Create by chenlei on 2018-11-26
 */
public class BindService extends Service {
    /**
     * 服务绑定时调用
     * onCreate()->onBind()
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //返回需要进行交互的类
        return new MyService();
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

    /**
     * 建立内部类继承Binder实现与其他组件之间的通信
     * 该类是其他组件与Seriver之间通信的桥梁
     */
    class MyService extends Binder{
        /**
         * 打印其他组件中传入的字符串
         * @param str
         */
        public void serviceFuncation(String str){
            Logger.e(str);
        }
    }
}
