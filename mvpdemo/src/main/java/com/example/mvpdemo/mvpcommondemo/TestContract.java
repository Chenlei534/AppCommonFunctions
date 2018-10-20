package com.example.mvpdemo.mvpcommondemo;

import javax.security.auth.callback.Callback;

/**
 * @author Chen Lei
 * 使用demo，在Activity或Fragment中实现TextContract.View的接口，
 * 再分别创建两个类用来实现TextContract.Model和TextContract.Presenter，
 * 复写里面的抽象方法
 */
public interface TestContract {

    interface View extends BaseView{
        void updateUI1();
        void updateUI2();
        void updateUI3();
    }

    interface Model extends BaseModel{

//        void getData1(Callback1 callback1);
//        void getData2(Callback2 callback2);
//        void getData3(Callback3 callback3);
        void getDate4();
        void getDate5();
    }

    abstract class Presenter extends BasePresenter<View,Model>{
//        abstract void request1();
//        abstract void request2();
//        void request3(){
//            model.getData3(new Callback3() {
//                @Override
//                public void request3() {
//                    view.updateUI1();
//                }
//            });
//        };

    }

    interface Callback1{
        void request1();
    }

    interface Callback2{
        void request3();
    }

    interface Callback3{
        void request3();
    }
}
