package com.example.mvpdemo.loginmvpdemo;

/**
 * 作为view与presenter之间沟通的接口，
 * 接口书写view需要进行的操作
 */
public interface LoginView {
    //显示消息
    void showMessage(String msg);
    //清除密码
    void delPassword();
    //登录成功
    void loginSuccess();
}
