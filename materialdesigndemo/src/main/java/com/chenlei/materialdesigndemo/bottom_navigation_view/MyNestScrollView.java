package com.chenlei.materialdesigndemo.bottom_navigation_view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

public class MyNestScrollView extends NestedScrollView {
    private Context context;

    public MyNestScrollView(Context context) {
        super(context);
        this.context=context;
    }

    public MyNestScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }

    public MyNestScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int winHeight=displayMetrics.heightPixels;
        int barHeight=((AppCompatActivity)context).getSupportActionBar().getHeight();

        heightMeasureSpec=winHeight-barHeight;
        Log.e("Test",winHeight+","+barHeight);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
