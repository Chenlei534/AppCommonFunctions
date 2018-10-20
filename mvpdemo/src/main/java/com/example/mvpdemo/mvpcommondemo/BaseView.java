package com.example.mvpdemo.mvpcommondemo;

/**
 * @author Chen Lei
 * mvp封装所需要View基础类，具体方法根据业务场景扩展
 */
public interface BaseView {
    /**
     * 在Activity显示消息
     * @param msg 消息
     */
    void showMessage(String msg);
}
