package com.example.andriodprimarycodedemo.four_components.about_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;

/**
 * 设置成远程服务
 * 并与Activity绑定
 */
public class BindRemoteService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Logger.e("当前Service所在进程："+Process.myPid()+"");
        Logger.e("onBind");
        return mBinder;
    }

    /**
     * 实现自定义的MyAIDLService.aidl的实现类MyAIDLService.java中的Stub
     * 并重写其中的方法
     * Stub是Binder的子类，所以可以在onBind()方法里返回
     */
    MyAIDLService.Stub mBinder=new MyAIDLService.Stub() {
        @Override
        public int add(int a, int b) throws RemoteException {
            return a+b;
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    };


    @Override
    public void onCreate() {
        super.onCreate();
        Logger.e("当前Service所在进程："+Process.myPid()+"");
        Logger.e("onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Logger.e("当前Service所在进程："+Process.myPid()+"");
        Logger.e("onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
