package com.example.andriodprimarycodedemo.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CustomBasicShapeView extends View {
    //创建画笔
    private Paint mPaint;

    public CustomBasicShapeView(Context context) {
        super(context);
    }

    public CustomBasicShapeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public CustomBasicShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    /**
     * 初始化画笔
     */
    private void initPaint(){
        mPaint=new Paint();
        mPaint.setColor(Color.BLUE);    //画笔颜色
        mPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mPaint.setStrokeWidth(10f);     //设置画笔宽度为10px
//        mPaint.setStyle(Paint.Style.STROKE);    //描边
//        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);   //填充并描边
    }
}
