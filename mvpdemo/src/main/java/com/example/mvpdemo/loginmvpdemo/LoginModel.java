package com.example.mvpdemo.loginmvpdemo;


import android.os.Handler;
import android.os.Message;

public class LoginModel {
    private String user;
    private String password;
    private final static int loginSucess=1;
    private final static int pwError=2;
    private final static int pwIsNull=3;
    private LoginModelCallback callback;

    public LoginModel(String user,String password){
        this.user=user;
        this.password=password;
    }
    //异步更新UI
    private Handler mHandler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what){
                case loginSucess:
                    callback.loginSuccess();
                    break;
                case pwError:
                    callback.pwError();
                    break;
                case pwIsNull:
                    callback.pwIsNull();
                    break;
            }
            return false;
        }
    });

    /**
     * 获取登录返回结果
     * @param callback
     */
    public void loginRequest(LoginModelCallback callback){
        this.callback=callback;
        //开启线程模拟登录
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    //如果密码不是123456则进一步判断是否为空
                    if(!password.equals("123456")){
                        if(password.equals("") || password==null){
                            mHandler.obtainMessage(pwIsNull).sendToTarget();
                        }else {
                            mHandler.obtainMessage(pwError).sendToTarget();
                        }
                    }else{
                        mHandler.obtainMessage(loginSucess).sendToTarget();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
