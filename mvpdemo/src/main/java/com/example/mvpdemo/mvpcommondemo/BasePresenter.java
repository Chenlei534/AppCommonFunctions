package com.example.mvpdemo.mvpcommondemo;

/**
 * @author Chen Lei
 * mvp封装所需要Model基础类，具体方法根据业务场景扩展
 * @param <V> 继承自BaseView的View接口
 * @param <M> 继承自BaseModel的Model接口
 */
public abstract class BasePresenter<V extends BaseView,M extends BaseModel> {
    protected V view;
    protected M model;

    public BasePresenter(){
        model=createModel();
    }

    /**
     * 常见View对象
     * @param view Activity对象
     */
    void attachView(V view){
        this.view=view;
    }

    /**
     * 将View对象置空，防止内存泄漏
     */
    void detachView(){
        this.view=null;
    }

    abstract M createModel();
}
