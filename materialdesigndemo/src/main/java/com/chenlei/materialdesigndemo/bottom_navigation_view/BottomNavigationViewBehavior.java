package com.chenlei.materialdesigndemo.bottom_navigation_view;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import androidx.viewpager.widget.ViewPager;

public class BottomNavigationViewBehavior extends CoordinatorLayout.Behavior<BottomNavigationView> {

    public BottomNavigationViewBehavior() {
        super();
    }

    public BottomNavigationViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, BottomNavigationView child, View dependency) {

        return dependency instanceof ViewPager;
    }

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, BottomNavigationView child, View dependency) {

//        Log.e("=====",dependency.getY()+","+child.getHeight());

//        if(dependency.getY()<10){
//    //        child.setVisibility(View.GONE);
//        }else {
////            child.setVisibility(View.VISIBLE);
//            ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(child,"translationY",-child.getHeight());
//            objectAnimator.setDuration(1000);
//            objectAnimator.start();
//        }

        return true;
    }
    //决定是否需要监听滑动事件，这里监听下滑事件
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, BottomNavigationView child, View directTargetChild, View target, int axes, int type) {
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }
    private boolean offsetFlag=true;
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, BottomNavigationView child, View target, int dx, int dy, int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);

        Log.e("Test",""+dy+","+offsetFlag);


        if(dy>20){
            if(offsetFlag) {
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(child, "translationY", child.getHeight());
                objectAnimator.setDuration(500);
                objectAnimator.start();
                offsetFlag = false;
                Log.e("============", "隐藏" + offsetFlag);
            }
        }
        if(dy<-0){
            if(!offsetFlag) {
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(child, "translationY", 0);
                objectAnimator.setDuration(500);
                objectAnimator.start();
                offsetFlag = true;
                Log.e("============","显示"+offsetFlag);
            }
        }
    }
}
