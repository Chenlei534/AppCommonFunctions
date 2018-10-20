package com.example.mvpdemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * 写一下感悟：
     *
     * 1.一个Activity对应的mvp模式需要：
     * 一个presenter类，来充当view与model的桥梁，也可书写部分逻辑
     * 一个model类，里面书写数据请求部分内容
     * 一个view接口，里面书写Activity需要展进行的操作，通过Activity继承
     * 一个modelcallback接口供presenter来对model类里面的内容回调，model中通过该接口向persenter发送内容
     *
     * 2.mvp实现方式因人而异没有固定的严格标准只要是实现了model、view、control的分离即可，
     * 但是接口还是必不可少的，可以不使用接口但是接口有利于组件的复用
     */
}
